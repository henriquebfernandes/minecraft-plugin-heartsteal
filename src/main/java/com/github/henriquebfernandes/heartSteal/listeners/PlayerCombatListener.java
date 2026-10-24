package com.github.henriquebfernandes.heartSteal.listeners;

import com.github.henriquebfernandes.heartSteal.managers.HealthManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerCombatListener implements Listener {
    private final HealthManager healthManager;
    public PlayerCombatListener(HealthManager healthManager) {
        this.healthManager = healthManager;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player victim = e.getEntity();
        healthManager.punish(victim);

        Player killer = victim.getKiller();
        if (killer != null) {
            healthManager.reward(killer);
        }
    }
}
