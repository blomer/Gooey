package xyz.leuo.gooey.gui;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import xyz.leuo.gooey.action.CommandAction;
import xyz.leuo.gooey.button.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GUI implements InventoryHolder {

    private String title;
    private int size;
    private boolean autoRefresh;
    private Map<Integer, Button> buttons;
    private ItemStack background;

    public void addButton(Integer slot, Button button) {
        this.buttons.put(slot, button);
    }

    public void removeButton(Integer slot) {
        this.buttons.remove(slot);
    }

    public Button getButton(Integer slot) {
        return this.buttons.get(slot);
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, size, ChatColor.translateAlternateColorCodes('&', title));

        for(Map.Entry<Integer, Button> entry : this.buttons.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue());
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

    public void refresh() {
        List<HumanEntity> viewers = this.getInventory().getViewers();
        for(HumanEntity viewer : viewers) {
            viewer.openInventory(this.getInventory());
        }
    }

    /**
     * Creates a new GUI.
     * @param title Inventory title.
     * @param size Inventory size.
     */
    public GUI(String title, int size) {
        this.title = title;
        this.size = size;
        this.autoRefresh = false;
        this.buttons = new HashMap<>();
    }
}
