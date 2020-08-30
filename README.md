# Gooey
 A simple and easy to use GUI creation tool for the Spigot API.
 
 ## Why should I use Gooey?
 The answer is simple. **Gooey** is very easy to use and is extremely flexible.
 With **Gooey** you can create custom GUIs, clickable items (buttons), and custom actions for the buttons you create.
 
 ## New In Version 1.1
 
 - **Paginated GUIs!**
 - Custom GUI update methods.
 - Easier way to add lore to Buttons.
 - GUI#addButton method.
 - Instance IDs, allows you to have theoretically unlimited instances of **Gooey** on your server.
 - Bug fixes.
 
 ## How to use
 
 - Add the dependency to your project. You *must* shade **Gooey** into your project's jar.
 
 ```
Public maven repository coming soon...
 ```
 
 - When starting your plugin, initialize **Gooey**.
 
 **Example**
 
 ```java
public class Example extends JavaPlugin {
    @Override
    public void onEnable() {
        Gooey gooey = new Gooey(this);
    }
}
 ```

 - Then after initialization, you can create your buttons and GUI of choice.
 
 **Example**
 
 ```java
Player player; // Get the player somehow.
GUI gui = new GUI("A Gooey GUI", 9); // Initialize the GUI.

Button button1 = new Button(Material.DIAMOND_SWORD, 1, "&cButton 1");
button1.setMoveable(true); // Button now moveable in the inventory.

Button button2 = new Button(Material.WORKBENCH, 1, "&6Button 2");
button2.setAction(new CommandAction("help", ClickType.LEFT)); // Button now executes the command "/help" on click.
button2.setCloseOnClick(true); // Closes the GUI when this button is clicked.

button2.setLore("&7Click to do /help.", "&cHere is another line."); // Adds lore to the button.

gui.setButton(0, button1); // Sets button at position 0 in the GUI.
gui.addButton(button2); // Adds a button to the GUI. Since a position number was not specified, it will go to the next available position.

gui.open(player) // Opens the GUI for the player.
 ```

- Your **Gooey** GUI should now be functional.

![GUI Example]("https://image.prntscr.com/image/J6JhuX94QXyPupWDTs60rQ.png")

## Custom Actions

Creating custom actions are actually quite simple.

- Create the custom action.

**Example**
```java
public class ExampleAction implements Action {

    private String command;

    @Override
    public void run(Player player, GUI gui, Button button, ClickType clickType) {
        if(clickType == ClickType.RIGHT) { // Checks if the click was a right click.
            player.performCommand(this.command); // Executes the command specified in the constructor's "command" parameter.
        }
    }
   
    public ExampleAction(String command) {
        this.command = command;
    }
}
```

- Use it!

**Example**
```java
Button button; // The button you want to attach the action to.
button.setAction(new ExampleAction("gamemode creative")); // Set the action.

// Or if you want a button to have a unique action, you can do something like this.
button.setAction(new Action() {
            @Override
            public void run(Player player, GUI gui, Button button, ClickType clickType) {
                player.sendMessage(ChatColor.GREEN + "Test!");
            }
        });
```

## GUI Updates

For your GUI, you can set custom update methods for a more dynamic GUI.

```java
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
```


