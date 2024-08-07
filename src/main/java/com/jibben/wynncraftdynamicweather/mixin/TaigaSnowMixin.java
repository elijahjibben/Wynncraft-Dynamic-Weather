package com.jibben.wynncraftdynamicweather.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(Biome.class)
public class TaigaSnowMixin {

    @ModifyReturnValue(method = "getPrecipitation", at = @At("RETURN"))
    private Biome.Precipitation getPrecipitation(Biome.Precipitation original, BlockPos pos) {
        if (MinecraftClient.getInstance().world.getBiome(pos).isIn(BiomeTags.IS_TAIGA)) {
            return Biome.Precipitation.SNOW;
        }
        return original;
    }

}
