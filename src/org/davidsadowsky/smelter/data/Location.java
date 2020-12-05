package org.davidsadowsky.smelter.data;

import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;

public enum Location {
    // TODO: Replace dummy data in enum
    LUMBRIDGE("Lumbridge", Area.rectangular(new Position(3210, 3220, 2), new Position(3208,3217,2)), Area.rectangular(new Position(3225,3255,0), new Position(3228,3253,0)), Bar.values());
    private final Area bankArea, furnaceArea;
    private final Bar[] bars;
    private final String name;

    Location(String name, final Area bankArea, final Area furnaceArea, final Bar... bars) {
        this.bankArea = bankArea;
        this.furnaceArea = furnaceArea;
        this.bars = bars;
        this.name = name;
    }

    public Area getBankArea() {
        return bankArea;
    }
    public Area getFurnaceArea() {
        return furnaceArea;
    }
    public Bar[] getBars() {
        return bars;
    }
    public String getName() { return name; }
}