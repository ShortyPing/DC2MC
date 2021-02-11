package xyz.shortping.dc2mc.events;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import xyz.shortping.dc2mc.DC2MC;

import java.util.concurrent.TimeUnit;

/**
 * Copyright © Michael Steinmötzger 2021 All Rights Reserved
 */
@AllArgsConstructor()
public class BotEvents extends ListenerAdapter {

    private final DC2MC plugin;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();

        String[] args = msg.getContentRaw().split(" ");
        if(msg.getContentRaw().toLowerCase().startsWith("!sendmc")) {
            msg.delete().queue();
            if(event.getMember().getRoles().contains(plugin.getJda().getRoleById(plugin.getConfigManager().getCfg().getString("Role.Admin")))) {
                if(args.length >= 2) {
                    String r = "";
                    for(int i = 1; i < args.length; i++) {
                        r = args[i] + " ";

                    }
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', r));
                } else {
                    event.getMessage().getChannel().sendMessage(plugin.getDiscordManager().getInvalidArgs("SendMC")).queue(message -> {
                        message.delete().queueAfter(5, TimeUnit.SECONDS);
                    });
                }
            } else {
                event.getMessage().getChannel().sendMessage(plugin.getDiscordManager().noPermissionEmbed()).queue(message -> {
                    message.delete().queueAfter(5, TimeUnit.SECONDS);
                });
            }
        }
    }
}
