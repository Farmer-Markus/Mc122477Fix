package me.sizableshrimp.mc122477fix;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.input.KeyInput;
import net.minecraft.util.ActionResult;
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
                    ActionResult result = listener.onKeyPressed(window, input);

                    if (result != ActionResult.PASS)
                        return result;
                }

                return ActionResult.PASS;
            });

    ActionResult onKeyPressed(long window, KeyInput input);
}
