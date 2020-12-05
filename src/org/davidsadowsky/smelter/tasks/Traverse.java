package org.davidsadowsky.smelter.tasks;

import org.davidsadowsky.smelter.Autosmelter;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

import java.util.List;

public class Traverse extends Task {
    @Override
    public boolean validate() {
        return (Autosmelter.bar != null && Autosmelter.location != null) && traverseToBank() || traverseToFurnace();
    }

    @Override
    public int execute() {
        if(traverseToBank()) {
            Autosmelter.isSmelting = false;
            List<Position> tiles = Autosmelter.location.getBankArea().getTiles();
            Movement.walkTo(tiles.get(Random.nextInt(0, tiles.size() - 1)));
        }
        else {
            List<Position> tiles = Autosmelter.location.getFurnaceArea().getTiles();
            Movement.walkTo(tiles.get(Random.nextInt(0, tiles.size() - 1)));
        }
        return Random.nextInt(400,600);
    }

    private boolean traverseToBank() {
        return !Inventory.contains(Autosmelter.bar.getOreNames()) && Inventory.contains(Autosmelter.bar.getBarId()) && !Autosmelter.location.getBankArea().contains(Players.getLocal());
    }

    private boolean traverseToFurnace() {
        return (Autosmelter.bar != null && Autosmelter.location != null) && Inventory.isFull() && !Autosmelter.isSmelting && !Autosmelter.location.getFurnaceArea().contains(Players.getLocal());
    }
}