package me.frost.commandspy;

import me.frost.commandspy.cmds.CommandSpyCmd;
import me.frost.commandspy.listeners.CommandSpyListener;
import me.frost.commandspy.managers.CommandSpyManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandSpy extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandSpyManager manager = new CommandSpyManager();
        System.out.println("[EternalCommandSpy] Enabling plugin...");
        new CommandSpyListener(this, manager);
        new CommandSpyCmd(this, manager);
        saveDefaultConfig();
        System.out.println("[EternalCommandSpy] Enabled successfully!");
    }
}