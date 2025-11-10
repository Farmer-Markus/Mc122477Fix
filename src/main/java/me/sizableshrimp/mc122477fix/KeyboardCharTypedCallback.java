package me.sizableshrimp.mc122477fix;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.util.ActionResult;
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
                    ActionResult result = listener.onCharTyped(window, input);

                    if (result != ActionResult.PASS)
                        return result;
                }

                return ActionResult.PASS;
            });

    ActionResult onCharTyped(long window, CharInput input);
}
