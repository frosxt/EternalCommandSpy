package me.frost.commandspy.cmds;

import me.frost.commandspy.CommandSpy;
import me.frost.commandspy.utils.Formatting;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpyCmd implements CommandExecutor {
    private CommandSpy plugin;

    public CommandSpyCmd(CommandSpy plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info("Only players can execute that command!");
            return false;
        } else {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                    plugin.reloadConfig();
                    player.sendMessage(Formatting.colorize("&a&l(!) &aSuccessfully reloaded the configuration files!"));
                }
                return false;
            }
            if (!player.hasPermission("ecommandspy.use")) {
                sender.sendMessage(Formatting.colorize(plugin.getConfig().getString("no-permission")));
                return false;
            }
            if (plugin.commandSpy.contains(player.getUniqueId())) {
                plugin.commandSpy.remove(player.getUniqueId());
                sender.sendMessage(Formatting.colorize(plugin.getConfig().getString("commandspy-disabled")));
            } else {
                plugin.commandSpy.add(player.getUniqueId());
                sender.sendMessage(Formatting.colorize(plugin.getConfig().getString("commandspy-enabled")));
            }
            return false;
        }
    }
}
