package xyz.leuo.gooey.gui;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import xyz.leuo.gooey.action.pagination.LastPageAction;
import xyz.leuo.gooey.action.pagination.NextPageAction;
import xyz.leuo.gooey.button.Button;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginatedGUI extends GUI {

    private int currentPage;
    private List<Button> pageButtons;

    @Override
    public void addButton(Button button) {
        this.pageButtons.add(button);
    }

    @Override
    public void setButton(int slot, Button button) {
        this.pageButtons.add(slot, button);
    }

    @Override
    public void removeButton(Button button) {
        this.pageButtons.remove(button);
    }

    @Override
    public Button getButton(int slot) {
        Button button = null;
        int i = slot + (this.currentPage * (this.getSize() - 9));
        if(slot < this.getSize() - 9) {
            if(this.pageButtons.size() > i) {
                button = this.getPageButtons().get(i);
            }
        } else {
            if(this.getPage(this.currentPage).getItem(slot).getType() == Material.ARROW) {
                if (slot == this.getSize() - 1) {
                    button = new Button(Material.ARROW, 1, "&eNext Page");
                    button.setAction(new NextPageAction());
                } else if (slot == this.getSize() - 9) {
                    button = new Button(Material.ARROW, 1, "&cPrevious Page");
                    button.setAction(new LastPageAction());
                }
            }
        }

        return button;
    }

    @Override
    public Inventory getInventory() {
        return this.getPage(0);
    }

    public Inventory getPage(int pageNumber) {
        Inventory inventory = Bukkit.createInventory(this, this.getSize(), this.getTitle());

        if(this.hasPage(pageNumber)) {
            this.currentPage = pageNumber;
            final int inv = this.getSize() - 9;
            final int index = this.currentPage * inv;
            for(int x = 0; x <= inv - 1; x++) {
                if(this.pageButtons.size() > index + x) {
                    Button button = this.pageButtons.get(index + x);
                    button.update();
                    inventory.setItem(x, button);
                } else {
                    break;
                }
            }

            Button separator = new Button(Material.STAINED_GLASS_PANE, 1, " ");
            separator.setDurability((short) 15);
            for(int x = this.getSize() - 9; x < this.getSize(); x++) {
                inventory.setItem(x, separator);
            }

            if(this.hasPage(pageNumber + 1)) {
                Button button = new Button(Material.ARROW, 1, "&eNext Page");
                button.setAction(new NextPageAction());
                inventory.setItem(this.getSize() - 1, button);
            }

            if(pageNumber != 0) {
                Button button = new Button(Material.ARROW, 1, "&cPrevious Page");
                button.setAction(new LastPageAction());
                inventory.setItem(this.getSize() - 9, button);
            }

            return inventory;
        } else {
            return null;
        }
    }

    public boolean hasPage(Integer pageNumber) {
        return pageNumber * (this.getSize() - 9) < this.pageButtons.size();
    }

    /**
     * Creates a new PaginatedGUI. Please note that a PaginatedGUI must be instantiated each time it is opened.
     * @param title Inventory title.
     * @param size Inventory size.
     */
    public PaginatedGUI(String title, Integer size) {
        super(title, size);

        if(size < 18) {
            throw new IllegalArgumentException("Size must be at least 18.");
        }

        this.currentPage = 0;
        this.pageButtons = new ArrayList<>();
    }
}
