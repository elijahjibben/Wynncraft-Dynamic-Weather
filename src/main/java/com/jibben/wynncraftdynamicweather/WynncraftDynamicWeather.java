package com.jibben.wynncraftdynamicweather;

import com.jibben.wynncraftdynamicweather.command.WynnWeatherCommand;
import com.jibben.wynncraftdynamicweather.config.WeatherType;
import com.jibben.wynncraftdynamicweather.modmenu.ModConfig;
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
	private static double dailyProbability = 0.99;
	private static final double tickProbability = 1 - Math.pow(1 - dailyProbability, 1 / dailyTicks);
	private static int weatherDuration = 0;

	public static WeatherType weatherType = WeatherType.CLEAR;

	public static ModConfig config;

	@Override
	public void onInitialize() {
		WynnWeatherCommand.register();
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();


		ClientTickEvents.END_CLIENT_TICK.register(client -> {

			if (client.player != null && client.level != null && config.enableMod) {
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