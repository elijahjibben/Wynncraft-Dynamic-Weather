package com.jibben.wynncraftdynamicweather.mixin;

import com.jibben.wynncraftdynamicweather.WynncraftDynamicWeather;
import com.jibben.wynncraftdynamicweather.config.WeatherType;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Biome.class)
public class NesaakForceSnowMixin {
    // Nesaak Coordinate Rectangle
    @Unique
    private static final int X1 = -475, Z1 = -575;
    @Unique
    private static final int X2 = 310, Z2 = -940;

    //Lusuco and TOA Coordinate Rectangle
    @Unique
    private static final int X3 = -475, Z3 = -575;
    @Unique
    private static final int X4 = -85, Z4 = -295;

    @ModifyReturnValue(method = "getPrecipitationAt", at = @At("RETURN"))
    private Biome.Precipitation getPrecipitation(Biome.Precipitation original, BlockPos pos) {
        if (isPlayerInNesaak(pos) && WynncraftDynamicWeather.config.enableMod)  {
            return Biome.Precipitation.SNOW;
        }
        return original;
    }

    @Unique
    private boolean isPlayerInNesaak(BlockPos pos) {
        return ((pos.getX() >= Math.min(X1, X2) && pos.getX() <= Math.max(X1, X2) &&
                pos.getZ() >= Math.min(Z1, Z2) && pos.getZ() <= Math.max(Z1, Z2)) ||
                (pos.getX() >= Math.min(X3, X4) && pos.getX() <= Math.max(X3, X4) &&
                        pos.getZ() >= Math.min(Z3, Z4) && pos.getZ() <= Math.max(Z3, Z4)));
    }
}
