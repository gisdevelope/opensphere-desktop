package io.opensphere.core.control.keybinding;

import javafx.beans.property.SimpleStringProperty;

/**
 * Holds the information for entries within a JavaFX tableview used in the "Menu
 * Shortcuts" tab of the "Help" -> "Key Map" Menu.
 */
public class MenuShortCut
{

    /**
     * The category of the shortcut.
     */
    private final SimpleStringProperty myMenu;

    /**
     * The action the shortcut executes.
     */
    private final SimpleStringProperty myItem;

    /**
     * The physical key the shortcut is mapped onto.
     */
    private final SimpleStringProperty myShortcut;

    /**
     * @param menu the category of the shortcut.
     * @param item the action the shortcut executes.
     * @param shortcut the physical key mapped.
     */
    public MenuShortCut(String menu, String item, String shortcut)
    {
        this.myMenu = new SimpleStringProperty(menu);
        this.myItem = new SimpleStringProperty(item);
        this.myShortcut = new SimpleStringProperty(shortcut);
    }

    /**
     * @return myMenu gets the category of the shortcut.
     */
    public String getMenu()
    {
        return myMenu.get();
    }

    /**
     * @param menu sets the category of the shortcut.
     */
    public void setMenuName(String menu)
    {
        myMenu.set(menu);
    }

    /**
     * @return myItem gets the action performed.
     */
    public String getItem()
    {
        return myItem.get();
    }

    /**
     * @param Item sets the action performed.
     */
    public void setItem(String item)
    {
        myItem.set(item);
    }

    /**
     * @return Gets the physical key mapped.
     */
    public String getShortcut()
    {
        return myShortcut.get();
    }

    /**
     * @param Shortcut sets the physical key mapped.
     */
    public void setShortcut(String shortcut)
    {
        myShortcut.set(shortcut);
    }
}
