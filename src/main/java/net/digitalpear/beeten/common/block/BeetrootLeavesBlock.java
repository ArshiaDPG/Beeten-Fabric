package net.digitalpear.beeten.common.block;

import net.minecraft.block.TintedParticleLeavesBlock;
import net.minecraft.particle.EntityEffectParticleEffect;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class BeetrootLeavesBlock extends TintedParticleLeavesBlock {

    public BeetrootLeavesBlock(float leafParticleChance, Settings settings) {
        super(leafParticleChance, settings);
    }

    @Override
    protected void spawnLeafParticle(World world, BlockPos pos, Random random) {
        EntityEffectParticleEffect entityEffectParticleEffect = EntityEffectParticleEffect.create(ParticleTypes.TINTED_LEAVES, this.getDefaultMapColor().color);
        ParticleUtil.spawnParticle(world, pos, random, entityEffectParticleEffect);
    }
}
