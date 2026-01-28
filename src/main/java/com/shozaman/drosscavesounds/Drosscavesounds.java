package com.shozaman.drosscavesounds;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;

public class Drosscavesounds implements ModInitializer {

    public static final String MOD_ID = "drosscavesounds";
    public static final Identifier SOUND_ID = Identifier.of(MOD_ID, "dross_cavesounds");
    public static final SoundEvent SOUND_EVENT = SoundEvent.of(SOUND_ID);

    //OLD CONSTS
    //public static final int AVERAGE_MINUTES = 5;
    //public static final float SOUND_PROBABILITY = 1.0f / (AVERAGE_MINUTES * 60 * 20);
    //public static final int Y_CAVE_LEVEL = 40;

    public static ModConfig CONFIG;

    @Override
    public void onInitialize() {

        CONFIG = ModConfig.loadConfig();
        System.out.println("DrossCaveSounds: Config Loaded.");
        System.out.println("Average Minutes: " + CONFIG.averageMinutes);
        System.out.println("Y Cave Level: "+CONFIG.yCaveLevel);
        Registry.register(Registries.SOUND_EVENT, SOUND_ID, SOUND_EVENT);
        System.out.println("DrossCaveSounds: Loaded Succesfully!");

        float soundProbability = 1.0f / (Math.max(1, CONFIG.averageMinutes) * 60 * 20);

        ServerTickEvents.END_SERVER_TICK.register(server -> {


            for(ServerPlayerEntity player : server.getPlayerManager().getPlayerList()){

                if(player.getRandom().nextFloat() > soundProbability){
                    continue;
                }

                if(player.getY() > CONFIG.yCaveLevel){
                    continue;
                }

                BlockPos pos = player.getBlockPos();

                int blockLight = player.getWorld().getLightLevel(LightType.BLOCK, pos);
                int skyLight = player.getWorld().getLightLevel(LightType.SKY, pos);

                if(blockLight < 6 && skyLight == 0){

                    player.getWorld().playSound(
                            null,
                            pos,
                            SOUND_EVENT,
                            SoundCategory.AMBIENT,
                            1.0f,
                            1.0f
                    );
                }
            }
        });
    }
}
