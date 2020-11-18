package xyz.leuo.gooey.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.leuo.gooey.Gooey;
import xyz.leuo.gooey.button.Button;
import xyz.leuo.gooey.gui.GUI;

public class InventoryDragListener implements Listener {

    private JavaPlugin plugin;
    private Gooey gooey;

    public InventoryDragListener(JavaPlugin plugin, Gooey gooey) {
        this.plugin = plugin;
        this.gooey = gooey;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Inventory inventory = event.getInventory();
        if(inventory.getHolder() != null && inventory.getHolder() instanceof GUI) {
            GUI gui = (GUI) inventory.getHolder();
            if(gui.getInstanceId() == this.gooey.getInstanceId()) {
                event.setCancelled(true);
            }
        }
    }
}
