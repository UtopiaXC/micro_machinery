package com.dbydd.micro_machinery.recipes;

import com.dbydd.micro_machinery.init.ModRecipes;
import com.dbydd.micro_machinery.items.ItemCast;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeHelper {

    public static KlinRecipe GetKlinRecipe(ItemStack stackInSlot, ItemStack stackInSlot1) {
        for (KlinRecipe tofluidrecipe : ModRecipes.tofluidrecipes) {
            if (areItemStackEqual(stackInSlot, tofluidrecipe.input1) && areItemStackEqual(stackInSlot1, tofluidrecipe.input2) || (areItemStackEqual(stackInSlot1, tofluidrecipe.input1) && areItemStackEqual(stackInSlot, tofluidrecipe.input2))) {
                if ((compareItemStackCont(stackInSlot, tofluidrecipe.input1) && compareItemStackCont(stackInSlot1, tofluidrecipe.input2)) || (compareItemStackCont(stackInSlot, tofluidrecipe.input2) && compareItemStackCont(stackInSlot1, tofluidrecipe.input1)))
                    return tofluidrecipe;
            }
        }
        return null;
    }

    public static KlinFluidRecipe GetKlinFluidRecipe(FluidStack fluidStack, ItemStack castslot) {
        Item cast = castslot.getItem();
        if (cast instanceof ItemCast) {
            for (KlinFluidRecipe recipe : ModRecipes.fluidRecipes) {
                if (fluidStack.getFluid() == recipe.inputfluid && recipe.cast.canpouring(fluidStack.amount) && recipe.cast.type == ((ItemCast) cast).type)
                    return recipe;
            }
        }
        return null;
    }


    private static boolean areItemStackEqual(ItemStack stackA, ItemStack stackB) {
        return stackA.getItem() == stackB.getItem() && stackA.getMetadata() == stackB.getMetadata();
    }

    private static boolean compareItemStackCont(ItemStack stackA, ItemStack stackB) {
        return stackA.getCount() >= stackB.getCount();
    }

}

