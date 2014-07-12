package com.tenjava.entries.TheNLGamerZone.t2;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import com.tenjava.entries.TheNLGamerZone.t2.features.FeatureBasics;

public enum Feature {
	BATTERY(ChatColor.YELLOW + "Battery", null, Material.STONE, Material.STONE)
	, CHARGER_POWER_SUPPLY(ChatColor.YELLOW + "Charger power supply", null, Material.SADDLE, Material.SAND)
	, CHARGER(ChatColor.YELLOW + "Charger", null, Material.RAILS, Material.BEDROCK);
	
	private final String name;
	private final Class<? extends FeatureBasics> fbClass;
	private final Material mat;
	private final Material b;
	
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
}
