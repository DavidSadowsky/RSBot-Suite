package org.davidsadowsky.tutorialisland.tasks;

import org.davidsadowsky.tutorialisland.Autotutorialisland;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;

public class Cooking extends Task {

    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && Autotutorialisland.location.getName().equals("Fishing Area") && Autotutorialisland.location.getSecondaryArea().contains(Players.getLocal()) && Autotutorialisland.isFiremakingComplete && !Autotutorialisland.isCookingComplete;
    }

    @Override
    public int execute() {
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return !Players.getLocal().isAnimating() && !Players.getLocal().isMoving();
            }
        }, 10000);
        Log.info("Beginning cooking phase");
        Item shrimp = Inventory.getFirst(item -> item.getName().contains("shrimps"));
        SceneObject fire = SceneObjects.getNearest(26185);
        while(shrimp == null || fire == null) {
            shrimp = Inventory.getFirst(2514);
            fire = SceneObjects.getNearest(26185);
        }
        if(shrimp != null && fire != null && !Players.getLocal().isAnimating() && !Players.getLocal().isMoving()) {
            shrimp.interact("Use");
            Time.sleep(250,400);
            fire.interact(ActionOpcodes.ITEM_ON_OBJECT);
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
        Autotutorialisland.isCookingComplete = true;
        Log.fine("Finished cooking phase");
        return Random.nextInt(400,600);
    }
}