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
import com.tenjava.entries.TheNLGamerZone.t2.features.battery.BatteryListener;
import com.tenjava.entries.TheNLGamerZone.t2.features.battery.BatteryTimer;

public class TenJava extends JavaPlugin {
	/*
	 * Please read 'README.md' and consider this plugin as never made :)
	 */
	
	
	
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
		
		/*//Load all the batteries saved in data.yml
		if(data.contains("Batteries")) {
			for(String s : data.getConfigurationSection("Batteries").getKeys(false)) {
				if(Integer.parseInt(s) <= 0) continue;
				
				String[] ss = s.split("_");
				Location loc = new Location(Bukkit.getWorld(ss[3]), toLocationDouble(ss[0]), toLocationDouble(ss[1]), toLocationDouble(ss[2]));
				BatteryListener.setPower(loc, Integer.parseInt(s));
			}
		}*/
		
		//Start battery timer
		new BatteryTimer(this);
	}
	
	@Override
	public void onDisable() {
		/*//Save all the placed batteries
		for(Location loc : BatteryListener.getPlacedBatteries()) {
			data.set("Batteries." + toConfigString(loc.getX()) + "_" + toConfigString(loc.getY()) + "_" + toConfigString(loc.getZ()) + "_" + loc.getWorld().getName(), BatteryListener.getPower(loc));
		}*/
		
		saveData();
		
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
		ItemStack is = new ItemStack(Feature.BATTERY.getMaterial());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(Feature.BATTERY.getName());
		im.setLore(Arrays.asList("Power: 4"));
		is.setItemMeta(im);
		p.getInventory().addItem(is);
		return false;
	}
	
	public static void saveData() {
		try {
			data.save(dFile);
		} catch(IOException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Couldn't save data.yml!");
		}
	}
    
	public static String toConfigString(double d) {
		return String.valueOf(d).replace(".", "!");
	}
	
	public static Double toLocationDouble(String s) {
		return Double.parseDouble(s.replace("!", "."));
	}
}
