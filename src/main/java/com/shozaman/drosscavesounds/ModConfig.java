package com.shozaman.drosscavesounds;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModConfig {

    public int averageMinutes = 5;
    public int yCaveLevel = 40;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "drosscavesounds.json");

    public static ModConfig loadConfig(){
        ModConfig config = null;

        if(CONFIG_FILE.exists()){
            try(FileReader reader = new FileReader(CONFIG_FILE)){
                config = GSON.fromJson(reader, ModConfig.class);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        if(config == null){
            config = new ModConfig();
            config.saveConfig();
        }

        return config;
    }


    public void saveConfig(){
        try(FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(this, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
