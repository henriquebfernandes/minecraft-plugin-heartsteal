package com.github.henriquebfernandes.heartSteal.managers;

import com.github.henriquebfernandes.heartSteal.config.HeartStealConfig;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class HealthManager {
    private final HeartStealConfig heartStealConfig;

    public HealthManager(HeartStealConfig heartStealConfig) {
        this.heartStealConfig = heartStealConfig;
    }

    public int punish(Player player) {
        var maxHealth = getMaxHealth(player);
        int value = Math.max(
                (int) maxHealth.getBaseValue()
                        - heartStealConfig.deathPunishment,
                heartStealConfig.minHP
        );
        maxHealth.setBaseValue(value);
        return value;
    }

    public int reward(Player player) {
        var maxHealth = getMaxHealth(player);
        int value = Math.min(
                (int) maxHealth.getBaseValue()
                        + heartStealConfig.killReward,
                heartStealConfig.maxHP
        );
        maxHealth.setBaseValue(value);
        return value;
    }

    public int set(Player player, int amount) {
        var maxHealth = getMaxHealth(player);
        int value = Math.clamp(
                amount,
                heartStealConfig.maxHP,
                heartStealConfig.minHP
        );
        maxHealth.setBaseValue(value);
        return value;
    }

    public int reset(Player player) {
        var maxHealth = getMaxHealth(player);
        int value = heartStealConfig.initialHP;
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
