package mods.grissess.block;

import mods.grissess.block.te.SecureBlockTE;
import mods.grissess.data.KeyBitting;
import mods.grissess.data.LocksetBitting;
import mods.grissess.item.Key;
import mods.grissess.registry.Items;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class SecureBlock extends Block {
    public SecureBlock() {
        super(Material.IRON);
        setUnlocalizedName("Secure Block");
        setRegistryName("secure_block");
        setBlockUnbreakable();
        setResistance(6000000f);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new SecureBlockTE();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.secure_block_item;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(SecureBlockBase.tryUnlock(worldIn, pos, playerIn, hand) == SecureBlockBase.TryUnlock.SUCCEEDED) {
            if(!worldIn.isRemote) {
                InventoryHelper.spawnItemStack(
                        worldIn,
                        pos.getX(), pos.getY(), pos.getZ(),
                        new ItemStack(Items.secure_block_item)
                );
                worldIn.setBlockToAir(pos);
            }
            return true;
        }
        return false;
    }
}