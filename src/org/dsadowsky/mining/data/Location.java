package org.dsadowsky.mining.data;

import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;

public enum Location {
    POWERMINE(null, null, Rock.values()),
    LUMBRIDGESWAMP(Area.rectangular(new Position(3210, 3220, 2), new Position(3208,3217,2)), Area.rectangular(new Position(3231,3151,0), new Position(3223,3145,0)), Rock.Copper);
    private final Area bankArea, mineArea;
    private final Rock[] rocks;

    Location(final Area bankArea, final Area mineArea, final Rock... rocks) {
        this.bankArea = bankArea;
        this.mineArea = mineArea;
        this.rocks = rocks;
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
}
