package org.dsadowsky.mining.tasks;

import org.dsadowsky.mining.Autominer;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class Mine extends Task {
    private static final String MINE_ACTION = "Mine";

    @Override
    public boolean validate() {
        return !Inventory.isFull() && Autominer.location.getMineArea().contains(Players.getLocal());
    }

    @Override
    public int execute() {
        final SceneObject rock = SceneObjects.getNearest(Autominer.rock.getName());
        if(rock != null) {
            rock.interact(MINE_ACTION);
        }
        return Random.nextInt(400,600);
    }
}
