package xyz.leuo.gooey;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.leuo.gooey.listeners.InventoryClickListener;
import xyz.leuo.gooey.tasks.Refresher;

public class Gooey {

    private static Gooey instance;
    private JavaPlugin plugin;

    public Gooey(JavaPlugin plugin) {
        instance = this;
        this.plugin = plugin;

        new InventoryClickListener(this.plugin);
        new Refresher(this.plugin);
    }

    public static Gooey getInstance() {
        return instance;
    }
}
