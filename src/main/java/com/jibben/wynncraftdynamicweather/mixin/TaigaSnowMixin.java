package com.jibben.wynncraftdynamicweather.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(WorldRenderer.class)
public abstract class TaigaSnowMixin {

    @ModifyExpressionValue(
            method = "renderWeather",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome;getPrecipitation(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/world/biome/Biome$Precipitation;")
    )
    private Biome.Precipitation forceSnow(Biome.Precipitation original) {
        BlockPos pos = MinecraftClient.getInstance().player.getBlockPos();
        if (MinecraftClient.getInstance().world.getBiome(pos).isIn(BiomeTags.IS_TAIGA)) {
            return Biome.Precipitation.SNOW;
        }
        return original;
    }
}
