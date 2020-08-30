package xyz.leuo.gooey;

import lombok.Data;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.leuo.gooey.listeners.InventoryClickListener;
import xyz.leuo.gooey.tasks.Refresher;

import java.util.UUID;

@Data
public class Gooey {

    private static Gooey instance;
    private JavaPlugin plugin;
    private UUID instanceId;

    public Gooey(JavaPlugin plugin) {
        instance = this;
        this.plugin = plugin;
        this.instanceId = UUID.randomUUID();

        new InventoryClickListener(this.plugin, this);
        new Refresher(this.plugin, this);
    }

    public static Gooey getInstance() {
        return instance;
    }
}
