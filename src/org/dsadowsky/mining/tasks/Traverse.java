package org.dsadowsky.mining.tasks;

import org.dsadowsky.mining.Autominer;
import org.dsadowsky.mining.data.Location;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Traverse extends Task {
    @Override
    public boolean validate() {
        return traverseToBank() || traverseToMine();
    }

    @Override
    public int execute() {
        Movement.walkTo(traverseToBank() ? Autominer.location.getBankArea().getCenter() : Autominer.location.getMineArea().getCenter());
        return Random.nextInt(400,600);
    }

    private boolean traverseToBank() {
        return Inventory.isFull() && !Autominer.location.getBankArea().contains(Players.getLocal()) && !Autominer.location.equals(Location.POWERMINE);
    }

    private boolean traverseToMine() {
        return !Inventory.isFull() && !Autominer.location.getMineArea().contains(Players.getLocal());
    }
}
