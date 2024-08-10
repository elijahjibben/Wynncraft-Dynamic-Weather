package com.jibben.wynncraftdynamicweather;

import com.jibben.wynncraftdynamicweather.command.WynnWeatherCommand;
import com.jibben.wynncraftdynamicweather.config.WeatherType;
import com.jibben.wynncraftdynamicweather.modmenu.ModConfig;
import com.jibben.wynncraftdynamicweather.region.MapRegion;
import com.jibben.wynncraftdynamicweather.region.RegionManager;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class WynncraftDynamicWeather implements ModInitializer {
	public static final String ModID = "wynncraft-dynamic-weather";
    public static final Logger LOGGER = LoggerFactory.getLogger(ModID);

	private static final double dailyTicks = 24000;
	private static double dailyProbability = 0.20;
	private static final double tickProbability = 1 - Math.pow(1 - dailyProbability, 1 / dailyTicks);
	private static int weatherDuration = 0;

	public static WeatherType weatherType = WeatherType.CLEAR;

	public static ModConfig config;

	private static final RegionManager regionManager = new RegionManager();

	@Override
	public void onInitialize() {
		WynnWeatherCommand.register();
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
		setupRegions();


		ClientTickEvents.END_CLIENT_TICK.register(client -> {

			if (client.player != null && client.level != null && config.enableMod && weatherType == WeatherType.CLEAR) {
				regionManager.assignDailyProbability();
				if (Math.random() < tickProbability) {
					changeWeather();
				}
			}

			if (weatherDuration > 0) {
				weatherDuration--;
				if (weatherDuration == 0) {
					weatherType = WeatherType.CLEAR;
				}
			}
		});
	}

	private void changeWeather() {

		if (config.weatherDuration == ModConfig.weatherDurationEnum.SHORT) {
			weatherDuration = 6000 + new Random().nextInt(6001);
		}
		else if (config.weatherDuration == ModConfig.weatherDurationEnum.DEFAULT) {
			weatherDuration = 12000 + new Random().nextInt(12001);
		}
		else if (config.weatherDuration == ModConfig.weatherDurationEnum.LONG) {
			weatherDuration = 24000 + new Random().nextInt(24001);
		}
		else weatherDuration = 12000 + new Random().nextInt(12001);

		if (Math.random() < 0.5) {
			weatherType = WeatherType.RAIN;
		}
		else {
			weatherType = WeatherType.THUNDER;
		}
	}

	private void setupRegions() {
		// Nesaak
		regionManager.addRegion(new MapRegion(-475, -575, 310, -940, 0.95));

		// Lusuco and TOA
		regionManager.addRegion(new MapRegion(-475, -575, -85, -295, 0.95));

		// Nemract
		regionManager.addRegion(new MapRegion(-30,-2300,530,-1820,0.75));

		// Pirate Town and Galleons Graveyard
		regionManager.addRegion(new MapRegion(-800,-3000,-495,-3600,0.75));

		// Almuj
		regionManager.addRegion(new MapRegion(850, -2330, 1500, -1180, 0.50));

		// Dernal Jungle
		regionManager.addRegion(new MapRegion(-485,-925,-980,-249,0.33));

		// Olux Swamp
		regionManager.addRegion(new MapRegion(-2275,-5600,-1450,-5070,0.33));

		//Silent Expanse
		regionManager.addRegion(new MapRegion(400,-1150,1500,-250,0.25));

		// Corkus and Legendary Island
		regionManager.addRegion(new MapRegion(-2155,-3400,-1000,-2030,0));

		// Volcanic Isles
		regionManager.addRegion(new MapRegion(-801,-3500,-1190,-3875,0, true));

		// Molten Heights
		regionManager.addRegion(new MapRegion(950,-5000,1580,-5650,0, true));

		// Realm of Light
		regionManager.addRegion(new MapRegion(-1150,-5775,-615,-6600,0, true));
	}

	public static WeatherType getWeatherType() {
		return weatherType;
	}

	public static void setWeatherType(WeatherType weatherType) {
		WynncraftDynamicWeather.weatherType = weatherType;
	}

	public static double getDailyProbability() {
		return dailyProbability;
	}

	public static void setDailyProbability(double dailyProbability) {
		WynncraftDynamicWeather.dailyProbability = dailyProbability;
	}
}