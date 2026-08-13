package me.sizableshrimp.mc122477fix;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StringUtil;
import net.minecraft.world.InteractionResult;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class Mc122477Fix implements ClientModInitializer {
    String lastKeyName;
    boolean lastGuiShown;

    @Override
    public void onInitializeClient() {
        // Key press events are always processed before char type events
        KeyboardKeyPressedCallback.EVENT.register((window, key) -> {
            if (key.isUp())
                return InteractionResult.PASS;

            // Save pressed key and ui state
            Minecraft client = Minecraft.getInstance();
            lastGuiShown = client.gui.screen() != null;
            lastKeyName = GLFW.glfwGetKeyName(key.key(),GLFW.glfwGetKeyScancode(key.key())); // Took me a while to figure out these conversions...
            return InteractionResult.PASS;
        });

        KeyboardCharTypedCallback.EVENT.register((window, keyChar) -> {
            Minecraft client = Minecraft.getInstance();
            // ui freshly opened?
            if (client.gui.screen() != null && !lastGuiShown) {
                String kChar = StringUtil.filterText(keyChar.codepointAsString());
                if(lastKeyName.equals(kChar))
                    return InteractionResult.FAIL;
            }

            return InteractionResult.PASS;
        });
    }
}
