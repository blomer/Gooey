# Gooey
 A simple and easy to use GUI creation tool for the Spigot API.
 
 ## Why should I use Gooey?
 The answer is simple. **Gooey** is very easy to use and is extremely flexible.
 With **Gooey** you can create custom GUIs, clickable items (buttons), and custom actions for the buttons you create.
 
 ## New in Version 1.2
 
 - **Global Compatibility!** You can now use this version of Gooey on any Bukkit/Spigot API version!
 - GUICloseUpdate, executes one tick after a GUI was closed.
 - Other smaller additions and bug fixes.
 
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
button1.setButtonAction(new ButtonAction() {
    @Override
    public void run(Player player, GUI gui, Button button, InventoryClickEvent inventoryClickEvent) {
        player.sendMessage("Hello!");
    }
});

Button button2 = new Button(Material.WORKBENCH, 1, "&6Button 2");
button2.setCloseOnClick(true); // Closes the GUI when this button is clicked.

button2.setLore("&7Click to do /help.", "&cHere is another line."); // Adds lore to the button.

gui.setButton(0, button1); // Sets button at position 0 in the GUI.
gui.addButton(button2); // Adds a button to the GUI. Since a position number was not specified, it will go to the next available position.

gui.open(player) // Opens the GUI for the player.
 ```

- Your **Gooey** GUI should now be functional.

![GUI Example]("https://image.prntscr.com/image/J6JhuX94QXyPupWDTs60rQ.png")

## GUI Updates

GUIs can update every 20 ticks if you set a GUIUpdate for your GUI. These can be used for many things.

**Example**
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


