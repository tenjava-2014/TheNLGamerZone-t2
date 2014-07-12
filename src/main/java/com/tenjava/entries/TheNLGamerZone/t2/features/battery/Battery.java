package com.tenjava.entries.TheNLGamerZone.t2.features.battery;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import com.tenjava.entries.TheNLGamerZone.t2.TenJava;
import com.tenjava.entries.TheNLGamerZone.t2.features.FeatureBasics;

public class Battery extends FeatureBasics{

	public Battery(String name, TenJava plugin) {
		super(name, plugin);
	}

	@Override
	public void enableFeatures() {
		registerListener(new BatteryListener(plugin));
	}

	@Override
	public void disableFeatures() { 
		//Does nothing, Feature class deals with this
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
