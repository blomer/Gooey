package xyz.leuo.gooey.action;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import xyz.leuo.gooey.button.Button;
import xyz.leuo.gooey.gui.GUI;

@Data
public class CommandAction implements Action{

    private String command;
    private ClickType clickType;

    @Override
    public void run(Player player, GUI gui, Button button, ClickType clickType) {

        if(this.clickType != null) {
            if(clickType != this.clickType) {
                return;
            }
        }

        PlayerCommandPreprocessEvent playerCommandPreprocessEvent = new PlayerCommandPreprocessEvent(player, command);
        Bukkit.getPluginManager().callEvent(playerCommandPreprocessEvent);

        if(!playerCommandPreprocessEvent.isCancelled()) {
            String c = this.command;
            c = c.replace("%player%", player.getName());
            player.performCommand(c);
        }
    }

    /**
     * Creates a new CommandAction. You can use %player% to get the player who clicked a button's name.
     * @param command The command to be executed.
     * @param clickType The type of click that would execute the command. Set to null for any type of click.
     */
    public CommandAction(String command, ClickType clickType) {
        this.command = command;
        this.clickType = clickType;
    }
}
