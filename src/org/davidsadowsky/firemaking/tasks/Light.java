package org.davidsadowsky.firemaking.tasks;

import org.davidsadowsky.firemaking.Autofiremaker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;

public class Light extends Task {
    @Override
    public boolean validate() {
        return Autofiremaker.inOpenLane && Inventory.getCount() > 1;
    }

    @Override
    public int execute() {
        Item logs = Inventory.getFirst(Autofiremaker.logs.getLogID());
        Item tinderbox = Inventory.getFirst(Autofiremaker.TINDERBOX_PREDICATE);
        if(logs != null && tinderbox != null && !Players.getLocal().isAnimating() && !Players.getLocal().isMoving()) {
            logs.interact("Use");
            Time.sleep(250,400);
            tinderbox.interact("Use");
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Players.getLocal().isAnimating() || Players.getLocal().isMoving();
                }
            }, 1000);
        }
        return Random.nextInt(400,600);
    }
}
