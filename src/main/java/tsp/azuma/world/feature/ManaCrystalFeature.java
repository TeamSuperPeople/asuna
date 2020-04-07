package tsp.azuma.world.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import tsp.azuma.Azuma;
import tsp.azuma.registry.Blocks;

import java.util.ArrayList;
import java.util.Random;

public class ManaCrystalFeature extends Feature<DefaultFeatureConfig> {

    public ManaCrystalFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        if(Azuma.caveAirList.containsKey(new ChunkPos(pos))) {
            ArrayList<BlockPos> cavePositions = Azuma.caveAirList.get(new ChunkPos(pos));

            if(cavePositions.size() > 0) {
                BlockPos randomPos = cavePositions.get(world.getRandom().nextInt(cavePositions.size()));

                while(world.getBlockState(randomPos.down()).isAir() && cavePositions.size() > 0) {
                    randomPos = cavePositions.get(world.getRandom().nextInt(cavePositions.size()));
                    cavePositions.remove(randomPos);
                }

                if(cavePositions.size() > 0) {
                    world.setBlockState(randomPos, Blocks.MANA_CRYSTAL.getDefaultState(), 3);
                }
            }

            Azuma.caveAirList.remove(new ChunkPos(pos));
        }

        return false;
    }
}
