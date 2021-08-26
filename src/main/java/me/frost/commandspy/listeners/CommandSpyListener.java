package me.frost.commandspy.listeners;

import me.frost.commandspy.CommandSpy;
import me.frost.commandspy.managers.CommandSpyManager;
import me.frost.commandspy.utils.Formatting;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandSpyListener implements Listener {
    private final CommandSpy plugin;
    private final CommandSpyManager manager;

    public CommandSpyListener(CommandSpy plugin, CommandSpyManager manager) {
        this.plugin = plugin;
        this.manager = manager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Bukkit.getServer().getOnlinePlayers().stream()
                .filter(manager::isToggled)
                .filter(player -> player.hasPermission("ecommandspy.use"))
                .forEach(player -> player.sendMessage(Formatting.colorize(plugin.getConfig().getString("commandspy-format")
                        .replace("%player%", event.getPlayer().getName())
                        .replace("%command%", event.getMessage()))));
    }
}