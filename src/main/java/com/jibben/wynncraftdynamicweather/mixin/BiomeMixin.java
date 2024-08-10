package com.jibben.wynncraftdynamicweather.mixin;

import com.jibben.wynncraftdynamicweather.WynncraftDynamicWeather;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Biome.class)
public class BiomeMixin {
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

    //Almuj Coordinate Rectangle
    @Unique
    private static final int X5 = 850, Z5 = -2330;
    @Unique
    private static final int X6 = 1500, Z6 = -1180;

    @Unique
    private boolean isPlayerInNesaak(BlockPos pos) {
        return ((pos.getX() >= Math.min(X1, X2) && pos.getX() <= Math.max(X1, X2) &&
                pos.getZ() >= Math.min(Z1, Z2) && pos.getZ() <= Math.max(Z1, Z2)) ||
                (pos.getX() >= Math.min(X3, X4) && pos.getX() <= Math.max(X3, X4) &&
                        pos.getZ() >= Math.min(Z3, Z4) && pos.getZ() <= Math.max(Z3, Z4)));
    }

    @Unique
    private boolean isPlayerInAlmuj(BlockPos pos) {
        return ((pos.getX() >= Math.min(X5, X6) && pos.getX() <= Math.max(X5, X6) &&
                pos.getZ() >= Math.min(Z5, Z6) && pos.getZ() <= Math.max(Z5, Z6)));
    }

    @ModifyReturnValue(method = "getPrecipitationAt", at = @At("RETURN"))
    private Biome.Precipitation getPrecipitation(Biome.Precipitation original, BlockPos pos) {
        if (isPlayerInNesaak(pos) && WynncraftDynamicWeather.config.enableMod)  {
            return Biome.Precipitation.SNOW;
        }
        if (isPlayerInAlmuj(pos) && WynncraftDynamicWeather.config.enableMod)  {
            return Biome.Precipitation.NONE;
        }
        return original;
    }

    @ModifyReturnValue(method = "getBaseTemperature", at = @At("RETURN"))
    private float getBaseTemperature(float original) {
        BlockPos pos = Minecraft.getInstance().player.blockPosition();
        if (isPlayerInAlmuj(pos) && WynncraftDynamicWeather.config.enableMod)  {
            return 1.0F;
        }
        else return original;
    }

}
