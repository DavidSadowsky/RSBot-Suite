package org.davidsadowsky.mining.tasks;

import org.davidsadowsky.mining.Autominer;
import org.davidsadowsky.mining.data.Location;
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
        return (Autominer.rock != null && Autominer.location != null) && traverseToBank() || traverseToMine();
    }

    @Override
    public int execute() {
        if(traverseToBank()) {
            List<Position> tiles = Autominer.location.getBankArea().getTiles();
            Movement.walkTo(tiles.get(Random.nextInt(0, tiles.size() - 1)));
        }
        else {
            List<Position> tiles = Autominer.location.getMineArea().getTiles();
            Movement.walkTo(tiles.get(Random.nextInt(0, tiles.size() - 1)));
        }
        return Random.nextInt(400,600);
    }

    private boolean traverseToBank() {
        return Inventory.isFull() && !Autominer.location.getBankArea().contains(Players.getLocal()) && !Autominer.location.equals(Location.POWERMINE);
    }

    private boolean traverseToMine() {
        return (Autominer.rock != null && Autominer.location != null) && !Inventory.isFull() && !Autominer.location.getMineArea().contains(Players.getLocal());
    }
}