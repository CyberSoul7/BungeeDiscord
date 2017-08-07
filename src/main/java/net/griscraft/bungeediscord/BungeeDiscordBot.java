package net.griscraft.bungeediscord;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeDiscordBot implements EventListener {
	
	BungeeDiscord plugin;
	
	public BungeeDiscordBot(BungeeDiscord plugin) {
		this.plugin = plugin;
	}

	public void onEvent(Event event) {
		
		if (event instanceof ReadyEvent) {
			plugin.getLogger().info("API is ready!");
		}
		
		if (event instanceof MessageReceivedEvent) {
			
			MessageReceivedEvent e = (MessageReceivedEvent) event;
			
			String user = e.getAuthor().getName();
			String channel = e.getTextChannel().getName();
			
			if (!channel.equalsIgnoreCase("minecraft")) return;
			if (e.getAuthor().isBot()) return;
			
			String message = "#minecraft <" + user + "> " + e.getMessage().getContent();
			
			for (ProxiedPlayer player : plugin.getProxy().getPlayers()) {
				player.sendMessage(new TextComponent(message));
			}
			
		}
		
	}
	
}
