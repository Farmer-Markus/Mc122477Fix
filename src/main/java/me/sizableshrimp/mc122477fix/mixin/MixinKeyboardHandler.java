package me.sizableshrimp.mc122477fix.mixin;

import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


// Would be better to overwrite the "setup" function, but I don't know how.
// Asking Ai will result in something I don't understand and thus will not be used
@Mixin(KeyboardHandler.class)
public class MixinKeyboardHandler {
    @Shadow
    @Final
    private Minecraft minecraft;
    @Unique
    private boolean isIngame;


    @Inject(method = "keyPress", at = @At("HEAD"))
    private void keyPressHead(final long handle, final int action, final KeyEvent event, CallbackInfo ci)
    {
        // Save before handling key event
        if (action == GLFW.GLFW_PRESS)
            isIngame = (this.minecraft.gui.screen() == null);
    }

    @Inject(method = "charTyped", at = @At("HEAD"), cancellable = true)
    private void charTypedHead(final long handle, final CharacterEvent event, CallbackInfo ci)
    {
        // If gui opened since last key event -> ignore char event
        if (isIngame && this.minecraft.gui.screen() != null) {
            isIngame = false;
            ci.cancel(); // Cancel method
        }
    }
}