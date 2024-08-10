package com.jibben.wynncraftdynamicweather.mixin;

import com.jibben.wynncraftdynamicweather.WynncraftDynamicWeather;
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

    @ModifyExpressionValue(
            method = "renderSnowAndRain",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/Biome;getPrecipitationAt(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/biome/Biome$Precipitation;")
    )
    private Biome.Precipitation snowRenderer(Biome.Precipitation precipitation) {
        BlockPos pos = Minecraft.getInstance().player.blockPosition();
        if (!WynncraftDynamicWeather.config.renderVanillaSnow) {
            if (isPlayerInNesaak(pos)) {
                return Biome.Precipitation.NONE;
            }
        }
        return precipitation;
    }

    @Unique
    private boolean isPlayerInNesaak(BlockPos pos) {
        return ((pos.getX() >= Math.min(X1, X2) && pos.getX() <= Math.max(X1, X2) &&
                pos.getZ() >= Math.min(Z1, Z2) && pos.getZ() <= Math.max(Z1, Z2)) ||
                (pos.getX() >= Math.min(X3, X4) && pos.getX() <= Math.max(X3, X4) &&
                        pos.getZ() >= Math.min(Z3, Z4) && pos.getZ() <= Math.max(Z3, Z4)));
    }
}