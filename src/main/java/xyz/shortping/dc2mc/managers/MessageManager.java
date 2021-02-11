package xyz.shortping.dc2mc.managers;

import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.shortping.dc2mc.DC2MC;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * Copyright © Michael Steinmötzger 2021 All Rights Reserved
 */
public class MessageManager {

    private final DC2MC plugin;

    private File file;
    private YamlConfiguration cfg;

    public MessageManager(DC2MC plugin) {
        this.plugin = plugin;
        this.file = new File("plugins/DC2MC/messages.yml");
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

    public void setMessage(String key, String message) {
        this.cfg.addDefault("Message." + key, message);
        this.save();
    }

    public String getMessage(String key, Object... args) {
        return ChatColor.translateAlternateColorCodes('&', MessageFormat.format(this.cfg.getString("Message."+key), args));
    }

}
