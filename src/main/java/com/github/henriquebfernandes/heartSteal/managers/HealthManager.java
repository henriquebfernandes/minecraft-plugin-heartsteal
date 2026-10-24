
package com.github.henriquebfernandes.heartSteal.managers;

import com.github.henriquebfernandes.heartSteal.data.PlayerData;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class HealthManager {
    private final PlayerData playerData;

    public HealthManager(PlayerData playerData) {
        this.playerData = playerData;
    }

    public int punish(Player player) {
        var maxHealth = getMaxHealth(player);
        int value = Math.max(
                (int) maxHealth.getBaseValue()
                        - playerData.getOrDefault(player, "death_punishment"),
                playerData.getOrDefault(player, "min_health_points")
        );
        maxHealth.setBaseValue(value);
        return value;
    }

    public int reward(Player player) {
        var maxHealth = getMaxHealth(player);
        int value = Math.min(
                (int) maxHealth.getBaseValue()
                        + playerData.getOrDefault(player, "kill_reward"),
                playerData.getOrDefault(player, "max_health_points")
        );
        maxHealth.setBaseValue(value);
        return value;
    }

    public int set(Player player, int amount) {
        var maxHealth = getMaxHealth(player);
        int value = Math.clamp(
                amount,
                playerData.getOrDefault(player, "min_health_points"),
                playerData.getOrDefault(player, "max_health_points")
        );
        maxHealth.setBaseValue(value);
        return value;
    }

    public int reset(Player player) {
        var maxHealth = getMaxHealth(player);
        int value = playerData.getOrDefault(player, "initial_health_points");
        maxHealth.setBaseValue(value);
        return value;
    }

    public int get(Player player) {
        var maxHealth = getMaxHealth(player);
        return (int) maxHealth.getBaseValue();
    }

    private AttributeInstance getMaxHealth(Player player) {
        var attribute = player.getAttribute(Attribute.MAX_HEALTH);
        if (attribute == null) {
            throw new IllegalStateException(
                    "MAX_HEALTH attribute is unavailable for " + player.getName()
            );
        }
        return attribute;
    }
}
