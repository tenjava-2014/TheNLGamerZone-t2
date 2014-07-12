package com.tenjava.entries.TheNLGamerZone.t2.features.battery;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.tenjava.entries.TheNLGamerZone.t2.Feature;
import com.tenjava.entries.TheNLGamerZone.t2.TenJava;

public class BatteryListener implements Listener {
	private final TenJava plugin;
	private Map<ItemStack, Integer> power;
	
	/**
	 * @param plugin
	 */
	public BatteryListener(TenJava plugin) {
		this.plugin = plugin;
		power = new HashMap<ItemStack, Integer>();
	}
	
	@EventHandler
	public void onBeforeBatteryPlace(PlayerInteractEvent e) {
		Feature f = Feature.valueOf("BATTERY");

		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) 
				&& e.getItem() != null 
				&& e.getItem().getType().equals(f.getMaterial()) 
				&& e.getItem().hasItemMeta()
				&& e.getItem().getItemMeta().hasDisplayName()
				&& e.getItem().getItemMeta().hasLore()
				&& e.getItem().getItemMeta().getDisplayName().equals(f.getName())
				&& e.getItem().getItemMeta().getLore().contains("Power:")) {
			
			Integer p = 0;
			for(String s : e.getItem().getItemMeta().getLore()) {
				if(!s.contains("Power:")) continue;
				
				try {
					p = Integer.parseInt(ChatColor.stripColor(s).split(":")[1]);
				} catch(NumberFormatException ex) {
					Bukkit.getLogger().log(Level.SEVERE, "Failed to get power from battery!");
					return;
				}
			}
						
			power.put(e.getItem(), p);
		}
	}
	
	@EventHandler
	public void onBatteryPlace(BlockPlaceEvent e) {
		Feature f = Feature.valueOf("BATTERY");
		
		if(f.getMaterial().equals(e.getBlock().getType()) && e.getBlock().hasMetadata(ChatColor.stripColor(f.getName()))) {
			
		}
	}
}
