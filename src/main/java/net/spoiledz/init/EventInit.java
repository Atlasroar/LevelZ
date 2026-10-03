package net.spoiledz.init;

import de.siphalor.capsaicin.api.food.FoodContext;
import de.siphalor.capsaicin.api.food.FoodEvents;
import de.siphalor.capsaicin.api.food.FoodModifications;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.spoiledz.util.SpoiledUtil;

public class EventInit {

    // How often (in ticks) online players' inventories are scanned for fully spoiled food.
    private static final int SPOIL_CHECK_INTERVAL = 100;

    public static void init() {

        // Negative effects only apply once a food item has 25% freshness or less remaining
        // (spoiledTime 3 = 25% fresh, spoiledTime 4 = 0% fresh), and are always the same -
        // no randomness in whether they apply or how strong they are.
        FoodEvents.EATEN.on((event) -> {
            FoodContext context = event.context();
            if (context.stack() != null && context.user() != null) {
                if (!context.user().getWorld().isClient() && SpoiledUtil.getSpoilingTime(context.user().getWorld(), context.stack()) >= 0) {
                    int spoiledTime = SpoiledUtil.getSpoilingTime(context.user().getWorld(), context.stack());

                    if (spoiledTime == 3) {
                        context.user().addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, ConfigInit.CONFIG.effectDuration, 0));
                    } else if (spoiledTime >= 4) {
                        context.user().addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, ConfigInit.CONFIG.effectDuration, 0));
                        context.user().addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, ConfigInit.CONFIG.effectDuration, 1));
                    }
                }
            }
        });
        FoodModifications.PROPERTIES_MODIFIERS.register((foodProperties, context) -> {
            ItemStack stack = context.stack();
            if (stack == null || context.user() == null || !stack.hasNbt()) {
                return foodProperties;
            }
            if (SpoiledUtil.getSpoilingTime(context.user().getWorld(), context.stack()) >= 0) {
                int spoiledTime = SpoiledUtil.getSpoilingTime(context.user().getWorld(), context.stack());
                if (spoiledTime == 3) {
                    foodProperties.setHunger((int) (foodProperties.getHunger() * 0.8f));
                } else if (spoiledTime >= 4) {
                    foodProperties.setHunger(foodProperties.getHunger() / 2);
                }
            }
            return foodProperties;
        }, new Identifier("spoiledz", "spoiling"));

        // Periodically swap any fully spoiled (0% freshness) food in a player's inventory for rotten flesh.
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (server.getTicks() % SPOIL_CHECK_INTERVAL != 0) {
                return;
            }
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                PlayerInventory inventory = player.getInventory();
                for (int i = 0; i < inventory.size(); i++) {
                    ItemStack stack = inventory.getStack(i);
                    if (SpoiledUtil.isFullySpoiled(player.getWorld(), stack)) {
                        inventory.setStack(i, SpoiledUtil.getRottenFleshReplacement(stack));
                    }
                }
            }
        });
    }

}
