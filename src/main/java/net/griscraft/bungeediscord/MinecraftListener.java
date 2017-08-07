package net.griscraft.bungeediscord;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MinecraftListener implements Listener {
	
	BungeeDiscord plugin;
	
	public MinecraftListener(BungeeDiscord plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onChat(ChatEvent e) {
		if (e.isCommand()) return;
		
		String message = e.getMessage();
		
		if (!(e.getSender() instanceof ProxiedPlayer)) return;
		ProxiedPlayer player = (ProxiedPlayer) e.getSender();
		
		String server = player.getServer().getInfo().getName();
		
		String discordMessage = "MC » " + server + " » " + player.getDisplayName() + " » " + message;
		plugin.sendDiscordMessage(343606500713037826L, discordMessage);
		
	}
	
	@EventHandler
	public void onJoin(ServerConnectEvent e) {
		String player = e.getPlayer().getDisplayName();
		if (e.getPlayer().getServer() == null) {
			String server = e.getTarget().getName();
			String message = "MC » " + player + " has joined the network on the " + server + " server!";
			plugin.sendDiscordMessage(343606500713037826L, message);
			return;
		}
		
		String toServer = e.getTarget().getName();
		String fromServer = e.getPlayer().getServer().getInfo().getName();
		
		String message = "MC » " + player + " switched to " + toServer + " from " + fromServer + "!";
		plugin.sendDiscordMessage(343606500713037826L, message);
		
	}
	
	@EventHandler
	public void onLeave(PlayerDisconnectEvent e) {
		
		String player = e.getPlayer().getDisplayName();
		String server = e.getPlayer().getServer().getInfo().getName();
		
		String message = "MC » " + player + " has left the network from the " + server + " server!";
		plugin.sendDiscordMessage(343606500713037826L, message);
		
	}
	
}
