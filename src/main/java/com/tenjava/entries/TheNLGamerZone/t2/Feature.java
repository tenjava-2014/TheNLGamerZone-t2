package com.tenjava.entries.TheNLGamerZone.t2;

import com.tenjava.entries.TheNLGamerZone.t2.features.FeatureBasics;

public enum Feature {
	BATTERY("battery", null), CHARGER_POWER_SUPPLY("charger_power_supply", null), CHARGER("charger", null);
	
	private final String name;
	private final Class<? extends FeatureBasics> fbClass;
	
	private Feature(String name, Class<? extends FeatureBasics> fbClass) {
		this.name = name;
		this.fbClass = fbClass;
	}
	
	public String getName() {
		return name;
	}
	
	public Class<? extends FeatureBasics> getfbClass() {
		return fbClass;
	}
}
