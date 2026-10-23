package com.github.henriquebfernandes.heartSteal;

import com.github.henriquebfernandes.heartSteal.config.HeartStealConfig;
import com.github.henriquebfernandes.heartSteal.manager.HealthManager;
import com.github.henriquebfernandes.heartSteal.listeners.PlayerCombatListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeartStealPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getLogger().info("[HeartSteal] Config loaded");

        HeartStealConfig config = new HeartStealConfig(this);
        HealthManager healthManager = new HealthManager(config);
        getServer().getPluginManager().registerEvents(new PlayerCombatListener(healthManager),this);

        getLogger().info("[HeartSteal] Enabling HeartSteal");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[HeartSteal] Disabling HeartSteal");
    }
}
