package xyz.leuo.gooey.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.leuo.gooey.Gooey;
import xyz.leuo.gooey.action.Action;
import xyz.leuo.gooey.button.Button;
import xyz.leuo.gooey.gui.GUI;

public class InventoryClickListener implements Listener {

    private JavaPlugin plugin;
    private Gooey gooey;

    public InventoryClickListener(JavaPlugin plugin, Gooey gooey) {
        this.plugin = plugin;
        this.gooey = gooey;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();
        ClickType clickType = event.getClick();

        if(inventory != null) {
            if(inventory.getHolder() != null && inventory.getHolder() instanceof GUI) {
                GUI gui = (GUI) inventory.getHolder();
                if(gui.getInstanceId() == this.gooey.getInstanceId()) {
                    Button button;
                    if(item != null) {
                        button = gui.getButton(event.getSlot());
                        if(button != null) {
                            if(!button.isMoveable()) {
                                event.setCancelled(true);
                                if(button.isCloseOnClick()) {
                                    player.closeInventory();
                                }
                            }

                            if(button.getAction() != null) {
                                button.getAction().run(player, gui, button, clickType);
                            }
                        } else {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}