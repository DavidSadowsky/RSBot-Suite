package org.davidsadowsky.firemaking.tasks;

import org.davidsadowsky.firemaking.Autofiremaker;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.*;
import java.util.function.BooleanSupplier;

public class Traverse extends Task {

    @Override
    public boolean validate() {
        return (Autofiremaker.logs != null && Autofiremaker.location != null) && traverseToBank() || traverseToLane();
    }

    @Override
    public int execute() {
        if (traverseToBank()) {
            Autofiremaker.inOpenLane = false;
            List<Position> tiles = Autofiremaker.location.getBankArea().getTiles();
            if (!Players.getLocal().isAnimating() && !Players.getLocal().isMoving()) Movement.walkTo(tiles.get(Random.nextInt(0, tiles.size() - 1)));
        } else {
            Log.info("Assessing accessible lanes...");
            final HashMap<Integer, Boolean> laneData = new HashMap<>();
            int y = -1, x = -1;
            final List<Position> tiles = Autofiremaker.location.getLightingArea().getTiles();

            for (Position tile : tiles) {
                laneData.put(tile.getY(), true);
            }

            for (Position tile : tiles) {
                if (SceneObjects.getFirstAt(tile) != null && Autofiremaker.FIREIDS.contains(SceneObjects.getFirstAt(tile).getId())) {
                    laneData.put(tile.getY(), false);
                }
            }

            for (Position tile : tiles) {
                if (laneData.get(tile.getY()) && tile.getX() > x) {
                    x = tile.getX();
                    y = tile.getY();
                }
            }
            if (x != -1 && y != -1) {
                Log.fine("Navigating to open lane.");
                final Position pos = new Position(x, y, 0);
                Movement.walkTo(pos);
                Time.sleepUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() {
                        return Players.getLocal().isMoving();
                    }
                }, 3000);
                Time.sleepUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() {
                        return !Players.getLocal().isMoving();
                    }
                }, 3000);
                while(!Players.getLocal().getPosition().equals(pos)) {
                    Movement.setWalkFlag(pos);
                    Time.sleepUntil(new BooleanSupplier() {
                        @Override
                        public boolean getAsBoolean() {
                            return Players.getLocal().getPosition().equals(pos);
                        }
                    }, 3000);
                }
                Autofiremaker.inOpenLane = true;
            } else {
                Log.info("Waiting for an accessible lane.");
                Autofiremaker.inOpenLane = false;
            }
        }
        return Random.nextInt(400, 600);
    }

    private boolean traverseToBank() {
        return (Inventory.getCount(Autofiremaker.TINDERBOX_PREDICATE) == 0 || Inventory.getCount(Autofiremaker.logs.getLogID()) == 0) && !Autofiremaker.location.getBankArea().contains(Players.getLocal());
    }

    private boolean traverseToLane() {
        return (Autofiremaker.logs != null && Autofiremaker.location != null) && (Inventory.getCount(Autofiremaker.TINDERBOX_PREDICATE) == 1 && Inventory.getCount(Autofiremaker.logs.getLogID()) > 0) && !Autofiremaker.inOpenLane;
    }
}