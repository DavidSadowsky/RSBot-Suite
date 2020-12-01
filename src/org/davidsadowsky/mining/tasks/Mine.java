package org.davidsadowsky.mining.tasks;

import org.davidsadowsky.mining.Autominer;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

import java.util.function.BooleanSupplier;

public class Mine extends Task {
    private static final String MINE_ACTION = "Mine";

    @Override
    public boolean validate() {
        return (Autominer.rock != null && Autominer.location != null) && Autominer.rock.isAccessible() && !Inventory.isFull() && Autominer.location.getMineArea().contains(Players.getLocal());
    }

    @Override
    public int execute() {
        SceneObject rock;
        if(Autominer.rock.getName().equals("Copper/Tin ore")) {
            if(Inventory.getCount("Copper ore") < 14) rock = SceneObjects.getNearest(Autominer.rock.getRockIds()[0], Autominer.rock.getRockIds()[1]);
            else rock = SceneObjects.getNearest(Autominer.rock.getRockIds()[2], Autominer.rock.getRockIds()[3]);
        }
        else {
            rock = SceneObjects.getNearest(Autominer.rock.getRockIds());
        }
        if(rock != null && !Players.getLocal().isAnimating()) {
            rock.interact(MINE_ACTION);
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Players.getLocal().isAnimating();
                }
            }, 5000);
        }

        return Random.nextInt(400,600);
    }
}