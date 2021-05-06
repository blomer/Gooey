package xyz.leuo.gooey.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.leuo.gooey.button.Button;
import xyz.leuo.gooey.gui.GUI;

public interface ButtonAction {
    void run(Player player, GUI gui, Button button, InventoryClickEvent event);
}
