package org.davidsadowsky.tutorialisland.tasks;

import org.davidsadowsky.tutorialisland.Autotutorialisland;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;

public class CookBread extends Task {

    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && Autotutorialisland.location.getName().equals("Cooking Area") && Autotutorialisland.location.getSecondaryArea().contains(Players.getLocal()) && Autotutorialisland.isCookingComplete && !Autotutorialisland.isRangeComplete;
    }

    @Override
    public int execute() {
        Npc masterChef = Npcs.getNearest(3305);
        while (masterChef == null) {
            masterChef = Npcs.getNearest(3305);
        }
        masterChef.interact(ActionOpcodes.NPC_ACTION_0);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Dialog.canContinue();
            }
        }, 10000);
        while (Dialog.canContinue()) {
            Dialog.processContinue();
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Dialog.canContinue();
                }
            }, 2500);
        }
        Item flour = Inventory.getFirst(2516);
        Item water = Inventory.getFirst(1929);
        while (flour == null || water == null) {
            flour = Inventory.getFirst(2516);
            water = Inventory.getFirst(1929);
        }
        Log.info("Making bread");
        while (!Inventory.contains(2307)) {
            flour.interact("Use");
            Time.sleep(500, 1000);
            water.interact("Use");
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Inventory.contains(2307);
                }
            }, 2500);
        }
        Log.info("Cooking bread");
        while (!Inventory.contains(2309)) {
            Inventory.getFirst(2307).interact("Use");
            Time.sleep(1000);
            SceneObjects.getNearest(9736).interact(ActionOpcodes.ITEM_ON_OBJECT);
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Players.getLocal().isAnimating();
                }
            }, 2500);
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return !Players.getLocal().isAnimating();
                }
            }, 2500);
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Inventory.contains(2309);
                }
            }, 2500);
        }
        Log.fine("Range cooking phase complete");
        Autotutorialisland.isRangeComplete = true;
        return Random.nextInt(400, 600);
    }
}