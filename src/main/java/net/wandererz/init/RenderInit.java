package net.wandererz.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.wandererz.entity.render.LevelExperienceOrbEntityRenderer;
import net.wandererz.screen.*;
import net.wandererz.screen.widget.WandererzTab;
import net.wandererz.screen.widget.VanillaInventoryTab;
import net.wandererz.util.TooltipUtil;
import net.libz.registry.TabRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RenderInit {

    public static final Identifier GUI_ICONS = new Identifier("wandererz:textures/gui/icons.png");
    public static final Identifier SKILL_TAB_ICON = new Identifier("wandererz:textures/gui/skill_tab_icon.png");
    public static final Identifier BAG_TAB_ICON = new Identifier("wandererz:textures/gui/bag_tab_icon.png");

    public static final Identifier MINEABLE_INFO = new Identifier("wandererz", "mineable_info");
    public static final Identifier MINEABLE_LEVEL_INFO = new Identifier("wandererz", "mineable_level_info");

    public static final boolean isInventorioLoaded = FabricLoader.getInstance().isModLoaded("inventorio");

    public static void init() {
        EntityRendererRegistry.register(EntityInit.LEVEL_EXPERIENCE_ORB, LevelExperienceOrbEntityRenderer::new);

        TabRegistry.registerInventoryTab(new VanillaInventoryTab(Text.translatable("container.crafting"), BAG_TAB_ICON, 0, InventoryScreen.class));
        TabRegistry.registerInventoryTab(new WandererzTab(Text.translatable("screen.wandererz.skill_screen"), SKILL_TAB_ICON, 1, SkillScreen.class, SkillInfoScreen.class, SkillListScreen.class));

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            TooltipUtil.renderTooltip(MinecraftClient.getInstance(), drawContext);
        });
    }
}
