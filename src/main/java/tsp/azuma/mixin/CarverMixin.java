package tsp.azuma.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.carver.Carver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tsp.azuma.Azuma;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

@Mixin(Carver.class)
public abstract class CarverMixin {

    @Inject(at = @At(
            value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/world/chunk/Chunk;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)Lnet/minecraft/block/BlockState;",
            shift = At.Shift.AFTER),
            method = "carveAtPoint")
    private void carveAtPoint(Chunk chunk, Function<BlockPos, Biome> function_1, BitSet bitSet_1, Random random_1, BlockPos.Mutable pos, BlockPos.Mutable blockPos$Mutable_2, BlockPos.Mutable blockPos$Mutable_3, int int_1, int int_2, int int_3, int int_4, int int_5, int int_6, int int_7, int int_8, AtomicBoolean atomicBoolean_1, CallbackInfoReturnable<Boolean> cir) {
        BlockPos originPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        ChunkPos chunkPos = new ChunkPos(originPos);

        if(Azuma.caveAirList.containsKey(chunkPos)) {
            Azuma.caveAirList.get(chunkPos).add(originPos);
        } else {
            Azuma.caveAirList.put(chunkPos, new ArrayList<>(Collections.singletonList(originPos)));
        }
    }
}
