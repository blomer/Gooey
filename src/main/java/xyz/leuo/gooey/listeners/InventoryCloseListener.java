package xyz.leuo.gooey.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.leuo.gooey.Gooey;
import xyz.leuo.gooey.gui.GUI;

public class InventoryCloseListener implements Listener {

    private JavaPlugin plugin;
    private Gooey gooey;
    public InventoryCloseListener(JavaPlugin plugin, Gooey gooey) {
        this.plugin = plugin;
        this.gooey = gooey;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        if(inventory.getHolder() != null && inventory.getHolder() instanceof GUI) {
            GUI gui = (GUI) inventory.getHolder();
            if(gui.getInstanceId() == this.gooey.getInstanceId() && gui.getGuiCloseUpdate() != null) {
                Bukkit.getScheduler().runTaskLater(plugin, ()-> gui.getGuiCloseUpdate().onClose((Player) event.getPlayer(), gui, event), 1);
            }
        }
    }
}
