package com.tenjava.entries.TheNLGamerZone.t2.features;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.tenjava.entries.TheNLGamerZone.t2.TenJava;

public abstract class FeatureBasics {
	protected String name;
	protected TenJava plugin;
	
	public FeatureBasics(String name, TenJava plugin) {
		this.name = name;
		this.plugin = plugin;
	}
	
	public abstract void enableFeature();
	
	protected void registerListener(Listener l) {
		Bukkit.getPluginManager().registerEvents(l, plugin);
	}
}
