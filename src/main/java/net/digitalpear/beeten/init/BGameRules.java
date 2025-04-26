package net.digitalpear.beeten.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class BGameRules {

    public static final GameRules.Key<GameRules.IntRule> MAX_HEART_BEAT_CONSUMPTION = GameRuleRegistry.register("maxBeetHealthBoost", GameRules.Category.MOBS, GameRuleFactory.createIntRule(20));
    public static void init() {

    }
}
