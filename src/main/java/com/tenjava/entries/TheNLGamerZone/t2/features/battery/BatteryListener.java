package com.tenjava.entries.TheNLGamerZone.t2.features.battery;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.tenjava.entries.TheNLGamerZone.t2.Feature;
import com.tenjava.entries.TheNLGamerZone.t2.TenJava;

public class BatteryListener implements Listener {
	private final TenJava plugin;
	private Map<Block, Short> durs;
	
	/**
	 * @param plugin
	 */
	public BatteryListener(TenJava plugin) {
		this.plugin = plugin;
		durs = new HashMap<Block, Short>();
	}
	
	@EventHandler
	public void onBeforeBatteryPlace(PlayerInteractEvent e) {
		
	}
	
	@EventHandler
	public void onBatteryPlace(BlockPlaceEvent e) {
		Feature f = Feature.valueOf("BATTERY");
		
		if(f.getMaterial().equals(e.getBlock().getType()) && e.getBlock().hasMetadata(ChatColor.stripColor(f.getName()))) {
			
		}
	}
}
