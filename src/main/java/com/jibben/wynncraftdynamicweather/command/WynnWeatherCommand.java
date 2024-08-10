package com.jibben.wynncraftdynamicweather.command;

import com.jibben.wynncraftdynamicweather.WynncraftDynamicWeather;
import com.jibben.wynncraftdynamicweather.config.WeatherType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.network.chat.Component;

public class WynnWeatherCommand {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

            dispatcher.register(ClientCommandManager.literal("wynnweather").then(ClientCommandManager.literal("clear")
                    .executes(context -> {

                        if (WynncraftDynamicWeather.getWeatherType() == WeatherType.DISABLED) {
                            context.getSource().sendFeedback(Component.literal("Mod is disabled! Please enable in Mod Menu or via /wynnweather toggle."));
                        } else {
                            WynncraftDynamicWeather.setWeatherType(WeatherType.CLEAR);
                            context.getSource().sendFeedback(Component.literal("Set weather to clear."));
                        }
                        return 0;
                    }))
            );

            dispatcher.register(ClientCommandManager.literal("wynnweather").then(ClientCommandManager.literal("rain")
                    .executes(context -> {
                        if (WynncraftDynamicWeather.getWeatherType() == WeatherType.DISABLED) {
                            context.getSource().sendFeedback(Component.literal("Mod is disabled! Please enable in Mod Menu or via /wynnweather toggle."));
                        } else {
                            WynncraftDynamicWeather.setWeatherType(WeatherType.RAIN);
                            context.getSource().sendFeedback(Component.literal("Set weather to rain."));
                        }
                        return 0;
                    }))
            );

            dispatcher.register(ClientCommandManager.literal("wynnweather").then(ClientCommandManager.literal("thunder")
                    .executes(context -> {
                        if (WynncraftDynamicWeather.getWeatherType() == WeatherType.DISABLED) {
                            context.getSource().sendFeedback(Component.literal("Mod is disabled! Please enable in Mod Menu or via /wynnweather toggle."));
                        } else {
                            WynncraftDynamicWeather.setWeatherType(WeatherType.THUNDER);
                            context.getSource().sendFeedback(Component.literal("Set weather to thunder."));
                        }
                        return 0;
                    }))
            );

            dispatcher.register(ClientCommandManager.literal("wynnweather").then(ClientCommandManager.literal("status")
                    .executes(context -> {
                        context.getSource().sendFeedback(Component.literal("Daily Probability: " + (WynncraftDynamicWeather.getDailyProbability() * 100) + "%" + " Current Mode: " + WynncraftDynamicWeather.getWeatherType()));
                        return 0;
                    }))
            );

            dispatcher.register(ClientCommandManager.literal("wynnweather").then(ClientCommandManager.literal("toggle")
                    .executes(context -> {
                        if (WynncraftDynamicWeather.getWeatherType() == WeatherType.DISABLED) {
                            WynncraftDynamicWeather.setWeatherType(WeatherType.CLEAR);
                            context.getSource().sendFeedback(Component.literal("Weather features have been enabled!"));
                        } else {
                            WynncraftDynamicWeather.setWeatherType(WeatherType.DISABLED);
                            context.getSource().sendFeedback(Component.literal("Weather features have been disabled!"));
                        }
                        return 0;
                    }))
            );

        });
    }
}
