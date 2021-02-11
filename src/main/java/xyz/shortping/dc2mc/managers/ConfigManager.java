package xyz.shortping.dc2mc.managers;

import lombok.AllArgsConstructor;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.shortping.dc2mc.DC2MC;

import java.io.File;
import java.io.IOException;

/**
 * Copyright © Michael Steinmötzger 2021 All Rights Reserved
 */
public class ConfigManager {

    private final DC2MC plugin;

    private File file;
    private YamlConfiguration cfg;

    public ConfigManager(DC2MC plugin) {
        this.plugin = plugin;
        this.file = new File("plugins/DC2MC/config.yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getCfg() {
        return cfg;
    }

    public void save() {
        cfg.options().copyDefaults(true);
        try {
            this.cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
