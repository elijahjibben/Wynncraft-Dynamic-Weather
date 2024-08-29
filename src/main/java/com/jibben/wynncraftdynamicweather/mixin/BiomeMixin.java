package com.jibben.wynncraftdynamicweather.mixin;

import com.jibben.wynncraftdynamicweather.WynncraftDynamicWeather;
import com.jibben.wynncraftdynamicweather.region.MapRegion;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Biome.class)
public class BiomeMixin {
    @Unique
    MapRegion nesaak = new MapRegion(-475, -575, 310, -940, 0.95);
    @Unique
    MapRegion lusuco = new MapRegion(-475, -575, -85, -295, 0.95);
    @Unique
    MapRegion almuj = new MapRegion(850, -2330, 1500, -1180, 0.50);

    @ModifyReturnValue(method = "getPrecipitationAt", at = @At("RETURN"))
    private Biome.Precipitation getPrecipitation(Biome.Precipitation original, BlockPos pos) {
        if ((nesaak.isWithin(pos) || lusuco.isWithin(pos)) && WynncraftDynamicWeather.config.enableMod)  {
            return Biome.Precipitation.SNOW;
        }
        if (almuj.isWithin(pos) && WynncraftDynamicWeather.config.enableMod)  {
            return Biome.Precipitation.NONE;
        }
        return original;
    }

    @ModifyReturnValue(method = "getBaseTemperature", at = @At("RETURN"))
    private float getBaseTemperature(float original) {
        if (Minecraft.getInstance().player != null) {
            BlockPos pos = Minecraft.getInstance().player.blockPosition();
            if (almuj.isWithin(pos) && WynncraftDynamicWeather.config.enableMod) {
                return 1.0F;
            }
        }
        return original;
    }

}
