package dev.ultimatchamp.mutils.mixin;

import com.mojang.blaze3d.platform.Window;
import dev.ultimatchamp.mutils.config.ModpackUtilsConfig;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Window.class)
public class WindowMixin {
    @ModifyArg(method = "setTitle", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwSetWindowTitle(JLjava/lang/CharSequence;)V"), index = 1)
    private CharSequence mutils$setTitle(CharSequence title) {
        if (ModpackUtilsConfig.instance().customTitle) {
            return ModpackUtilsConfig.instance().title
                    .replaceAll("<modpack-name>", ModpackUtilsConfig.instance().modpackName)
                    .replaceAll("<mc>", Minecraft.getInstance().getLaunchedVersion())
                    .replaceAll("<version>", ModpackUtilsConfig.instance().localVersion);
        }
        return title;
    }
}
