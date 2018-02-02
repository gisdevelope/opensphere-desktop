package io.opensphere.kml.settings;

import io.opensphere.core.util.swing.binding.ComboBox;
import io.opensphere.core.util.swing.input.ViewPanel;
import io.opensphere.kml.common.model.ScalingMethod;

/** KML settings panel. */
class KMLSettingsPanel extends ViewPanel
{
    /** The serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param model the view model
     */
    public KMLSettingsPanel(KMLSettingsViewModel model)
    {
        super();
        ComboBox<ScalingMethod> comboBox = new ComboBox<>(model.scalingMethodProperty(), model.getScalingMethodOptions());
        comboBox.setToolTipText("How to scale icons and labels");
        addLabelComponent("Scaling Method:", comboBox);
    }
}