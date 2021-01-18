package xyz.leuo.gooey;

import lombok.Data;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.leuo.gooey.listeners.InventoryClickListener;
import xyz.leuo.gooey.listeners.InventoryCloseListener;
import xyz.leuo.gooey.listeners.InventoryDragListener;
import xyz.leuo.gooey.listeners.InventoryMoveItemListener;
import xyz.leuo.gooey.tasks.Refresher;

import java.util.UUID;

@Data
public class Gooey {

    public static Gooey instance;
    private JavaPlugin plugin;
    private UUID instanceId;

    public Gooey(JavaPlugin plugin) {
        instance = this;
        this.plugin = plugin;
        this.instanceId = UUID.randomUUID();

        new InventoryClickListener(this.plugin, this);
        new InventoryCloseListener(this.plugin, this);
        new InventoryDragListener(this.plugin, this);
        new InventoryMoveItemListener(this.plugin, this);

        new Refresher(this.plugin, this);
    }
}
