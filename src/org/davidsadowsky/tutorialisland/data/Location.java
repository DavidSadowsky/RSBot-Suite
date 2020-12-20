package org.davidsadowsky.tutorialisland.data;

import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;

public enum Location {
    // TODO: Replace dummy data in enum
    START("Display Name", Area.rectangular(new Position(3091, 3110, 0), new Position(3096,3105,0)), Area.rectangular(new Position(3091, 3110, 0), new Position(3096,3105,0))),
    FISHING("Fishing Area", Area.rectangular(new Position(3105, 3091, 0), new Position(3098,3099,0)), Area.rectangular(new Position(3104,3096,0), new Position(3099,3092,0))),
    COOKING("Cooking Area", Area.rectangular(new Position(3078, 3083, 0), new Position(3074,3091,0)), Area.rectangular(new Position(3075,3082,0), new Position(3078,3085,0))),
    QUEST("Quest Guide Area", Area.rectangular(new Position(3088, 3125, 0), new Position(3083,3120,0)), Area.rectangular(new Position(3088,3125,0), new Position(3083,3120,0))),
    MINING("Mining Area", Area.rectangular(new Position(3210, 3220, 2), new Position(3208,3217,2)), Area.rectangular(new Position(3225,3255,0), new Position(3228,3253,0))),
    COMBAT("Combat Area", Area.rectangular(new Position(3210, 3220, 2), new Position(3208,3217,2)), Area.rectangular(new Position(3225,3255,0), new Position(3228,3253,0))),
    BANK("Bank Area", Area.rectangular(new Position(3210, 3220, 2), new Position(3208,3217,2)), Area.rectangular(new Position(3225,3255,0), new Position(3228,3253,0))),
    PRAYER("Prayer Area", Area.rectangular(new Position(3210, 3220, 2), new Position(3208,3217,2)), Area.rectangular(new Position(3225,3255,0), new Position(3228,3253,0))),
    MAGIC("Magic Area", Area.rectangular(new Position(3210, 3220, 2), new Position(3208,3217,2)), Area.rectangular(new Position(3225,3255,0), new Position(3228,3253,0)));
    private final Area primaryArea, secondaryArea;
    private final String name;

    Location(String name, final Area primaryArea, final Area secondaryArea) {
        this.primaryArea = primaryArea;
        this.secondaryArea = secondaryArea;
        this.name = name;
    }

    public Area getPrimaryArea() {
        return primaryArea;
    }
    public Area getSecondaryArea() {
        return secondaryArea;
    }
    public String getName() { return name; }
}