package xyz.leuo.gooey.gui;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import xyz.leuo.gooey.Gooey;
import xyz.leuo.gooey.button.Button;

import java.util.*;

@Data
public class GUI implements InventoryHolder {

    private UUID instanceId;
    private String title;
    private int size;
    private boolean autoRefresh;
    private Map<Integer, Button> buttons;
    private ItemStack background;
    private GUIUpdate update;

    public void addButton(Button button) {
        if(buttons.isEmpty()) {
            this.buttons.put(0, button);
        } else {
            this.buttons.put(buttons.size(), button);
        }
    }

    public void setButton(Integer slot, Button button) {
        this.buttons.put(slot, button);
    }

    public void removeButton(Integer slot) {
        this.buttons.remove(slot);
    }

    /**
     * This method is useless within this class.
     * @param button The button to be removed.
     */
    public void removeButton(Button button) {
    }

    public Button getButton(Integer slot) {
        if(this.buttons.size() > slot) {
            return this.buttons.get(slot);
        } else {
            return null;
        }
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, size, ChatColor.translateAlternateColorCodes('&', title));

        for(Map.Entry<Integer, Button> entry : this.buttons.entrySet()) {
            Button button = entry.getValue();
            button.update();
            inventory.setItem(entry.getKey(), button);
        }

        if(this.background != null) {
            for(int x = 0; x < inventory.getSize() - 1; x++) {
                inventory.setItem(x, this.background);
            }
        }

        return inventory;
    }

    public void open(Player player) {
        player.openInventory(this.getInventory());
    }

    public void update() {
        List<HumanEntity> viewers = this.getInventory().getViewers();
        for(HumanEntity viewer : viewers) {
            this.update.onUpdate(this);
            viewer.openInventory(this.getInventory());
        }
    }

    /**
     * Creates a new GUI.
     * @param title Inventory title.
     * @param size Inventory size.
     */
    public GUI(String title, int size) {
        this.instanceId = Gooey.getInstance().getInstanceId();
        this.title = title;
        this.size = size;
        this.autoRefresh = false;
        this.buttons = new HashMap<>();
    }
}
