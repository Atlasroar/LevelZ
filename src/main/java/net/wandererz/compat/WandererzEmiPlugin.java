package net.wandererz.compat;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.recipe.EmiBrewingRecipe;
import net.wandererz.init.ItemInit;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class WandererzEmiPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        registry.addRecipe(new EmiBrewingRecipe(EmiStack.of(Items.DRAGON_BREATH), EmiStack.of(Items.NETHER_STAR), EmiStack.of(ItemInit.STRANGE_POTION), new Identifier("wandererz", "strange_potion")));
    }

}
