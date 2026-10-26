package dev.prettyhud;

import com.mojang.blaze3d.platform.InputConstants;
import dev.prettyhud.config.HudConfig;
import dev.prettyhud.fx.Effects;
import dev.prettyhud.gui.MenuScreen;
import dev.prettyhud.hud.HudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class PrettyHudClient implements ClientModInitializer {
    private static KeyMapping openMenu;

    @Override
    public void onInitializeClient() {
        HudConfig.load();
        HudRenderer.register();
        Effects.register();

        openMenu = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.prettyhud.menu", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, KeyMapping.Category.MISC));

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            while (openMenu.consumeClick()) {
                if (mc.screen == null) mc.setScreen(new MenuScreen());
            }
        });
    }
}
