package io.opensphere.server.serverprovider.http.requestors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.easymock.IAnswer;
import org.junit.Test;

import com.bitsys.common.http.client.HttpClient;
import com.bitsys.common.http.entity.HttpEntity;
import com.bitsys.common.http.message.HttpRequest;
import com.bitsys.common.http.message.HttpResponse;
import com.google.common.collect.ListMultimap;

import io.opensphere.core.server.ResponseValues;
import io.opensphere.core.util.collections.New;
import io.opensphere.core.util.io.CancellableInputStream;
import io.opensphere.core.util.lang.ByteString;

/**
 * Tests the GetRequestor class.
 */
public class GetRequestorTest
{
    /**
     * The expected headers.
     */
    private static final Map<String, Collection<String>> ourHeaderValues = New.map();

    /**
     * The expected content length.
     */
    private static final long ourLength = 100;

    /**
     * Tests the get request.
     *
     * @throws IOException Bad io.
     * @throws URISyntaxException Bad URI.
     */
    @Test
    public void testGetRequest() throws IOException, URISyntaxException
    {
        EasyMockSupport support = new EasyMockSupport();

        URL url = new URL("http://somehost/getStuff?param=stuff");

        ByteArrayInputStream stream = new ByteArrayInputStream(new byte[0]);

        HttpResponse response = createResponse(support, stream, false, false);
        HttpClient client = createClient(support, response, url.toURI(), false);

        support.replayAll();

        ResponseValues responseValues = new ResponseValues();

        GetRequestorImpl requestor = new GetRequestorImpl(client, new HeaderConstantsMock());
        CancellableInputStream actual = requestor.sendGet(url, responseValues);

        assertEquals(stream, actual.getWrappedInputStream());
        assertEquals(HttpURLConnection.HTTP_OK, responseValues.getResponseCode());
        assertEquals(ourLength, responseValues.getContentLength());
        assertEquals(ourHeaderValues, responseValues.getHeader());

        support.verifyAll();
    }

    /**
     * Tests the get request with extra header values.
     *
     * @throws IOException Bad io.
     * @throws URISyntaxException Bad URI.
     */
    @Test
    public void testGetRequestExtraHeaders() throws IOException, URISyntaxException
    {
        EasyMockSupport support = new EasyMockSupport();

        URL url = new URL("http://somehost/getStuff?param=stuff");

        ByteArrayInputStream stream = new ByteArrayInputStream(new byte[0]);

        HttpResponse response = createResponse(support, stream, false, false);
        HttpClient client = createClient(support, response, url.toURI(), true);

        support.replayAll();

        ResponseValues responseValues = new ResponseValues();

        GetRequestorImpl requestor = new GetRequestorImpl(client, new HeaderConstantsMock());

        Map<String, String> extraHeaderValues = New.map();
        extraHeaderValues.put("key1", "value1");
        extraHeaderValues.put("key2", "value2");

        CancellableInputStream actual = requestor.sendGet(url, extraHeaderValues, responseValues);

        assertEquals(stream, actual.getWrappedInputStream());
        assertEquals(HttpURLConnection.HTTP_OK, responseValues.getResponseCode());
        assertEquals(ourLength, responseValues.getContentLength());
        assertEquals(ourHeaderValues, responseValues.getHeader());

        support.verifyAll();
    }

    /**
     * Tests the get request.
     *
     * @throws IOException Bad io.
     * @throws URISyntaxException Bad URI.
     */
    @Test
    public void testGetWithError() throws IOException, URISyntaxException
    {
        EasyMockSupport support = new EasyMockSupport();

        URL url = new URL("http://somehost/getStuff?param=stuff");

        ByteArrayInputStream stream = new ByteArrayInputStream(new byte[0]);

        HttpResponse response = createResponse(support, stream, true, false);
        HttpClient client = createClient(support, response, url.toURI(), false);

        support.replayAll();

        ResponseValues responseValues = new ResponseValues();

        GetRequestorImpl requestor = new GetRequestorImpl(client, new HeaderConstantsMock());
        CancellableInputStream actual = requestor.sendGet(url, responseValues);

        assertEquals(stream, actual.getWrappedInputStream());
        assertEquals(HttpURLConnection.HTTP_FORBIDDEN, responseValues.getResponseCode());
        assertEquals("Error Message", responseValues.getResponseMessage());
        assertEquals(ourLength, responseValues.getContentLength());
        assertEquals(ourHeaderValues, responseValues.getHeader());

        support.verifyAll();
    }

