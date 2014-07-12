package com.tenjava.entries.TheNLGamerZone.t2.features.battery;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.TheNLGamerZone.t2.Feature;
import com.tenjava.entries.TheNLGamerZone.t2.TenJava;
import com.tenjava.entries.TheNLGamerZone.t2.features.battery.Battery;
import com.tenjava.entries.TheNLGamerZone.t2.features.battery.BatteryListener;

public class BatteryTimer extends BukkitRunnable{
	private final TenJava plugin;
	
	public BatteryTimer(TenJava plugin) {
		this.plugin = plugin;
		this.runTaskTimer(plugin, 1, TenJava.getPlugin().getConfig().getInt("bt-delay") * 20);
	}

	@Override
	public void run() {
		for(Location loc : Battery.getPlacedBatteries()) {
			loc.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 1);
			Battery.setPower(loc, Battery.getPower(loc) - 1);
			
			if(Battery.getPower(loc) <= 0) {
				loc.getWorld().getBlockAt(loc).setType(Material.AIR);
				loc.getWorld().dropItemNaturally(loc, BatteryListener.getItemStack(0, Feature.BATTERY));
			}
		}
	}
}
