package mods.grissess.data;

import net.minecraft.nbt.NBTTagCompound;

public enum BittingDescriptor {
    WOOD(4, 4),
    IRON(6, 5),
    GOLD(7, 6),
    DIAMOND(8, 8);

    public static final int MAX_POSITIONS = 8;
    public static final int MAX_SETTINGS = 8;

    public final int positions;
    public final int settings;

    BittingDescriptor(int positions, int settings) {
        this.positions = positions;
        this.settings = settings;
    }

    public String variant() {
        switch(this) {
            case WOOD:
                return "wood";

            case IRON:
                return "iron";

            case GOLD:
                return "gold";

            case DIAMOND:
                return "diamond";
        }
        
        return "wood";
    }
    
    public static BittingDescriptor fromVariant(String name) {
        switch(name) {
            case "wood":
                return WOOD;

            case "iron":
                return IRON;

            case "gold":
                return GOLD;

            case "diamond":
                return DIAMOND;
        }

        return WOOD;
    }

    public NBTTagCompound toNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("variant", variant());
        return tag;
    }

    public static BittingDescriptor fromNBT(NBTTagCompound tag) {
        return fromVariant(tag.getString("variant"));
    }
}
