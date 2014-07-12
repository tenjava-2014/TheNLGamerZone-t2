package com.tenjava.entries.TheNLGamerZone.t2.features.battery;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

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
	public void onBatteryPlace(BlockPlaceEvent e) {
		Feature f = Feature.valueOf("BATTERY");
		
		if(e.getPlayer().getItemInHand() != null 
				&& e.getPlayer().getItemInHand().getType().equals(f.getMaterial()) 
				&& e.getPlayer().getItemInHand().hasItemMeta()
				&& e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()
				&& e.getPlayer().getItemInHand().getItemMeta().hasLore()
				&& e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(f.getName())
				&& e.getPlayer().getItemInHand().getItemMeta().getLore().contains("Power:")) {
			
			Integer p = 0;
			for(String s : e.getPlayer().getItemInHand().getItemMeta().getLore()) {
				if(!s.contains("Power:")) continue;
				
				try {
					p = Integer.parseInt(ChatColor.stripColor(s).split(":")[1]);
				} catch(NumberFormatException ex) {
					Bukkit.getLogger().log(Level.SEVERE, "Failed to get power from battery!");
					return;
				}
			}
			
			e.getPlayer().sendMessage("" + p);
						
			power.put(e.getPlayer().getItemInHand(), p);
		}
		
		if(f.getMaterial().equals(e.getBlock().getType()) && e.getBlock().hasMetadata(ChatColor.stripColor(f.getName()))) {
			e.getBlock().setMetadata("Power: " + power.get(e.getPlayer().getItemInHand()), new FixedMetadataValue(TenJava.getPlugin(), ""));
			power.remove(e.getPlayer().getItemInHand());
		}
	}
}
