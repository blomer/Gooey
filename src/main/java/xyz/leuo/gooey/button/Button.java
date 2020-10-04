package xyz.leuo.gooey.button;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.leuo.gooey.action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Button extends ItemStack {

    private @Getter @Setter ItemMeta meta;
    private @Getter @Setter Action action;
    private @Getter @Setter boolean moveable, closeOnClick;

    public String getName() {
        return this.meta.getDisplayName();
    }

    public void setName(String name) {
        this.meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        this.setItemMeta(this.meta);
        this.update();
    }

    /**
     * Gets the lore directly from the ItemMeta.
     * @return Lore list.
     */
    public List<String> getLore() {
        return this.meta.getLore();
    }

    /**
     * Sets the lore for this button.
     * @param strings The lines of lore.
     */
    public void setLore(String... strings) {
        List<String> list = new ArrayList<>();
        for(String string : strings) {
            list.add(ChatColor.translateAlternateColorCodes('&', string));
        }

        this.meta.setLore(list);
        this.update();
    }

    public void setLore(List<String> lore) {
        List<String> list = new ArrayList<>();
        for(String string : lore) {
            list.add(ChatColor.translateAlternateColorCodes('&', string));
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
        this.meta = this.getItemMeta();
        if(name != null) {
            this.setName(name);
        }
        this.moveable = false;
        this.closeOnClick = false;
        this.meta.setLore(new ArrayList<>());
    }

    public Button(ItemStack itemStack, String name) {
        super(itemStack.getType(), itemStack.getAmount(), itemStack.getDurability());
        this.setItemMeta(itemStack.getItemMeta());
        this.meta = this.getItemMeta();
        if(name != null) {
            this.setName(name);
        }
        this.moveable = false;
        this.closeOnClick = false;

        if(itemStack.getEnchantments() != null) {
            this.addEnchantments(itemStack.getEnchantments());
        }

        if(this.meta.getLore() == null) {
            this.meta.setLore(new ArrayList<>());
        }
    }
}
