package com.jibben.wynncraftdynamicweather.mixin;

import com.jibben.wynncraftdynamicweather.WynncraftDynamicWeather;
import com.jibben.wynncraftdynamicweather.config.WeatherType;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(World.class)
public class ForceWeatherMixin {

    @ModifyReturnValue(method = "getRainGradient", at = @At("RETURN"))
    public float getRainGradient(float original) {
        WeatherType type = WynncraftDynamicWeather.weatherType;

        if (type == WeatherType.CLEAR) {
            return 0F;
        }
        else if (type == WeatherType.RAIN || type == WeatherType.THUNDER) {
            return 1F;
        }
        else return original;
    }

    @ModifyReturnValue(method = "getThunderGradient", at = @At("RETURN"))
    public float getThunderGradient(float original) {
        WeatherType type = WynncraftDynamicWeather.weatherType;

        if (type == WeatherType.CLEAR) {
            return 0F;
        }
        else if (type == WeatherType.THUNDER) {
            return 1F;
        }
        else return original;
    }
}
