package xyz.leuo.gooey.action;

import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import xyz.leuo.gooey.button.Button;
import xyz.leuo.gooey.gui.GUI;

@Data
public class CommandAction implements Action{

    private String command;

    @Override
    public void run(Player player, GUI gui, Button button, ClickType clickType) {
        String c = this.command;
        c = c.replace("%player%", player.getName());
        player.performCommand(c);
    }

    /**
     * Creates a new CommandAction. You can use %player% to get the player who clicked a button's name.
     * @param command The command to be executed.
     */
    public CommandAction(String command) {
        this.command = command;
    }
}
