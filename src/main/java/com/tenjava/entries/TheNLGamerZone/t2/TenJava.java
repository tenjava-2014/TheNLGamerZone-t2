package com.tenjava.entries.TheNLGamerZone.t2;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
	public static TenJava plugin;
	
	public static TenJava getPlugin() {
		return plugin;
	}
	
	public static void setPlugin(TenJava plugin) {
		TenJava.plugin = plugin;
	}
	
	@Override
	public void onEnable() {
		setPlugin(this);
		
		this.saveDefaultConfig();
	}
	
	@Override
	public void onDisable() {
		setPlugin(null);
	}
}
