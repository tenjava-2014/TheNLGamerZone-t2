package com.tenjava.entries.TheNLGamerZone.t2.features.battery;

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
}
