package org.davidsadowsky.mining.data;

import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;

public enum Location {
    POWERMINE("Powermine", false, null, null, Rock.values()),
    LUMBRIDGESWAMP("Lumbridge Swamp", false, Area.rectangular(new Position(3210, 3220, 2), new Position(3208,3217,2)), Area.rectangular(new Position(3231,3151,0), new Position(3223,3145,0)), Rock.values()),
    RIMMINGTON("Rimmington", true, Area.rectangular(new Position(3047, 3234, 0), new Position(3043,3236,0)), Area.rectangular(new Position(2973,3244,0), new Position(2967,3235,0)), Rock.IRON, Rock.GOLD, Rock.CLAY);
    private final Area bankArea, mineArea;
    private final Rock[] rocks;
    private final boolean isDepositBox;
    private final String name;

    Location(String name, boolean isDepositBox, final Area bankArea, final Area mineArea, final Rock... rocks) {
        this.bankArea = bankArea;
        this.mineArea = mineArea;
        this.rocks = rocks;
        this.isDepositBox = isDepositBox;
        this.name = name;
    }

    public Area getBankArea() {
        return bankArea;
    }
    public Area getMineArea() {
        return mineArea;
    }
    public Rock[] getRocks() {
        return rocks;
    }
    public boolean isDepositBox() { return isDepositBox; }
    public String getName() { return name; }
}