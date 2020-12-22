package org.davidsadowsky.tutorialisland.tasks;

import org.davidsadowsky.tutorialisland.Autotutorialisland;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;

public class Firemaking extends Task {

    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && Autotutorialisland.location.getName().equals("Fishing Area") && Autotutorialisland.location.getPrimaryArea().contains(Players.getLocal()) && Autotutorialisland.isWoodcuttingComplete && !Autotutorialisland.isFiremakingComplete;
    }

    @Override
    public int execute() {
        Log.info("Beginning firemaking phase");
        Item logs = Inventory.getFirst(item -> item.getName().contains("Logs"));
        Item tinderbox = Inventory.getFirst(item -> item.getName().contains("Tinder"));
        while (logs == null || tinderbox == null) {
            logs = Inventory.getFirst(item -> item.getName().contains("Logs"));
            tinderbox = Inventory.getFirst(item -> item.getName().contains("Tinder"));
        }
        if (logs != null && tinderbox != null && !Players.getLocal().isAnimating() && !Players.getLocal().isMoving()) {
            logs.interact("Use");
            Time.sleep(250, 400);
            tinderbox.interact("Use");
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Players.getLocal().isAnimating();
                }
            }, 10000);
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return !Players.getLocal().isAnimating();
                }
            }, 10000);
        }
        Autotutorialisland.isFiremakingComplete = true;
        Log.fine("Finished firemaking phase");
        return Random.nextInt(400, 600);
    }
}