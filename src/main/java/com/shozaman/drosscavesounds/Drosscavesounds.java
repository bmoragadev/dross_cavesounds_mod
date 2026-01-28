package com.shozaman.drosscavesounds;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.sun.jdi.connect.Connector;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;

public class Drosscavesounds implements ModInitializer {

    public static final String MOD_ID = "drosscavesounds";
    public static final Identifier SOUND_ID = Identifier.of(MOD_ID, "dross_cavesounds");
    public static final SoundEvent SOUND_EVENT = SoundEvent.of(SOUND_ID);

    public static ModConfig CONFIG;

    private static float currentSoundProbability;

    @Override
    public void onInitialize() {

        CONFIG = ModConfig.loadConfig();
        System.out.println("DrossCaveSounds: Config Loaded.");

        recalculateProbability();

        Registry.register(Registries.SOUND_EVENT, SOUND_ID, SOUND_EVENT);
        System.out.println("DrossCaveSounds: Loaded Successfully!");

        registerCommands();



        ServerTickEvents.END_SERVER_TICK.register(server -> {


            for(ServerPlayerEntity player : server.getPlayerManager().getPlayerList()){

                if(player.getRandom().nextFloat() > currentSoundProbability){
                    continue;
                }

                if(player.getY() > CONFIG.yCaveLevel){
                    continue;
                }

                BlockPos pos = player.getBlockPos();

                int blockLight = player.getWorld().getLightLevel(LightType.BLOCK, pos);
                int skyLight = player.getWorld().getLightLevel(LightType.SKY, pos);

                if(blockLight < CONFIG.maxBlockLight && skyLight == 0){

                    int radius = CONFIG.soundRadius;

                    int offsetX = player.getRandom().nextInt(radius * 2 + 1) - radius;
                    int offsetY = player.getRandom().nextInt(radius * 2 + 1) - radius;
                    int offsetZ = player.getRandom().nextInt(radius * 2 + 1) - radius;

                    BlockPos randomPos = pos.add(offsetX, offsetY, offsetZ);

                    player.getWorld().playSound(
                            null,
                            randomPos,
                            SOUND_EVENT,
                            SoundCategory.AMBIENT,
                            CONFIG.soundVolume,
                            1.0f
                    );
                }
            }
        });
    }

    public static void recalculateProbability(){
        int minutes = Math.max(1, CONFIG.averageMinutes);
        currentSoundProbability = 1.0f / (minutes * 60 * 20);
    }

    private void registerCommands(){
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {

            dispatcher.register(CommandManager.literal("drosscavesounds")
                    .requires(source -> source.hasPermissionLevel(2))

                    // Command /drosscavesounds info
                    .then(CommandManager.literal("info")
                            .executes(context -> {
                              String msg = String.format("§6[DrossCaveSounds]§r\nAverage Minutes: §e%d§r\nY Level: §e%d§r\nSound Spawn Radius: §e%d§r\nVolume: §e%.1f§r\nMax Light: §e%d§r",
                                      CONFIG.averageMinutes, CONFIG.yCaveLevel, CONFIG.soundRadius, CONFIG.soundVolume, CONFIG.maxBlockLight);
                              context.getSource().sendFeedback(()-> Text.literal(msg), false);
                              return 1;
                            }))

                    .then(CommandManager.literal("set")
                        // Command /drosscavesounds set minutes <number>
                        .then(CommandManager.literal("minutes")
                                .then(CommandManager.argument("value", IntegerArgumentType.integer(1))
                                        .executes(context -> {
                                            int val = IntegerArgumentType.getInteger(context, "value");
                                            CONFIG.averageMinutes = val;
                                            CONFIG.saveConfig();
                                            recalculateProbability();
                                            context.getSource().sendFeedback(() -> Text.literal("§a[DrossCaveSounds] Average Minutes changed to: " + val), true);
                                            return 1;
                                        }))))
                        // Command /drosscavesounds set ylevel <number>
                        .then(CommandManager.literal("ylevel")
                                .then(CommandManager.argument("value", IntegerArgumentType.integer(-64, 320))
                                        .executes(context -> {
                                            int val = IntegerArgumentType.getInteger(context, "value");
                                            CONFIG.yCaveLevel = val;
                                            CONFIG.saveConfig();
                                            context.getSource().sendFeedback(() -> Text.literal("§a[DrossCaveSounds] YLevel changed to: " + val), true);
                                            return 1;
                                        })))
                        // Command /drosscavesounds set sound radius <number>
                        .then(CommandManager.literal("radius")
                                .then(CommandManager.argument("value", IntegerArgumentType.integer(1, 100))
                                        .executes(context -> {
                                            int val = IntegerArgumentType.getInteger(context, "value");
                                            CONFIG.soundRadius = val;
                                            CONFIG.saveConfig();
                                            context.getSource().sendFeedback(() -> Text.literal("§a[DrossCaveSounds] SoundRadius changed to: " + val), true);
                                            return 1;
                                        })))
                        // Command /drosscavesounds set volume <decimal>
                        .then(CommandManager.literal("volume")
                                .then(CommandManager.argument("value", FloatArgumentType.floatArg(0.0f, 10.0f))
                                        .executes(context -> {
                                            float val = FloatArgumentType.getFloat(context, "value");
                                            CONFIG.soundVolume = val;
                                            CONFIG.saveConfig();
                                            context.getSource().sendFeedback(() -> Text.literal("§a[DrossCaveSounds] Volume changed to: " + val), true);
                                            return 1;
                                        })))
                        // Command /drosscavesounds set light <0-15>
                        .then(CommandManager.literal("light")
                                .then(CommandManager.argument("value", IntegerArgumentType.integer(0, 15)) // Limitamos entre 0 y 15
                                        .executes(context -> {
                                            int val = IntegerArgumentType.getInteger(context, "value");
                                            CONFIG.maxBlockLight = val;
                                            CONFIG.saveConfig();
                                            context.getSource().sendFeedback(() -> Text.literal("§a[DrossCaveSounds] Max Light changed to: " + val), true);
                                            return 1;
                                        })))
            );

        });
    }
}
