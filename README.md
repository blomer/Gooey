# Gooey
 A simple and easy to use GUI creation tool for the Spigot API.
 
 ## Why should I use Gooey?
 The answer is simple. **Gooey** is very easy to use and is extremely flexible.
 With **Gooey** you can create custom GUIs, clickable items (buttons), and custom actions for the buttons you create.
 
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
button2.setAction(new CommandAction("help")); // Button now executes the command "/help" on click.

List<String> lore = button2.getLore(); // Gets button lore.
lore.add("&7Click to do /help."); // Adds lore to the button.

gui.addButton(0, button1); // Sets button at position 0 in the GUI.
gui.addButton(1, button2); // Sets button at position 1 in the GUI.

gui.open(player) // Opens the GUI for the player.
player.openInventory(gui.getInventory()) // OPTIONAL: Does the same thing as the line before.
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
            player.performCommand(c); // Executes the command specified in the constructor's "command" parameter.
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
```
