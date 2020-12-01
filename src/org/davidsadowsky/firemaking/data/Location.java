package org.davidsadowsky.firemaking.data;

import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;

public enum Location {
    DRAYNOR("Draynor", Area.rectangular(new Position(3095, 3246, 0), new Position(3092,3241,0)), Area.rectangular(new Position(3102,3239,0), new Position(3085,3235,0)), Logs.values()),
    /*TODO*/ GE("General Exchange", Area.rectangular(new Position(3047, 3234, 0), new Position(3043,3236,0)), Area.rectangular(new Position(2973,3244,0), new Position(2967,3235,0)), Logs.values());
    private final Area bankArea, lightingArea;
    private final Logs[] logs;
    private final String name;

    Location(String name, final Area bankArea, final Area lightingArea, final Logs... logs) {
        this.bankArea = bankArea;
        this.lightingArea = lightingArea;
        this.logs = logs;
        this.name = name;
    }

    public Area getBankArea() {
        return bankArea;
    }
    public Area getLightingArea() {
        return lightingArea;
    }
    public Logs[] getLogs() {
        return logs;
    }
    public String getName() { return name; }
}