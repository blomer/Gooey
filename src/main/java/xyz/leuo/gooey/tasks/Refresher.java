package xyz.leuo.gooey.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.leuo.gooey.action.CommandAction;
import xyz.leuo.gooey.gui.GUI;

public class Refresher {

    public Refresher(JavaPlugin plugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ()-> {
            for(Player player : Bukkit.getOnlinePlayers()) {
                if(player.getOpenInventory().getTopInventory() != null) {
                    Inventory inventory = player.getOpenInventory().getTopInventory();
                    if(inventory.getHolder() instanceof GUI) {
                        GUI gui = (GUI) inventory.getHolder();
                        if(gui.isAutoRefresh()) {
                            gui.refresh();
                        }
                    }
                }
            }
        }, 0, 10);

    }
}
