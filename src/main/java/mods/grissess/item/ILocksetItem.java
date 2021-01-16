package mods.grissess.item;

import mods.grissess.data.LocksetBitting;
import net.minecraft.item.ItemStack;

public interface ILocksetItem {
    static LocksetBitting getBitting(ItemStack stack) {
        if(stack == null) return null;
        if(!stack.hasTagCompound()) return null;
        return LocksetBitting.fromNBT(stack.getTagCompound());
    }

    static void setBitting(ItemStack stack, LocksetBitting bitting) {
        stack.setTagCompound(bitting.toNBT());
    }
}
