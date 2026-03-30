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
    @Inject(method = "keyPress", at = @At(value = "FIELD", target = "Lnet/minecraft/client/KeyboardHandler;debugCrashKeyTime:J", ordinal = 0), cancellable = true)
    private void injectOnKey(long window, int action, KeyEvent input, CallbackInfo ci) {
        InteractionResult result = KeyboardKeyPressedCallback.EVENT.invoker().onKeyPressed(window, input);

        if (result == InteractionResult.FAIL)
            ci.cancel();
    }

    @Inject(method = "charTyped", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;screen:Lnet/minecraft/client/gui/screens/Screen;", ordinal = 0), cancellable = true)
    private void injectOnChar(long window, CharacterEvent input, CallbackInfo ci) {
        InteractionResult result = KeyboardCharTypedCallback.EVENT.invoker().onCharTyped(window, input);

        if (result == InteractionResult.FAIL)
            ci.cancel();
    }
}


/*
@Mixin(Keyboard.class)
public class MixinKeyboard {
    @Inject(method = "onKey", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Keyboard;debugCrashStartTime:J", ordinal = 0), cancellable = true)
    private void injectOnKey(long window, int key, int scancode, int i, int j, CallbackInfo ci) {
        ActionResult result = KeyboardKeyPressedCallback.EVENT.invoker().onKeyPressed(window, key, scancode, i, j);

        if (result == ActionResult.FAIL)
            ci.cancel();
    }

    @Inject(method = "onChar", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;currentScreen:Lnet/minecraft/client/gui/screen/Screen;", ordinal = 0), cancellable = true)
    private void injectOnChar(long window, int i, int j, CallbackInfo ci) {
        ActionResult result = KeyboardCharTypedCallback.EVENT.invoker().onCharTyped(window, i, j);

        if (result == ActionResult.FAIL)
            ci.cancel();
    }
}

 */