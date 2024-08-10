package com.jibben.wynncraftdynamicweather.modmenu;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreen {

    public static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("title.mod_config"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // Category
        ConfigCategory general = builder.getOrCreateCategory(Component.translatable("category.general"));

        // Enable Mod Toggle
        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("option.enable_mod"), getConfig("enableMod"))
                .setTooltip(Component.translatable("tooltip.enable_mod"))
                .setSaveConsumer(newValue -> setConfig("enableMod", newValue))
                .setDefaultValue(true) // Default value
                .build());

        // Disable Vanilla Snow Toggle
        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("option.disable_snow"), getConfig("disableSnow"))
                .setTooltip(Component.translatable("tooltip.disable_snow"))
                .setSaveConsumer(newValue -> setConfig("disableSnow", newValue))
                .setDefaultValue(false)
                .build());

        // Weather Duration Selector
        general.addEntry(entryBuilder.startEnumSelector(Component.translatable("option.weather_duration"), WeatherDuration.class, getConfig("weatherDuration"))
                .setTooltip(Component.translatable("tooltip.weather_duration"))
                .setSaveConsumer(newValue -> setConfig("weatherDuration", newValue))
                .setDefaultValue(WeatherDuration.DEFAULT)
                .build());

        // Debug Status Command Toggle
        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("option.debug_status"), getConfig("debugStatus"))
                .setTooltip(Component.translatable("tooltip.debug_status"))
                .setSaveConsumer(newValue -> setConfig("debugStatus", newValue))
                .setDefaultValue(false)
                .build());

        return builder.build();
    }

    // Placeholder methods for configuration management
    private static <T> T getConfig(String key) {
        // Implement your configuration retrieval logic here
        return null;
    }

    private static <T> void setConfig(String key, T value) {
        // Implement your configuration setting logic here
    }
}

enum WeatherDuration {
    SHORT, DEFAULT, LONG;
}

