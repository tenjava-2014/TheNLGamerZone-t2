package com.tenjava.entries.TheNLGamerZone.t2.features.battery;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import com.tenjava.entries.TheNLGamerZone.t2.Feature;
import com.tenjava.entries.TheNLGamerZone.t2.TenJava;

public class BatteryListener implements Listener {
	private final TenJava plugin;
	private Map<ItemStack, Integer> power;
	protected static Map<Location, Integer> spower;
	
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
				&& e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(f.getName())) {
			Integer p = 0;
			for(String s : e.getPlayer().getItemInHand().getItemMeta().getLore()) {
				if(!s.contains("Power:")) continue;

				try {
					p = Integer.parseInt(ChatColor.stripColor(s).split(" ")[1]);
				} catch(NumberFormatException ex) {
					Bukkit.getLogger().log(Level.SEVERE, "Failed to get power from battery!");
					return;
				}
			}
						
			power.put(e.getPlayer().getItemInHand(), p);
		}
		
		if(f.getMaterial().equals(e.getBlock().getType()) && e.getBlock().hasMetadata(ChatColor.stripColor(f.getName()))) {
			spower.put(e.getBlock().getLocation(), power.get(e.getPlayer().getItemInHand()));
			power.remove(e.getPlayer().getItemInHand());
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Feature f = Feature.valueOf("BATTERY");
		Integer p = 0;
		
		if(e.getBlock().getType().equals(f.getMaterial())
				&& spower.containsKey(e.getBlock().getLocation())) {
			p = spower.get(e.getBlock().getLocation());
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
			e.getBlock().getWorld().playEffect(e.getBlock().getLocation(), Effect.STEP_SOUND, e.getBlock().getData());
			e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(f.getMaterial()));
		}
	}
}
