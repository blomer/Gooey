package xyz.leuo.gooey.action.pagination;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.leuo.gooey.action.Action;
import xyz.leuo.gooey.button.Button;
import xyz.leuo.gooey.gui.GUI;
import xyz.leuo.gooey.gui.PaginatedGUI;

public class NextPageAction implements Action {
    @Override
    public void run(Player player, GUI gui, Button button, InventoryClickEvent event) {
        if(gui instanceof PaginatedGUI) {
            PaginatedGUI paginatedGUI = (PaginatedGUI) gui;
            if(paginatedGUI.hasPage(paginatedGUI.getCurrentPage() + 1)) {
                player.openInventory(paginatedGUI.getPage(paginatedGUI.getCurrentPage() + 1));
            } else {
                player.sendMessage(ChatColor.RED + "There is no next page to show.");
            }
        } else {
            throw new ClassCastException("This action is only for a PaginatedGUI, and will break otherwise.");
        }
    }
}
