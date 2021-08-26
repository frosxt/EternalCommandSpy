package me.frost.commandspy.managers;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CommandSpyManager {
    private final Set<UUID> commandSpy = new HashSet<>();

    public void addPlayer(Player player) {
        commandSpy.add(player.getUniqueId());
    }

    public void removePlayer(Player player) {
        commandSpy.remove(player.getUniqueId());
    }

    public boolean isToggled(Player player) {
        return commandSpy.contains(player.getUniqueId());
    }
}
