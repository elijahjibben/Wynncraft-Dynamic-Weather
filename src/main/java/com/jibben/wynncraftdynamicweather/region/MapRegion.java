package com.jibben.wynncraftdynamicweather.region;

import net.minecraft.core.BlockPos;

public class MapRegion {
    private final int x1, z1, x2, z2;
    private final double dailyProbability;
    private final boolean neverRain;

    public MapRegion(int x1, int z1, int x2, int z2, double dailyProbability, boolean neverRain) {
        this.x1 = x1;
        this.z1 = z1;
        this.x2 = x2;
        this.z2 = z2;
        this.dailyProbability = dailyProbability;
        this.neverRain = neverRain;
    }

    public MapRegion(int x1, int z1, int x2, int z2, double dailyProbability) {
        this.x1 = x1;
        this.z1 = z1;
        this.x2 = x2;
        this.z2 = z2;
        this.dailyProbability = dailyProbability;
        this.neverRain = false;
    }


    public boolean isWithin(BlockPos pos) {
        return ((pos.getX() >= Math.min(x1, x2) && pos.getX() <= Math.max(x1, x2) &&
                pos.getZ() >= Math.min(z1, z2) && pos.getZ() <= Math.max(z1, z2)));
    }

    public double getDailyProbability() {
        return dailyProbability;
    }

    public boolean isNeverRain() {
        return neverRain;
    }
}
