package tsp.asuna.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class ThunderbandItem  extends SwordItem {

 public ThunderbandItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
  super(material, attackDamage, attackSpeed, settings);
 }

 @Override
 public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
  StatusEffectInstance haste = new StatusEffectInstance(StatusEffects.HASTE, 100);
  StatusEffectInstance speed = new StatusEffectInstance(StatusEffects.SPEED, 100);

  double hitRate = MinecraftClient.getInstance().world.getRandom().nextDouble();

  if (hitRate < 0.5) {


      MinecraftClient.getInstance().world.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
         attacker.addStatusEffect(haste);
         attacker.addStatusEffect(speed);
         target.damage(DamageSource.mob(attacker),5);
  }


  return super.postHit(stack, target, attacker);
 }
}
