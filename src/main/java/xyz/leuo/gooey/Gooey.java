package xyz.leuo.gooey;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.leuo.gooey.action.Action;
import xyz.leuo.gooey.button.Button;
import xyz.leuo.gooey.gui.GUI;
import xyz.leuo.gooey.gui.GUIUpdate;
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

        GUI gui = new GUI("Another Gooey GUI", 9);
        gui.setUpdate(new GUIUpdate() {
            @Override
            public void onUpdate(GUI gui) {
                if(Bukkit.getOnlinePlayers().size() == 2) {
                    gui.setTitle("A Different Title");
                } else {
                    gui.setTitle("Standard Title");
                }
            }
        });
    }

    public static Gooey getInstance() {
        return instance;
    }
}
