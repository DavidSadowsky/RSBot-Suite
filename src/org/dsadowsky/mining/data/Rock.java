package org.dsadowsky.mining.data;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

public enum Rock {
    Copper(new int[] { 10943, 11161 }, "Copper ore", 1),
    Tin(new int[] { 11360, 11361 }, "Tin ore", 1);
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
