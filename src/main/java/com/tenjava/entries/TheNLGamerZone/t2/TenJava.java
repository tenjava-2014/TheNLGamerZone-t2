package com.tenjava.entries.TheNLGamerZone.t2;

import java.util.Arrays;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
	public void onLoad() {
		//Load all the features (batteries, charger and charger power supply)
		for(Feature f : Feature.values()) {
			f.enable(this, false);
			
			Bukkit.getLogger().log(Level.INFO, f.toString());
		}
	}
	
	@Override
	public void onEnable() {
		setPlugin(this);
		
		this.saveDefaultConfig();
		
		//Enable all features
		for(Feature f : Feature.values()) {
			f.enable(this, true);
			Bukkit.getLogger().log(Level.INFO, f.toString());
		}
	}
	
	@Override
	public void onDisable() {
		setPlugin(null);
		
		//Disable all features 
		for(Feature f : Feature.values()) {
			f.disable();
		}
	}
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		Player p = (Player)s;
		ItemStack is = new ItemStack(Feature.valueOf("BATTERY").getMaterial());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(Feature.valueOf("BATTERY").getName());
		im.setLore(Arrays.asList("Power: 4"));
		is.setItemMeta(im);
		p.getInventory().addItem(is);
		return false;
	}
}
