package tsp.asuna.world.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import tsp.asuna.Asuna;
import tsp.asuna.registry.Blocks;

import java.util.ArrayList;
import java.util.Random;

public class ManaCrystalFeature extends Feature<DefaultFeatureConfig> {

    public ManaCrystalFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        if(Asuna.caveAirList.containsKey(world.getChunk(pos).getPos())) {
            ArrayList<BlockPos> cavePositions = Asuna.caveAirList.get(world.getChunk(pos).getPos());

            if(cavePositions.size() > 0) {
                BlockPos randomPos = cavePositions.get(world.getRandom().nextInt(cavePositions.size()));

                while(world.getBlockState(randomPos.down()).isAir()) {
                    randomPos = cavePositions.get(world.getRandom().nextInt(cavePositions.size()));
                }

                world.setBlockState(randomPos, Blocks.MANA_CRYSTAL.getDefaultState(), 3);
            }

            Asuna.caveAirList.remove(world.getChunk(pos).getPos());
        }

        return false;
    }
}
