package tsp.asuna.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tsp.asuna.entity.BetterLightningEntity;

import java.util.List;

public class ThunderItem extends Item {

    public ThunderItem(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("Conjure a ball of venomous gas from poisonous particles").formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("from the atmosphere to launch at your foes").formatted(Formatting.GRAY));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        assert world != null;

        ItemStack itemStack = user.getStackInHand(hand);

        HitResult hitResult = user.rayTrace(50D,100F,true);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = ((BlockHitResult) hitResult);
            user.getItemCooldownManager().set(this, 20);

            if(!world.isClient) {
                BlockPos pos = blockHitResult.getBlockPos();
                ((ServerWorld) world).addLightning(new BetterLightningEntity(world, pos.getX(), pos.getY(), pos.getZ(), false));
            }
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack);
    }
}
