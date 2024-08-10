package com.jibben.wynncraftdynamicweather;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "wynncraft-dynamic-weather")
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    public boolean enableMod = true;

    @ConfigEntry.Gui.Tooltip
    public boolean renderVanillaSnow = false;

    @ConfigEntry.Gui.Tooltip
    public weatherDurationEnum weatherDuration = weatherDurationEnum.DEFAULT;


    @ConfigEntry.Gui.CollapsibleObject
    public Advanced advanced = new Advanced();

    public static class Advanced {
        @ConfigEntry.Gui.Tooltip
        public boolean enableDebugCommand = false;
    }

    public enum weatherDurationEnum {
        SHORT,
        DEFAULT,
        LONG
    }
}
