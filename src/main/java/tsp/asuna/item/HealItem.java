package tsp.asuna.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HealItem extends Item {

    public HealItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        assert world != null;

        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, 100);
        ParticleEffect particle = ParticleTypes.HEART;
        
        double userParticlesPosX = user.getX();
        double userParticlesPosY = user.getY();
        double userParticlesPosZ = user.getZ();

        world.addParticle(particle, userParticlesPosX, userParticlesPosY, userParticlesPosZ,0,0,0);
        world.addParticle(particle,userParticlesPosX + world.getRandom().nextDouble(),userParticlesPosY + world.getRandom().nextDouble(),userParticlesPosZ + world.getRandom().nextDouble(),0,0,0);
        world.addParticle(particle,userParticlesPosX + world.getRandom().nextDouble(),userParticlesPosY + world.getRandom().nextDouble(),userParticlesPosZ + world.getRandom().nextDouble(),0,0,0);
        world.addParticle(particle,userParticlesPosX + world.getRandom().nextDouble(),userParticlesPosY + world.getRandom().nextDouble(),userParticlesPosZ + world.getRandom().nextDouble(),0,0,0);
        world.addParticle(particle,userParticlesPosX + world.getRandom().nextDouble(),userParticlesPosY + world.getRandom().nextDouble(),userParticlesPosZ + world.getRandom().nextDouble(),0,0,0);

        if (!world.isClient) {
           user.heal(4);
        }

        return TypedActionResult.success(itemStack);
    }
}

