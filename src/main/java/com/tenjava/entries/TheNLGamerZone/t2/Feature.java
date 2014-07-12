package com.tenjava.entries.TheNLGamerZone.t2;

import java.lang.reflect.Constructor;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import com.tenjava.entries.TheNLGamerZone.t2.features.FeatureBasics;
import com.tenjava.entries.TheNLGamerZone.t2.features.battery.Battery;

public enum Feature {
	BATTERY(ChatColor.YELLOW + "Battery", Battery.class, Material.STONE, Material.STONE)
	, CHARGER_POWER_SUPPLY(ChatColor.YELLOW + "Charger power supply", null, Material.SADDLE, Material.SAND)
	, CHARGER(ChatColor.YELLOW + "Charger", null, Material.RAILS, Material.BEDROCK);
	
	private final String name;
	private final Class<? extends FeatureBasics> fbClass;
	private final Material mat;
	private final Material b;
	private FeatureBasics fb;
	
	/**
	 * @param name
	 * @param fbClass
	 * @param mat
	 * @param b
	 */
	
	private Feature(String name, Class<? extends FeatureBasics> fbClass, Material mat, Material b) {
		this.name = name;
		this.fbClass = fbClass;
		this.mat = mat;
		this.b = b;
	}
	
	/**
	 * Returns the feature's name
	 * @return Feature's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns feature's basic class
	 * @return Class which extends FeaturesBasics
	 */
	public Class<? extends FeatureBasics> getfbClass() {
		return fbClass;
	}
	
	/**
	 * Returns the feature's material
	 * @return Feature's material
	 */
	public Material getMaterial() {
		return mat;
	}
	
	public Material getBlock() {
		return b;
	}
	
	public void enable(TenJava plugin, boolean m) {
		if(m) {
			fb.enableFeatures();
		} else {
			try {
				Constructor<? extends FeatureBasics> c = fbClass.getConstructor(String.class, TenJava.class);
				this.fb = c.newInstance(name, plugin);
			} catch(Exception e) {
				Bukkit.getLogger().log(Level.SEVERE, "Failed to start feature");
			}	
		}
	}
	
	public void disable() {
		fb.disableFeatures();
	}
}
