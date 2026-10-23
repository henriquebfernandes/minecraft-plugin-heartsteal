package com.github.henriquebfernandes.heartSteal.config;

import com.github.henriquebfernandes.heartSteal.HeartStealPlugin;

public final class HeartStealConfig {
    public final int killReward;
    public final int deathPunishment;
    public final int maxHealthPoints;
    public final int minHealthPoints;

    public HeartStealConfig(HeartStealPlugin plugin) {
        var config = plugin.getConfig();
        this.killReward = config.getInt("settings.kill-reward");
        this.deathPunishment = config.getInt("settings.death-punishment");
        this.maxHealthPoints = config.getInt("settings.max-health-points");
        this.minHealthPoints = config.getInt("settings.min-health-points");
    }
}