    /**
     * Tests returning a zipped stream.
     *
     * @throws URISyntaxException Bad URI.
     * @throws IOException Bad IO
     */
    @Test
    public void testZippedReturn() throws IOException, URISyntaxException
    {
        EasyMockSupport support = new EasyMockSupport();

        URL url = new URL("http://somehost/getStuff?param=stuff");

        String testData = "Some test data here";

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        GZIPOutputStream output = new GZIPOutputStream(byteOut);
        output.write(ByteString.getBytes(testData));
        output.flush();
        output.close();

        ByteArrayInputStream stream = new ByteArrayInputStream(byteOut.toByteArray());

        HttpResponse response = createResponse(support, stream, false, true);
        HttpClient client = createClient(support, response, url.toURI(), false);

        support.replayAll();

        ResponseValues responseValues = new ResponseValues();

        GetRequestorImpl requestor = new GetRequestorImpl(client, new HeaderConstantsMock());
        InputStream actual = requestor.sendGet(url, responseValues);

        List<Byte> bytes = New.list();
        int read = actual.read();
        while (read >= 0)
        {
            bytes.add(Byte.valueOf((byte)read));
            read = actual.read();
        }

        byte[] byteArray = new byte[bytes.size()];
        int index = 0;
        for (Byte aByte : bytes)
        {
            byteArray[index] = aByte.byteValue();
            index++;
        }

        String actualString = ByteString.getStringFromBytes(byteArray);

        assertEquals(testData, actualString);
        assertEquals(HttpURLConnection.HTTP_OK, responseValues.getResponseCode());
        assertEquals(ourLength, responseValues.getContentLength());

        support.verifyAll();
    }

    /**
     * Create easy mocked HttpClient.
     *
     * @param support The easy mock support.
     * @param response The HttpResponse to return.
     * @param expectedUri The expected uri in the request.
     * @param expectExtraHeaders True if we should expect extra header values,
     *            false otherwise.
     * @return The easy mocked HttpClient.
     * @throws IOException Bad io.
     */
    private HttpClient createClient(EasyMockSupport support, final HttpResponse response, final URI expectedUri,
            boolean expectExtraHeaders) throws IOException
    {
        HttpClient client = support.createMock(HttpClient.class);

        client.execute(EasyMock.isA(HttpRequest.class));
        EasyMock.expectLastCall().andAnswer(new IAnswer<HttpResponse>()
        {
            @Override
            public HttpResponse answer()
            {
                HttpRequest request = (HttpRequest)EasyMock.getCurrentArguments()[0];
                assertEquals(HttpRequest.GET, request.getMethod());
                assertTrue(request.getHeaders().asMap().get("Accept-Encoding").contains("gzip,default"));
                assertTrue(request.getHeaders().asMap().get("User-Agent").contains(new HeaderConstantsMock().getUserAgent()));

                if (expectExtraHeaders)
                {
                    assertTrue(request.getHeaders().asMap().get("key1").contains("value1"));
                    assertTrue(request.getHeaders().asMap().get("key2").contains("value2"));
                }

                assertEquals(expectedUri, request.getURI());

                return response;
            }
        });

        return client;
    }

    /**
     * Creates the easy mocked response object.
     *
     * @param support The easy mock support.
     * @param returnStream The stream to return.
     * @param isError True if the response code should be an error.
     * @param isCompressed True if the response stream should be compressed.
     * @return The easy mocked support.
     */
    private HttpResponse createResponse(EasyMockSupport support, InputStream returnStream, boolean isError, boolean isCompressed)
    {
        HttpResponse response = support.createMock(HttpResponse.class);

        response.getStatusCode();

        if (!isError)
        {
            EasyMock.expectLastCall().andReturn(Integer.valueOf(HttpURLConnection.HTTP_OK));
        }
        else
        {
            EasyMock.expectLastCall().andReturn(Integer.valueOf(HttpURLConnection.HTTP_FORBIDDEN));
        }

        response.getStatusMessage();

        if (!isError)
        {
            EasyMock.expectLastCall().andReturn(null);
        }
        else
        {
            EasyMock.expectLastCall().andReturn("Error Message");
        }

        @SuppressWarnings("unchecked")
        ListMultimap<String, String> headerValues = support.createMock(ListMultimap.class);
        headerValues.asMap();

        if (isCompressed)
        {
            Map<String, Collection<String>> headerMap = New.map();
            headerMap.put("Content-Encoding", New.list("gzip"));
            EasyMock.expectLastCall().andReturn(headerMap);
        }
        else
        {
            EasyMock.expectLastCall().andReturn(ourHeaderValues);
        }

        response.getHeaders();
        EasyMock.expectLastCall().andReturn(headerValues);

        HttpEntity entity = support.createMock(HttpEntity.class);
        entity.getContent();
        EasyMock.expectLastCall().andReturn(returnStream);
        entity.getContentLength();
        EasyMock.expectLastCall().andReturn(Long.valueOf(ourLength));

        response.getEntity();
        EasyMock.expectLastCall().andReturn(entity);

        return response;
    }
}