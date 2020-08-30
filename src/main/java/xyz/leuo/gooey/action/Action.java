package xyz.leuo.gooey.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.leuo.gooey.button.Button;
import xyz.leuo.gooey.gui.GUI;

public interface Action {
    void run(Player player, GUI gui, Button button, ClickType clickType);
}
