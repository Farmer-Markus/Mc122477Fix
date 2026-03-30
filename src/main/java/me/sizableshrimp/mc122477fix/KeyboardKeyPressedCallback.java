package me.sizableshrimp.mc122477fix;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.world.InteractionResult;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

/**
 * Callback for pressing a key on the keyboard.
 * This event happens before char typed events in {@link KeyboardCharTypedCallback} are processed, if at all.
 * Called before the key press is processed.
 *
 * <p>Upon return:
 * <ul><li>SUCCESS cancels further listener processing and forwards the key press to be processed by the client.
 * <li>PASS falls back to further processing.
 * <li>FAIL cancels further processing and does not forward the key press.</ul>
 */
public interface KeyboardKeyPressedCallback {
    Event<KeyboardKeyPressedCallback> EVENT = EventFactory.createArrayBacked(KeyboardKeyPressedCallback.class,
            listeners -> (window, input) -> {
                for (KeyboardKeyPressedCallback listener : listeners) {
                    InteractionResult result = listener.onKeyPressed(window, input);

                    if (result != InteractionResult.PASS)
                        return result;
                }

                return InteractionResult.PASS;
            });

    InteractionResult onKeyPressed(long window, KeyEvent input);
}
