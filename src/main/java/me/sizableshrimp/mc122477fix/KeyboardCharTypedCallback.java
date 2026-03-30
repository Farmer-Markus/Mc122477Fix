package me.sizableshrimp.mc122477fix;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.world.InteractionResult;
import org.lwjgl.glfw.GLFWCharModsCallbackI;

/**
 * Callback for typing a character in a GUI.
 * Called before the character is processed.
 *
 * <p>Upon return:
 * <ul><li>SUCCESS cancels further listener processing and forwards the character to be processed by the client.
 * <li>PASS falls back to further processing.
 * <li>FAIL cancels further processing and does not forward the character.</ul>
 */
public interface KeyboardCharTypedCallback {
    Event<KeyboardCharTypedCallback> EVENT = EventFactory.createArrayBacked(KeyboardCharTypedCallback.class,
            listeners -> (window, input) -> {
                for (KeyboardCharTypedCallback listener : listeners) {
                    InteractionResult result = listener.onCharTyped(window, input);

                    if (result != InteractionResult.PASS)
                        return result;
                }

                return InteractionResult.PASS;
            });

    InteractionResult onCharTyped(long window, CharacterEvent input);
}
