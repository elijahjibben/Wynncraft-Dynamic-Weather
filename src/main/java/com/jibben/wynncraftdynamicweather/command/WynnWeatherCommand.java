package com.jibben.wynncraftdynamicweather.command;

import com.jibben.wynncraftdynamicweather.WynncraftDynamicWeather;
import com.jibben.wynncraftdynamicweather.config.WeatherType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;

public class WynnWeatherCommand {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

            dispatcher.register(ClientCommandManager.literal("wynnweather").then(ClientCommandManager.literal("clear")
                    .executes(context -> {

                        if (WynncraftDynamicWeather.getWeatherType() == WeatherType.DISABLED) {
                            context.getSource().sendFeedback(Text.literal("Mod is disabled! Please enable in Mod Menu."));
                        } else {
                            WynncraftDynamicWeather.setWeatherType(WeatherType.CLEAR);
                            context.getSource().sendFeedback(Text.literal("Set weather to clear."));
                        }
                        return 0;
                    }))
            );

            dispatcher.register(ClientCommandManager.literal("wynnweather").then(ClientCommandManager.literal("rain")
                    .executes(context -> {
                        if (WynncraftDynamicWeather.getWeatherType() == WeatherType.DISABLED) {
                            context.getSource().sendFeedback(Text.literal("Mod is disabled! Please enable in Mod Menu."));
                        } else {
                            WynncraftDynamicWeather.setWeatherType(WeatherType.RAIN);
                            context.getSource().sendFeedback(Text.literal("Set weather to rain."));
                        }
                        return 0;
                    }))
            );

            dispatcher.register(ClientCommandManager.literal("wynnweather").then(ClientCommandManager.literal("thunder")
                    .executes(context -> {
                        if (WynncraftDynamicWeather.getWeatherType() == WeatherType.DISABLED) {
                            context.getSource().sendFeedback(Text.literal("Mod is disabled! Please enable in Mod Menu."));
                        } else {
                            WynncraftDynamicWeather.setWeatherType(WeatherType.THUNDER);
                            context.getSource().sendFeedback(Text.literal("Set weather to thunder."));
                        }
                        return 0;
                    }))
            );

            dispatcher.register(ClientCommandManager.literal("wynnweather").then(ClientCommandManager.literal("status")
                    .executes(context -> {
                        context.getSource().sendFeedback(Text.literal("Daily Probability: " + (WynncraftDynamicWeather.getDailyProbability() * 100) + "%" ));
                        return 0;
                    }))
            );

        });
    }
}
