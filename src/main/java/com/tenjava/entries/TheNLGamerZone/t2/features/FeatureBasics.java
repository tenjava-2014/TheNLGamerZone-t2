package com.tenjava.entries.TheNLGamerZone.t2.features;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.tenjava.entries.TheNLGamerZone.t2.TenJava;

public abstract class FeatureBasics {
	protected String name;
	protected TenJava plugin;
	
	/**
	 * @param name
	 * @param plugin
	 */
	public FeatureBasics(String name, TenJava plugin) {
		this.name = name;
		this.plugin = plugin;
	}
	
	/**
	 * Enable features	
	 */
	public abstract void enableFeatures();
	
	/**
	 * Disable features
	 */
	public abstract void disableFeatures();
	
	/**
	 * Registers listener
	 * @param l Listener to register
	 */
	protected void registerListener(Listener l) {
		Bukkit.getPluginManager().registerEvents(l, plugin);
	}
}
