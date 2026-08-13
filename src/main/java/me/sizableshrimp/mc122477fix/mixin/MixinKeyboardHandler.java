package me.sizableshrimp.mc122477fix.mixin;

import me.sizableshrimp.mc122477fix.KeyboardCharTypedCallback;
import me.sizableshrimp.mc122477fix.KeyboardKeyPressedCallback;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.world.InteractionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(KeyboardHandler.class)
public class MixinKeyboardHandler {
    @Inject(method = "keyPress", at = @At("HEAD"), cancellable = true)
    private void KeyPressTest(long handle, int action, KeyEvent event, CallbackInfo ci)
    {
        InteractionResult result = KeyboardKeyPressedCallback.EVENT.invoker().onKeyPressed(handle, event);

        if (result == InteractionResult.FAIL)
            ci.cancel();
    }

    @Inject(method = "charTyped", at = @At("HEAD"), cancellable = true)
    private void KeyPressTest(long handle, CharacterEvent event, CallbackInfo ci)
    {
        InteractionResult result = KeyboardCharTypedCallback.EVENT.invoker().onCharTyped(handle, event);

        if (result == InteractionResult.FAIL)
            ci.cancel();
    }
}