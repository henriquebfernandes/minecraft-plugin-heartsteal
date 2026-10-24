package com.github.henriquebfernandes.heartSteal.utils;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.function.Consumer;
import java.util.function.Function;

public final class CommandUtils {
    public static Player getPlayer(CommandSourceStack source
    ) {
        if (!(source.getExecutor() instanceof Player player)) {
            source.getSender().sendMessage(Component.text(
                    "Only a player can use this command!",
                    NamedTextColor.RED
            ));
            return null;
        }
        return player;
    }

    public static int executeAndSendResult(
            CommandContext<CommandSourceStack> context,
            Function<Player, ?> operation,
            String label,
            String replaceNull
    ) {
        var source = context.getSource();
        var player = CommandUtils.getPlayer(source);

        if (player == null) {
            return 0; // Failure
        }

        var value = operation.apply(player);
        if (value == null) {
            value = replaceNull;
        }

        source.getSender().sendMessage(
                Component.text(label)
                        .append(Component.text(value.toString()))
                        .color(NamedTextColor.GREEN)
        );
        return 1;
    }
}