package me.frost.commandspy;

import me.frost.commandspy.cmds.CommandSpyCmd;
import me.frost.commandspy.events.CommandSpyEvent;
import me.frost.commandspy.utils.Formatting;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class CommandSpy extends JavaPlugin {
    public Set<UUID> commandSpy = new HashSet<>();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(Formatting.colorize("[EternalCommandSpy] Enabling plugin..."));
        getServer().getPluginManager().registerEvents(new CommandSpyEvent(this), this);
        getCommand("commandspy").setExecutor(new CommandSpyCmd(this));
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(Formatting.colorize("[EternalCommandSpy] Enabled successfully!"));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Formatting.colorize("[EternalCommandSpy] Disabling plugin..."));
        Bukkit.getConsoleSender().sendMessage(Formatting.colorize("[EternalCommandSpy] Disabled successfully!"));
    }
}
