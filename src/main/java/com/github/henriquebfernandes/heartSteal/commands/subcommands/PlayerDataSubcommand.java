package com.github.henriquebfernandes.heartSteal.commands.subcommands;

import com.github.henriquebfernandes.heartSteal.data.PlayerData;
import com.github.henriquebfernandes.heartSteal.utils.CommandUtils;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

public class PlayerDataSubcommand {

    public static LiteralArgumentBuilder<CommandSourceStack> create(PlayerData playerData
    ) {
        var command = Commands.literal("playerdata");

        var setCommand = Commands.literal("set");
        var getCommand = Commands.literal("get");
        var resetCommand = Commands.literal("reset");

        playerData.keys.keySet().forEach(key -> {
            setCommand.then(
                    Commands.literal(key)
                            .then(Commands.argument(
                                    "amount",
                                    IntegerArgumentType.integer()
                            ).executes(context -> {
                                int amount = IntegerArgumentType.getInteger(
                                        context, "amount"
                                );

                                return CommandUtils.executeAndSendResult(
                                        context,
                                        player -> playerData.set(player, key, amount),
                                        key + " set successfully.\nCurrent value: ",
                                        "default"
                                );
                            }))
            );

            getCommand.then(
                    Commands.literal(key)
                            .executes(context ->
                                    CommandUtils.executeAndSendResult(
                                            context,
                                            player -> playerData.getOrDefault(player, key),
                                            key + " fetched successfully.\nCurrent value: ",
                                            "default"
                                    )
                            )
            );

            resetCommand.then(
                    Commands.literal(key)
                            .executes(context ->
                                    CommandUtils.executeAndSendResult(
                                            context,
                                            player -> playerData.reset(player, key),
                                            key + " reset successfully.\nCurrent value: ",
                                            "default"
                                    )
                            )
            );
        });

        return command
                .then(setCommand)
                .then(getCommand)
                .then(resetCommand);
    }
}