package com.jibben.wynncraftdynamicweather.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class TaigaSnowMixin {
    @Inject(method = "getPrecipitation", at = @At("HEAD"), cancellable = true)
    private void getPrecipitation(BlockPos pos, CallbackInfoReturnable<Biome.Precipitation> cir) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.world.getBiome(pos).isIn(BiomeTags.IS_TAIGA)) {
            cir.setReturnValue(Biome.Precipitation.SNOW);
        }

    }
}
