package io.opensphere.mantle.data.geom.style.dialog;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import io.opensphere.core.options.impl.AbstractPreferencesOptionsProvider;
import io.opensphere.core.preferences.PreferencesRegistry;
import io.opensphere.mantle.data.geom.style.VisualizationStyleController;

/**
 * The Class VisualizationStyleOptionsProvider.
 */
public class VisualizationStyleOptionsProvider extends AbstractPreferencesOptionsProvider
{
    /** The Panel. */
    private JPanel myPanel;

    /** The Reset all style data button. */
    private JButton myResetAllStyleDataButton;

    /** The Style controller. */
    private final VisualizationStyleController myStyleController;

    /**
     * Instantiates a new visualization style options provider.
     *
     * @param controller the controller
     * @param prefsRegistry the prefs registry
     */
    public VisualizationStyleOptionsProvider(VisualizationStyleController controller, PreferencesRegistry prefsRegistry)
    {
        super(prefsRegistry, VisualizationStyleControlDialog.TITLE);
        myStyleController = controller;
    }

    @Override
    public void applyChanges()
    {
    }

    @Override
    public JPanel getOptionsPanel()
    {
        if (myPanel == null)
        {
            myPanel = new JPanel();
            myPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
            myPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

            JTextArea ta = new JTextArea();
            ta.setBackground(TRANSPARENT_COLOR);
            ta.setBorder(BorderFactory.createEmptyBorder());
            ta.setFont(ta.getFont().deriveFont(Font.PLAIN, ta.getFont().getSize() + 1));
            ta.setEditable(false);
            ta.setMaximumSize(new Dimension(3000, 200));
            ta.setFocusable(true);
            ta.setWrapStyleWord(true);
            ta.setLineWrap(true);
            ta.setText("Use the button below to reset all style settings and restore all" + " types to use the default styles.");
            myPanel.add(ta);

            myPanel.add(Box.createVerticalStrut(10));

            Box subPanel = Box.createHorizontalBox();
            subPanel.setMaximumSize(new Dimension(3000, 30));
            subPanel.setPreferredSize(new Dimension(300, 30));
            subPanel.add(Box.createHorizontalGlue());
            subPanel.add(getResetAllStyleButton());
            subPanel.add(Box.createHorizontalGlue());
            subPanel.setBackground(TRANSPARENT_COLOR);

            myPanel.add(subPanel);
            JPanel emptyPanel = new JPanel();
            emptyPanel.setBackground(TRANSPARENT_COLOR);
            myPanel.add(emptyPanel);
            myPanel.add(Box.createVerticalGlue());
        }
        return myPanel;
    }

    @Override
    public void restoreDefaults()
    {
    }

    @Override
    public boolean usesApply()
    {
        return false;
    }

    @Override
    public boolean usesRestore()
    {
        return false;
    }

    /**
     * Gets the analyzer enabled check box.
     *
     * @return the analyzer enabled check box
     */
    private JButton getResetAllStyleButton()
    {
        if (myResetAllStyleDataButton == null)
        {
            myResetAllStyleDataButton = new JButton("Reset All Styles");
            myResetAllStyleDataButton.setFocusable(false);
            myResetAllStyleDataButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    int option = JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(myResetAllStyleDataButton),
                            "Are you sure you want to reset all styles to default and clear all style data?",
                            "Reset All Style Confirmation", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION)
                    {
                        myStyleController.resetAllStyleSettings(this);
                    }
                }
            });
        }
        return myResetAllStyleDataButton;
    }
}