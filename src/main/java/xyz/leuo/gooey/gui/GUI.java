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
    private GUICloseUpdate guiCloseUpdate;

    public void addButton(Button button) {
        if(buttons.isEmpty()) {
            this.buttons.put(0, button);
        } else {
            this.buttons.put(buttons.size(), button);
        }
    }

    public void addButtons(Button... buttons) {
        for(Button button : buttons) {
            this.addButton(button);
        }
    }

    public void setButton(int slot, Button button) {
        this.buttons.put(slot, button);
    }

    public void removeButton(int slot) {
        this.buttons.remove(slot);
    }

    /**
     * This method is useless within this class.
     * @param button The button to be removed.
     */
    public void removeButton(Button button) {
    }

    public Button getButton(int slot) {
        return this.buttons.get(slot);
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, size, ChatColor.translateAlternateColorCodes('&', title));

        if(this.background != null) {
            for(int x = 0; x < inventory.getSize(); x++) {
                inventory.setItem(x, this.background);
            }
        }

        for(Map.Entry<Integer, Button> entry : this.buttons.entrySet()) {
            Button button = entry.getValue();
            button.update();
            inventory.setItem(entry.getKey(), button);
        }

        return inventory;
    }

    /**
     * Opens the GUI for a player.
     * @param player The player that will see the GUI.
     */
    public void open(Player player) {
        player.openInventory(this.getInventory());
    }

    public void open(Player player, boolean update) {
        if(update && this.getUpdate() != null) {
            this.update();
        }
        open(player);
    }

    /**
     * Updates the GUI with whatever GUIUpdate method was set for this GUI.
     */
    public void update() {
        this.update.onUpdate(this);
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getOpenInventory().getTopInventory() != null) {
                Inventory inventory = player.getOpenInventory().getTopInventory();
                if(inventory.getHolder() instanceof GUI) {
                    GUI gui = (GUI) inventory.getHolder();
                    if(gui.equals(this)) {
                        gui.open(player);
                    }
                }
            }
        }
    }

    /**
     * Creates a new GUI.
     * @param title Inventory title.
     * @param size Inventory size.
     */
    public GUI(String title, int size) {
        this.instanceId = Gooey.instance.getInstanceId();
        this.title = title;
        this.size = size;
        this.autoRefresh = false;
        this.buttons = new HashMap<>();
    }
}
