package com.jibben.wynncraftdynamicweather.region;

import com.jibben.wynncraftdynamicweather.WynncraftDynamicWeather;
import com.jibben.wynncraftdynamicweather.config.WeatherType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class RegionManager {
    private final List<MapRegion> regions = new ArrayList<>();

    public void addRegion(MapRegion region) {
        regions.add(region);
    }

    public void assignDailyProbability() {
        BlockPos pos = Minecraft.getInstance().player.blockPosition();
        for (MapRegion region : regions) {
            if (region.isWithin(pos)) {
                WynncraftDynamicWeather.setDailyProbability(region.getDailyProbability());
                if (region.isNeverRain()) {
                    WynncraftDynamicWeather.setWeatherType(WeatherType.CLEAR);
                }
                break;
            }
            else WynncraftDynamicWeather.setDailyProbability(0.20);
        }
    }
}
