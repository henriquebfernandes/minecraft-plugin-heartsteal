package com.github.henriquebfernandes.heartSteal.manager;

import com.github.henriquebfernandes.heartSteal.config.HeartStealConfig;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class HealthManager {
    private final HeartStealConfig config;

    public HealthManager(HeartStealConfig config) {
        this.config = config;
    }

    public void punish(Player player){
        var maxHealth = player.getAttribute(Attribute.MAX_HEALTH);
        assert maxHealth != null;
        maxHealth.setBaseValue(Math.max(maxHealth.getBaseValue() - config.deathPunishment, config.minHealthPoints));
    }

    public void reward(Player player){
        var maxHealth = player.getAttribute(Attribute.MAX_HEALTH);
        assert maxHealth != null;
        maxHealth.setBaseValue(Math.min(maxHealth.getBaseValue() + config.killReward, config.maxHealthPoints));
    }
}
