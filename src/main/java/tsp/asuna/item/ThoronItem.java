package tsp.asuna.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import tsp.asuna.entities.ThoronEntity;

import java.util.List;

public class ThoronItem extends Item {
    public ThoronItem(Settings settings) {
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
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, 20);

        if (!world.isClient) {
            ThoronEntity thoronEntity = new ThoronEntity(world, user);
            ThoronEntity thoronEntity1 = new ThoronEntity(world, user);
            ThoronEntity thoronEntity2 = new ThoronEntity(world, user);
            ThoronEntity thoronEntity3 = new ThoronEntity(world, user);
            ThoronEntity thoronEntity4 = new ThoronEntity(world, user);

            thoronEntity.setProperties(user, user.pitch, user.yaw, 0F, 2, 0);
            thoronEntity1.setProperties(user, user.pitch, user.yaw, 0F, 2, 0);
            thoronEntity2.setProperties(user, user.pitch, user.yaw, 0F, 2, 0);
            thoronEntity3.setProperties(user, user.pitch, user.yaw, 0F, 2, 0);
            thoronEntity4.setProperties(user, user.pitch, user.yaw, 0F, 2, 0);

            thoronEntity.setPos(user.getX(),user.getY()+2,user.getZ());
            thoronEntity1.setPos(user.getX(),user.getY()+2,user.getZ());
            thoronEntity2.setPos(user.getX(),user.getY()+2,user.getZ());
            thoronEntity3.setPos(user.getX(),user.getY()+2,user.getZ());
            thoronEntity4.setPos(user.getX(),user.getY()+2,user.getZ());

            world.spawnEntity(thoronEntity);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            world.spawnEntity(thoronEntity1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            world.spawnEntity(thoronEntity2);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            world.spawnEntity(thoronEntity3);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            world.spawnEntity(thoronEntity4);


        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack);
    }
}



