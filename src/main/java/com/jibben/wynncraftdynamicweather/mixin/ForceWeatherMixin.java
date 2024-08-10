package com.jibben.wynncraftdynamicweather.mixin;

import com.jibben.wynncraftdynamicweather.WynncraftDynamicWeather;
import com.jibben.wynncraftdynamicweather.config.WeatherType;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Level.class)
public abstract class ForceWeatherMixin {
    @ModifyReturnValue(at = @At("RETURN"), method = "getRainLevel")
    public float changeToRain(float original) {
        if (WynncraftDynamicWeather.getWeatherType() == WeatherType.CLEAR) {
            return 0F;
        }
        if (WynncraftDynamicWeather.getWeatherType() == WeatherType.RAIN || WynncraftDynamicWeather.getWeatherType() == WeatherType.THUNDER) {
            return 1.0F;
        }
        else return original;
    }

    @ModifyReturnValue(at = @At("RETURN"), method = "getThunderLevel")
    public float changeToThunder(float original) {
        if (WynncraftDynamicWeather.getWeatherType() == WeatherType.CLEAR) {
            return 0F;
        }
        if (WynncraftDynamicWeather.getWeatherType() == WeatherType.THUNDER) {
            return 1.0F;
        }
        else return original;
    }


}
