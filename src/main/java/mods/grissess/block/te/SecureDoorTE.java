package mods.grissess.block.te;

import mods.grissess.block.SecureDoor;
import mods.grissess.data.BittingDescriptor;
import mods.grissess.data.LocksetBitting;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SecureDoorTE extends TileEntity implements IBittedTE {
    public LocksetBitting bitting;

    public SecureDoorTE() {
        this(BittingDescriptor.WOOD);
    }

    public SecureDoorTE(BittingDescriptor desc) {
        bitting = new LocksetBitting(desc);
    }

    public SecureDoorTE(LocksetBitting bitting) {
        this.bitting = bitting;
    }

    public LocksetBitting getBitting() {
        return bitting;
    }

    public void setBitting(LocksetBitting bitting) {
        this.bitting = bitting;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound.hasKey("bitting")) {
            bitting = LocksetBitting.fromNBT(compound.getCompoundTag("bitting"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("bitting", bitting.toNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }
}