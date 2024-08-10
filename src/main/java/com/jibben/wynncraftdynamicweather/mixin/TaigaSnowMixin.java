package com.jibben.wynncraftdynamicweather.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;


@Mixin(Biome.class)
@Environment(EnvType.CLIENT)
public class TaigaSnowMixin {
    @ModifyReturnValue(method = "getPrecipitationAt", at = @At("RETURN"))
    private Biome.Precipitation getPrecipitation(Biome.Precipitation original, BlockPos pos) {
        if (Minecraft.getInstance().level.getBiome(pos).is(BiomeTags.IS_TAIGA))  {
            return Biome.Precipitation.SNOW;
        }
        return original;
    }
}