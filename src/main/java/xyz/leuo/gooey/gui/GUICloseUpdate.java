package xyz.leuo.gooey.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;

public interface GUICloseUpdate {

    void onClose(Player player, GUI gui, InventoryCloseEvent event);
}
