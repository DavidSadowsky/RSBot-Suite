package org.davidsadowsky.tutorialisland.tasks;

import org.davidsadowsky.tutorialisland.Autotutorialisland;
import org.davidsadowsky.tutorialisland.data.Location;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.List;

public class Traverse extends Task {
    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && traverseToFishingArea();
    }

    @Override
    public int execute() {
        if(traverseToFishingArea()) {
            Autotutorialisland.location = Location.FISHING;
            List<Position> tiles = Autotutorialisland.location.getSecondaryArea().getTiles();
            Movement.walkTo(tiles.get(Random.nextInt(0, tiles.size() - 1)));
        }
        return Random.nextInt(400,600);
    }

    private boolean traverseToFishingArea() {
        return (Autotutorialisland.location.getName().equals("Display Name")) && Autotutorialisland.location.getPrimaryArea().contains(Players.getLocal()) && Autotutorialisland.isSettingsComplete;
    }
}