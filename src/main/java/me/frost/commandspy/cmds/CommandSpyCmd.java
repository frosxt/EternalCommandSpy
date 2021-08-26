package me.frost.commandspy.cmds;

import me.frost.commandspy.CommandSpy;
import me.frost.commandspy.managers.CommandSpyManager;
import me.frost.commandspy.utils.Formatting;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpyCmd implements CommandExecutor {
    private final CommandSpy plugin;
    private final CommandSpyManager manager;

    public CommandSpyCmd(CommandSpy plugin, CommandSpyManager manager) {
        this.plugin = plugin;
        this.manager = manager;
        plugin.getCommand("commandspy").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info("Only players can execute that command!");
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("ecommandspy.use")) {
            sender.sendMessage(Formatting.colorize(plugin.getConfig().getString("no-permission")));
            return false;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                plugin.reloadConfig();
                player.sendMessage(Formatting.colorize("&a&l(!) &aSuccessfully reloaded the configuration files!"));
            }
            return false;
        }
        if (manager.isToggled(player)) {
            manager.removePlayer(player);
            sender.sendMessage(Formatting.colorize(plugin.getConfig().getString("commandspy-disabled")));
        } else {
            manager.addPlayer(player);
            sender.sendMessage(Formatting.colorize(plugin.getConfig().getString("commandspy-enabled")));
        }
        return false;
    }
}