package xyz.leuo.gooey.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.leuo.gooey.action.Action;
import xyz.leuo.gooey.button.Button;
import xyz.leuo.gooey.gui.GUI;

public class InventoryClickListener implements Listener {

    private JavaPlugin plugin;
    public InventoryClickListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ClickType clickType = event.getClick();

        if(inventory.getHolder() != null && inventory.getHolder() instanceof GUI) {
            GUI gui = (GUI) inventory.getHolder();
            Button button = gui.getButton(event.getSlot());
            if(button != null) {
                if(!button.isMoveable()) {
                    event.setCancelled(true);
                }

                if(button.isCloseOnClick()) {
                    player.closeInventory();
                }

                for(Action action : button.getActions()) {
                    action.run(player, gui, button, clickType);
                }
            }
        }
    }
}