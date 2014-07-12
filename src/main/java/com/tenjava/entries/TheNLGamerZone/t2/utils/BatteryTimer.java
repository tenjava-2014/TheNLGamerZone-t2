package com.tenjava.entries.TheNLGamerZone.t2.utils;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import com.tenjava.entries.TheNLGamerZone.t2.TenJava;
import com.tenjava.entries.TheNLGamerZone.t2.features.battery.Battery;

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
		}
	}
}
