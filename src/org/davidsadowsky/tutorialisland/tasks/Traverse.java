package org.davidsadowsky.tutorialisland.tasks;

import org.davidsadowsky.tutorialisland.Autotutorialisland;
import org.davidsadowsky.tutorialisland.data.Location;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Traverse extends Task {
    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && (traverseToFishingArea() || traverseToCookingArea());
    }

    @Override
    public int execute() {
        if (traverseToFishingArea()) {
            Autotutorialisland.location = Location.FISHING;
            Movement.walkTo(Autotutorialisland.location.getSecondaryArea().getCenter());
        }
        if (traverseToCookingArea()) {
            Log.info("test");
            Autotutorialisland.location = Location.COOKING;
            Movement.walkTo(Autotutorialisland.location.getSecondaryArea().getCenter());
        }
        return Random.nextInt(400, 600);
    }

    private boolean traverseToFishingArea() {
        return (Autotutorialisland.location.getName().equals("Display Name")) && Autotutorialisland.location.getPrimaryArea().contains(Players.getLocal()) && Autotutorialisland.isSettingsComplete;
    }

    private boolean traverseToCookingArea() {
        return (Autotutorialisland.location.getName().equals("Fishing Area")) && Autotutorialisland.location.getPrimaryArea().contains(Players.getLocal()) && Autotutorialisland.isCookingComplete;
    }
}