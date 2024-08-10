package com.jibben.wynncraftdynamicweather;

import com.jibben.wynncraftdynamicweather.command.WynnWeatherCommand;
import com.jibben.wynncraftdynamicweather.config.WeatherType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WynncraftDynamicWeather implements ModInitializer {
	public static final String ModID = "wynncraft-dynamic-weather";
    public static final Logger LOGGER = LoggerFactory.getLogger(ModID);

	private static final double dailyTicks = 24000;
	private static double dailyProbability = 0.99;
	private static final double tickProbability = 1 - Math.pow(1 - dailyProbability, 1 / dailyTicks);

	public static WeatherType weatherType = WeatherType.DISABLED;

	@Override
	public void onInitialize() {
		WynnWeatherCommand.register();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null && client.level != null && weatherType == WeatherType.CLEAR) {
				if (Math.random() < tickProbability) {
					changeWeather();
				}
			}
		});
	}

	private void changeWeather() {
		if (Math.random() < 0.5) {
			weatherType = WeatherType.RAIN;

		}
		else {
			weatherType = WeatherType.THUNDER;
		}
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