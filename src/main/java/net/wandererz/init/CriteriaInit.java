package net.wandererz.init;

import net.wandererz.criteria.WandererZCriterion;
import net.wandererz.criteria.SkillCriterion;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.scoreboard.ScoreboardCriterion;

public class CriteriaInit {

    public static final WandererZCriterion LEVEL_UP = Criteria.register(new WandererZCriterion());
    public static final SkillCriterion SKILL_UP = Criteria.register(new SkillCriterion());
    public static final ScoreboardCriterion WANDERERZ = ScoreboardCriterion.create("wandererz");

    public static void init() {
    }

}
