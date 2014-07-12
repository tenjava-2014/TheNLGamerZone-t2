package com.tenjava.entries.TheNLGamerZone.t2.features.battery;

import org.bukkit.Bukkit;
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
		this.runTaskTimer(plugin, TenJava.getPlugin().getConfig().getInt("bt-delay") * 20, TenJava.getPlugin().getConfig().getInt("bt-delay") * 20);
	}

	@Override
	public void run() {
		for(Location loc : BatteryListener.getPlacedBatteries()) {
			loc.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 1);
			BatteryListener.setPower(loc, BatteryListener.getPower(loc) - 1);
			/*TenJava.data.set("Batteries." + TenJava.toConfigString(loc.getX()) + "_" + TenJava.toConfigString(loc.getY()) + "_" + TenJava.toConfigString(loc.getZ()) + "_" + loc.getWorld().getName(), BatteryListener.getPower(loc));
			TenJava.saveData();*/
			
			if(BatteryListener.getPower(loc) <= 0) {
				BatteryListener.spower.remove(loc);
				loc.getWorld().getBlockAt(loc).setType(Material.AIR);
				loc.getWorld().dropItemNaturally(loc, BatteryListener.getItemStack(0, Feature.BATTERY));
			}
		}
	}
}
