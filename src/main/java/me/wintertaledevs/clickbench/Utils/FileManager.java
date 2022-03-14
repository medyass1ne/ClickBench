package me.wintertaledevs.clickbench.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    File path;
    String fileName;
    File rawFile;
    FileConfiguration file;

    public FileManager(File path, String file) {
        this.path = path;
        this.fileName = file;
        rawFile = new File(path, file);
        if(!rawFile.exists()) {
            try{
                boolean isCreated = rawFile.createNewFile();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.file = YamlConfiguration.loadConfiguration(rawFile);
        this.file.options().copyDefaults(true);
        boolean isSaved = this.Save();
    }

    public FileConfiguration GetConfig() {
        return file;
    }

    public boolean Save() {
        try{
            file.save(rawFile);
            return true;
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void Reload() {
        rawFile = new File(path, fileName);
        if(!rawFile.exists()) {
            try{
                boolean isCreated = rawFile.createNewFile();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.file = YamlConfiguration.loadConfiguration(rawFile);
        this.file.options().copyDefaults(true);
        boolean isSaved = this.Save();
    }
}
