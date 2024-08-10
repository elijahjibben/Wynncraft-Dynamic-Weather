package com.jibben.wynncraftdynamicweather.mixin;

import com.jibben.wynncraftdynamicweather.WynncraftDynamicWeather;
import com.jibben.wynncraftdynamicweather.region.MapRegion;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelRenderer.class)
public class PreventVanillaSnowMixin {
    @Unique
    MapRegion nesaak = new MapRegion(-475, -575, 310, -940, 0.95);
    @Unique
    MapRegion lusuco = new MapRegion(-475, -575, -85, -295, 0.95);

    @ModifyExpressionValue(
            method = "renderSnowAndRain",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/Biome;getPrecipitationAt(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/biome/Biome$Precipitation;")
    )
    private Biome.Precipitation snowRenderer(Biome.Precipitation precipitation) {
        BlockPos pos = Minecraft.getInstance().player.blockPosition();
        if (!WynncraftDynamicWeather.config.renderVanillaSnow) {
            if (nesaak.isWithin(pos) || lusuco.isWithin(pos)) {
                return Biome.Precipitation.NONE;
            }
        }
        return precipitation;
    }

}
