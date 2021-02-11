package xyz.shortping.dc2mc.managers;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import xyz.shortping.dc2mc.DC2MC;

/**
 * Copyright © Michael Steinmötzger 2021 All Rights Reserved
 */
@AllArgsConstructor
public class DiscordManager {

    private final DC2MC plugin;

    public MessageEmbed noPermissionEmbed() {
        return new EmbedBuilder()
                .setTitle(plugin.getMessageManager().getMessage("Discord.Embed.NoPermission.Title"))
                .setDescription(plugin.getMessageManager().getMessage("Discord.Embed.NoPermission.Message"))
                .setColor(Integer.parseInt(plugin.getMessageManager().getMessage("Discord.Embed.NoPermission.Color").replaceAll("#", ""), 16))
                .build();
    }

    public MessageEmbed getInvalidArgs(String commandName) {
        return new EmbedBuilder()
                .setTitle(plugin.getMessageManager().getMessage("Discord.Embed.InvalidArgs.Title"))
                .setDescription(plugin.getMessageManager().getMessage("Discord.Embed.InvalidArgs.Message", plugin.getMessageManager().getMessage("Discord.Usages." + commandName)))
                .setColor(Integer.parseInt(plugin.getMessageManager().getMessage("Discord.Embed.InvalidArgs.Color").replaceAll("#", ""), 16))
                .build();
    }
}
