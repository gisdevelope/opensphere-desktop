package io.opensphere.mantle.data.geom.style.impl.ui;

import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import io.opensphere.core.util.collections.New;
import io.opensphere.core.util.swing.EventQueueUtilities;

/**
 * The Class StyleParameterEditorGroupPanel.
 */
public class StyleParameterEditorGroupPanel extends JPanel
{
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /** The Editor panels. */
    private final List<AbstractStyleParameterEditorPanel> myEditorPanels;

    /** The Show border. */
    private final boolean myShowBorder;

    /** The Spacing. */
    private final int mySpacing;

    /**
     * Instantiates a new style parameter editor group panel.
     */
    public StyleParameterEditorGroupPanel()
    {
        this(null, Collections.<AbstractStyleParameterEditorPanel>emptyList(), true, 4);
    }

    /**
     * Instantiates a new style parameter editor group panel.
     *
     * @param panels the panels
     */
    public StyleParameterEditorGroupPanel(List<AbstractStyleParameterEditorPanel> panels)
    {
        this(null, panels, true, 4);
    }

    /**
     * Instantiates a new style parameter editor group panel.
     *
     * @param borderTitle the border title
     */
    public StyleParameterEditorGroupPanel(String borderTitle)
    {
        this(borderTitle, Collections.<AbstractStyleParameterEditorPanel>emptyList(), true, 4);
    }

    /**
     * Instantiates a new style parameter editor group panel.
     *
     * @param borderTitle the border title
     * @param panels the panels
     */
    @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
    public StyleParameterEditorGroupPanel(String borderTitle, List<AbstractStyleParameterEditorPanel> panels)
    {
        this(borderTitle, panels, true, 4);
    }

    /**
     * Instantiates a new style parameter editor group panel.
     *
     * @param borderTitle the border title
     * @param panels the panels
     * @param showBorders the show borders
     * @param panelSpacing the panel spacing
     */
    @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
    public StyleParameterEditorGroupPanel(String borderTitle, List<AbstractStyleParameterEditorPanel> panels, boolean showBorders,
            int panelSpacing)
    {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        myEditorPanels = New.list();
        if (borderTitle != null && !borderTitle.isEmpty())
        {
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), borderTitle));
        }
        myEditorPanels.addAll(panels);
        myShowBorder = showBorders;
        mySpacing = panelSpacing;
        rebuild();
    }

    /**
     * Adds the editor panel.
     *
     * @param panel the panel
     */
    public void addEditorPanel(AbstractStyleParameterEditorPanel panel)
    {
        myEditorPanels.add(panel);
        rebuild();
    }

    /**
     * Rebuild internal.
     */
    public void rebuildInternal()
    {
        removeAll();
        for (AbstractStyleParameterEditorPanel pnl : myEditorPanels)
        {
            if (myShowBorder)
            {
                pnl.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20), BorderFactory
                        .createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(2, 0, 2, 2))));
            }
            add(pnl);

            if (mySpacing > 0)
            {
                add(Box.createVerticalStrut(mySpacing));
            }
        }
        revalidate();
    }

    /**
     * Update.
     */
    public void update()
    {
        for (AbstractStyleParameterEditorPanel pnl : myEditorPanels)
        {
            pnl.update();
        }
    }

    /**
     * Rebuild.
     */
    private void rebuild()
    {
        EventQueueUtilities.runOnEDT(this::rebuildInternal);
    }
}