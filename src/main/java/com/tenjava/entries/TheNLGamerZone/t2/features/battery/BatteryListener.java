package com.tenjava.entries.TheNLGamerZone.t2.features.battery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
import org.bukkit.inventory.meta.ItemMeta;

import com.tenjava.entries.TheNLGamerZone.t2.Feature;
import com.tenjava.entries.TheNLGamerZone.t2.TenJava;

public class BatteryListener implements Listener {
	private final TenJava plugin;
	protected static Map<ItemStack, Integer> power = new HashMap<ItemStack, Integer>();
	protected static Map<Location, Integer> spower = new HashMap<Location, Integer>();
	
	/**
	 * @param plugin
	 */
	public BatteryListener(TenJava plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBatteryPlace(BlockPlaceEvent e) {
		Feature f = Feature.BATTERY;
				
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
			
			if(p <= 0) {
				e.getPlayer().sendMessage(ChatColor.RED + "That battery is empty! Recharge it first!");
				e.setCancelled(true);
				return;
			}
			
			e.getPlayer().sendMessage("TETS");
			e.getBlock().setType(Material.REDSTONE_BLOCK);
			spower.put(e.getBlock().getLocation(), p);
			power.remove(e.getPlayer().getItemInHand());
			TenJava.data.set("Batteries." + e.getBlock().getLocation().getX() + "-" + e.getBlock().getLocation().getY() + "-" + e.getBlock().getLocation().getZ() + "-" + e.getBlock().getLocation().getWorld().getName(), power);
			TenJava.saveData();
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Feature f = Feature.BATTERY;
		Integer p = 0;
				
		if(spower.containsKey(e.getBlock().getLocation())) {
			p = spower.get(e.getBlock().getLocation());
			
			e.getPlayer().sendMessage("TETS");
			spower.remove(e.getBlock().getLocation());
			power.put(getItemStack(p, f), p);
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
			e.getBlock().getWorld().playEffect(e.getBlock().getLocation(), Effect.STEP_SOUND, e.getBlock().getData());
			e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), getItemStack(p, f));
		}
	}
	
	public static ItemStack getItemStack(Integer p, Feature f) {
		ItemStack is = new ItemStack(f.getMaterial());
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(f.getName());
		im.setLore(Arrays.asList("Power: " + p));
		is.setItemMeta(im);
		return is;
	}
	
	public static void setPower(Location loc, Integer power) {
		BatteryListener.spower.put(loc, power);
	}
	
	public static void setPower(ItemStack is, Integer power) {
		BatteryListener.power.put(is, power);
	}
	
	public static Set<Location> getPlacedBatteries() {
		return BatteryListener.spower.keySet();
	}
	
	public static Integer getPower(Location loc) {
		return BatteryListener.spower.get(loc);
	}
	
	public static Integer getPower(ItemStack is) {
		return BatteryListener.power.get(is);
	}
}
