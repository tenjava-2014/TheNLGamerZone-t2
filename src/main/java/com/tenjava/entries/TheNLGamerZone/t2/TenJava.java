package com.tenjava.entries.TheNLGamerZone.t2;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.TheNLGamerZone.t2.features.battery.Battery;
import com.tenjava.entries.TheNLGamerZone.t2.features.battery.BatteryTimer;

public class TenJava extends JavaPlugin {
	public static TenJava plugin;
	public static File dFile;
	public static FileConfiguration data;
	
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
		
		//Check if data.yml exists, if not create it
		dFile = new File(getDataFolder() + File.separator + "data.yml");
		if(!dFile.exists()) {
			try {
				dFile.createNewFile();
				Bukkit.getLogger().log(Level.INFO, "Created data.yml!");
			} catch(IOException e) {
				Bukkit.getLogger().log(Level.SEVERE, "Couldn't create data.yml!");
			}
		}
		
		data = YamlConfiguration.loadConfiguration(dFile);
		
		//Enable all features
		for(Feature f : Feature.values()) {
			f.enable(this, true);
			Bukkit.getLogger().log(Level.INFO, f.toString());
		}
		
		//Load all the batteries saved in data.yml
		if(data.contains("Batteries")) {
			for(String s : data.getConfigurationSection("Batteries").getKeys(false)) {
				if(Integer.parseInt(s) <= 0) continue;
				
				String[] ss = s.split(":");
				Location loc = new Location(Bukkit.getWorld(ss[3]), Double.parseDouble(ss[0]), Double.parseDouble(ss[1]), Double.parseDouble(ss[2]));
				Battery.setPower(loc, Integer.parseInt(s));
			}
		}
		
		//Start battery timer
		new BatteryTimer(this);
	}
	
	@Override
	public void onDisable() {
		//Save all the placed batteries
		for(Location loc : Battery.getPlacedBatteries()) {
			data.set("Batteries." + loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getWorld().getName(), Battery.getPower(loc));
		}
		
		setPlugin(null);
		dFile = null;
		data = null;
		
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
