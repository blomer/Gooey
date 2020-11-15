package xyz.leuo.gooey.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.leuo.gooey.button.Button;
import xyz.leuo.gooey.gui.GUI;

public class OpenGUIAction implements Action{

    private final GUI gui;

    @Override
    public void run(Player player, GUI gui, Button button, InventoryClickEvent event) {
        player.openInventory(this.gui.getInventory());
    }

    /**
     * Opens a GUI.
     * @param gui The GUI to be opened for the executor.
     */
    public OpenGUIAction(GUI gui) {
        this.gui = gui;
    }
}
