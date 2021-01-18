package xyz.leuo.gooey.action.pagination;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import xyz.leuo.gooey.action.ButtonAction;
import xyz.leuo.gooey.button.Button;
import xyz.leuo.gooey.gui.GUI;
import xyz.leuo.gooey.gui.PaginatedGUI;

public class LastPageAction implements ButtonAction {
    @Override
    public void run(Player player, GUI gui, Button button, InventoryClickEvent event) {
        if(gui instanceof PaginatedGUI) {
            PaginatedGUI paginatedGUI = (PaginatedGUI) gui;
            if(paginatedGUI.hasPage(paginatedGUI.getCurrentPage() - 1)) {
                player.openInventory(paginatedGUI.getPage((paginatedGUI.getCurrentPage()) - 1));
            } else {
                player.sendMessage(ChatColor.RED + "The page you tried to access doesn't exist, so you have been brought back to the first page.");
                paginatedGUI.open(player);
            }
        } else {
            throw new ClassCastException("This action is only for a PaginatedGUI, and will break otherwise.");
        }
    }
}
