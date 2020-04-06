package tsp.asuna.item;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.LongTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tsp.asuna.api.ManaConnectable;

public class CrystalLinkerItem extends Item {

    public static final String SUBTAG_KEY = "ManaLink";
    public static final String ORIGIN_KEY = "OriginPos";

    public CrystalLinkerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.isSneaking()) {
            user.getStackInHand(hand).getOrCreateSubTag(SUBTAG_KEY).remove(ORIGIN_KEY);
        }

        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context == null || context.getWorld().isClient) {
            return ActionResult.PASS;
        }

        ItemStack thisStack = context.getStack();
        BlockPos thisPos = context.getBlockPos();
        World world = context.getWorld();

        // check current spot
        BlockEntity currentBlockEntity = world.getBlockEntity(thisPos);
        if (!(currentBlockEntity instanceof ManaConnectable)) {
            return ActionResult.FAIL;
        }

        // contains origin key, we're setting the target
        if(thisStack.getOrCreateSubTag(SUBTAG_KEY).contains(ORIGIN_KEY)) {
            BlockPos originPos = BlockPos.fromLong(((LongTag) thisStack.getOrCreateSubTag(SUBTAG_KEY).get(ORIGIN_KEY)).getLong());

            // verify we're not going to the same spot
            if(originPos.equals(thisPos)) {
                return ActionResult.FAIL;
            }

            // verify original BE stil lexists
            BlockEntity originBlockEntity = world.getBlockEntity(originPos);
            if (originBlockEntity instanceof ManaConnectable) {
                ManaConnectable connectionOrigin = (ManaConnectable) originBlockEntity;
                connectionOrigin.addTarget((ManaConnectable) currentBlockEntity);
            }

            thisStack.getOrCreateSubTag(SUBTAG_KEY).remove(ORIGIN_KEY);
        }

        // setting origin
        else {
            thisStack.getOrCreateSubTag(SUBTAG_KEY).put(ORIGIN_KEY, LongTag.of(thisPos.asLong()));
        }

        return super.useOnBlock(context);
    }
}
