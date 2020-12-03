package org.davidsadowsky.mining.data;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

public enum Rock {
    COPPER(new int[] { 10943, 11161 }, "Copper ore", 1),
    TIN(new int[] { 11360, 11361 }, "Tin ore", 1),
    COPPERANDTIN(new int[] { 10943, 11161, 11360, 11361 }, "Copper/Tin ore", 1),
    CLAY(new int[] { 11362, 11363 }, "Clay", 1),
    IRON(new int[] { 11364, 11365 }, "Iron ore", 15),
    GOLD(new int[] { 11370, 11371 }, "Gold ore", 40);
    private final int[] rockIds;
    private final String name;
    private final int requiredLevel;

    private Rock(int[] rockIds, String oreName, int requiredLevel) {
        this.rockIds = rockIds;
        this.name = oreName;
        this.requiredLevel = requiredLevel;
    }

    public int[] getRockIds() { return rockIds; };
    public String getName() { return name; };
    public int getRequiredLevel() { return requiredLevel; };
    public boolean isAccessible() {
        return Skills.getCurrentLevel(Skill.MINING) >= requiredLevel;
    }
}