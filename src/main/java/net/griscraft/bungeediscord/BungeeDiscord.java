package net.griscraft.bungeediscord;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeDiscord extends Plugin {
	
	JDA jda;
	String serverId = "304088294654738432";
	
	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerListener(this, new MinecraftListener(this));
		startBot();
	}
	
	@Override
	public void onDisable() {
		getProxy().getPluginManager().unregisterListeners(this);
		stopBot();
	}

	private void startBot() {
		try {
			jda = new JDABuilder(AccountType.BOT)
			        .setToken("MzQzNTM1ODMyODkzODgyMzY4.DGfmLw.10OjgDI_kxRZFulzBo22L_AOzDI")
			        .addEventListener(new BungeeDiscordBot(this))
			        .buildBlocking();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (RateLimitedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		sendDiscordMessage(343606500713037826L, "Server is online");
		
	}
	
	private void stopBot() {
		jda.getPresence().setStatus(OnlineStatus.OFFLINE);
	}
	
	private TextChannel getChannel(long id) {
		return jda.getGuildById(serverId).getTextChannelById(id);
	}
	
	public void sendDiscordMessage(long channelId, String message) {
		getChannel(channelId).sendMessage(new MessageBuilder().append(message).build()).complete();
	}
	
}
