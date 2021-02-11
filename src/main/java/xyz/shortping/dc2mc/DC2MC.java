package xyz.shortping.dc2mc;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.shortping.dc2mc.events.BotEvents;
import xyz.shortping.dc2mc.managers.ConfigManager;
import xyz.shortping.dc2mc.managers.DiscordManager;
import xyz.shortping.dc2mc.managers.MessageManager;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

/**
 * Copyright © Michael Steinmötzger 2021 All Rights Reserved
 */

public class DC2MC extends JavaPlugin {
    @Getter
    public ConfigManager configManager;
    @Getter
    public MessageManager messageManager;
    @Getter
    public JDA jda;
    @Getter
    public DiscordManager discordManager;
    public String prefix, consolePrefix;

    @Override
    public void onEnable() {
        System.out.println("§aDC2MC started... Developed by ShortPing // https://shortping.xyz");
        init();

        try {
            this.jda = JDABuilder.createDefault(configManager.getCfg().getString("Bot-Token")).addEventListeners(new BotEvents(this)).build();

        } catch (LoginException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§4This Error may happen when you haven't configured your Bot-Token");
        }
    }

    public void init() {
        /*
        -- Config-Initialization --
         */
        this.configManager = new ConfigManager(this);
        this.configManager.getCfg().addDefault("Bot-Token", "############");
        this.configManager.getCfg().addDefault("Presence.Type", "###");
        this.configManager.getCfg().addDefault("Presence.Text", "###");
        this.configManager.getCfg().addDefault("Role.Admin", "####");
        this.configManager.save();
        /*
        -- Config-Initialization --
         */

        /*
        -- Command-Initialization --
         */

        /*
        -- Command-Initialization --
         */

        /*
        -- Event-Initialization --
         */

        /*
        -- Event-Initialization --
         */

        /*
        -- Message-Initialization --
         */
        this.messageManager = new MessageManager(this);
        this.messageManager.setMessage("Prefixes.Console", "[DC2MC] ");
        this.messageManager.setMessage("Prefixes.Minecraft", "&bDC&62&aMC &7» ");
        this.messageManager.setMessage("Player.NoPermission", "&cYou do not have enough permissions to use this command.");
        this.messageManager.setMessage("Player.NotOnline", "&cThe Player {0} is not online.");
        this.messageManager.setMessage("Discord.Embed.NoPermission.Title", "Error");
        this.messageManager.setMessage("Discord.Embed.NoPermission.Message", "You do not have enough permissions to use this command.");
        this.messageManager.setMessage("Discord.Embed.NoPermission.Color", "#ed0c0c");
        this.messageManager.setMessage("Discord.Embed.InvalidArgs.Title", "Error");
        this.messageManager.setMessage("Discord.Embed.InvalidArgs.Message", "Please use {0}.");
        this.messageManager.setMessage("Discord.Embed.InvalidArgs.Color", "#ed0c0c");
        this.messageManager.setMessage("Discord.Usages.SendMC", "!sendmc <Message>");


        /*
        -- Message-Initialization --
         */

        this.prefix = this.messageManager.getMessage("Prefixes.Minecraft");
        this.consolePrefix = this.messageManager.getMessage("Prefixes.Console");

        this.discordManager = new DiscordManager(this);
    }

    @Override
    public void onDisable() {
        System.out.println("§cDC2MC stopped... Developed by ShortPing // https://shortping.xyz");
    }
}
