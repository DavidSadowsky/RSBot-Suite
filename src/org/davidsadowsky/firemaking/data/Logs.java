package org.davidsadowsky.firemaking.data;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.ui.Log;

public enum Logs {
    LOGS("Logs", 1, 1511),
    OAK("Oak logs", 15, 1521),
    WILLOW("Willow logs", 30, 1519),
    BESTAVAILABLE("BESTAVAILABLE", 1, -1);
    private final String name;
    private final int logID;
    private final int requiredLevel;

    Logs(String name, int requiredLevel, int logID) {
        if(name.equals("BESTAVAILABLE")) {
            if(Skills.getCurrentLevel(Skill.FIREMAKING) >= 30) {
                this.name = "Willow logs";
                this.logID = 1519;
                this.requiredLevel = 30;
            }
            else if(Skills.getCurrentLevel(Skill.FIREMAKING) >= 15) {
                this.name = "Oak logs";
                this.logID = 1521;
                this.requiredLevel = 15;
            }
            else {
                this.name = "Logs";
                this.logID = 1511;
                this.requiredLevel = 1;
            }
        }
        else {
            this.name = name;
            this.logID = logID;
            this.requiredLevel = requiredLevel;
        }
    }

    public String getName() { return name; };
    public int getLogID() { return logID; };
    public int getRequiredLevel() { return requiredLevel; };
    public boolean isAccessible() {
        return Skills.getCurrentLevel(Skill.FIREMAKING) >= requiredLevel;
    }
}