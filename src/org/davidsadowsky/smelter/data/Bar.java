package org.davidsadowsky.smelter.data;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

public enum Bar {
    // TODO: Replace dummy data in enum
    BRONZE("Bronze", new int[] { 438, 436 },-1, 270, 14, 1, new String[] { "Copper ore", "Tin ore" }),
    IRON("Iron", new int[] { 11360, 11361 }, -1, 270, 15, 15, "Iron ore");
    private final int[] oreIds;
    private final String[] oreNames;
    private final int requiredLevel;
    private final String name;
    private final int barId;
    private final int componentGroup;
    private final int componentNum;

    private Bar(String name, int[] oreIds, int barId, int componentGroup, int componentNum, int requiredLevel, final String... oreNames) {
        this.oreIds = oreIds;
        this.oreNames = oreNames;
        this.requiredLevel = requiredLevel;
        this.name = name;
        this.barId = barId;
        this.componentGroup = componentGroup;
        this.componentNum = componentNum;
    }

    public int[] getOreIds() { return oreIds; };
    public String getName() { return name; };
    public String[] getOreNames() { return oreNames; };
    public int getBarId() { return barId; };
    public int getRequiredLevel() { return requiredLevel; };
    public boolean isAccessible() {
        return Skills.getCurrentLevel(Skill.SMITHING) >= requiredLevel;
    }
    public int getComponentGroup() { return  componentGroup; };
    public int getComponentNum() { return componentNum; };
}