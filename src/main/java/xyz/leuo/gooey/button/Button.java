package xyz.leuo.gooey.button;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.leuo.gooey.action.Action;

import java.util.ArrayList;
import java.util.List;

public class Button extends ItemStack {

    private @Getter @Setter ItemMeta meta = this.getItemMeta();
    private @Getter @Setter List<Action> actions;
    private @Getter @Setter boolean moveable, closeOnClick;

    public String getName() {
        return this.meta.getDisplayName();
    }

    public void setName(String name) {
        this.meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        this.setItemMeta(this.meta);
        this.update();
    }

    public List<String> getLore() {
        return this.meta.getLore();
    }

    public void setLore(List<String> lore) {
        List<String> list = new ArrayList<>();
        for(String line : lore) {
            list.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        this.meta.setLore(list);
        this.update();
    }

    /**
     * Updates the ItemMeta for this ItemStack. You should never have to directly call this method.
     */
    public void update() {
        this.setItemMeta(this.meta);
    }

    /**
     * Create a button to be added into a GUI.
     * @param material The material of the ItemStack.
     * @param amount The amount of the ItemStack.
     * @param name The name of the Button/Item.
     */
    public Button(Material material, int amount, String name) {
        super(material, amount, (short) 0);
        this.setName(name);
        this.moveable = false;
        this.closeOnClick = false;
        this.meta.setLore(new ArrayList<>());
        this.actions = new ArrayList<>();
    }
}
