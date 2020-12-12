package org.davidsadowsky.tutorialisland.tasks;

import org.davidsadowsky.firemaking.Autofiremaker;
import org.davidsadowsky.tutorialisland.Autotutorialisland;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;

public class Woodcutting extends Task {

    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && Autotutorialisland.location.getName().equals("Fishing Area") && Autotutorialisland.location.getSecondaryArea().contains(Players.getLocal()) && Autotutorialisland.isFishingComplete && !Autotutorialisland.isWoodcuttingComplete;
    }

    @Override
    public int execute() {
        Log.info("Beginning woodcutting phase");
        Npc survivalExpert = Npcs.getNearest(8503);
        while(survivalExpert == null) {
            survivalExpert = Npcs.getNearest(8503);
        }

        Log.info("Talking to Survival Expert");
        survivalExpert.interact(ActionOpcodes.NPC_ACTION_0);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Dialog.canContinue();
            }
        }, 2000);

        while(Dialog.canContinue()) {
            Dialog.processContinue();
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Dialog.canContinue();
                }
            },2000);
        }
        SceneObject tree = SceneObjects.getNearest(9730);
        while(tree == null) {
            tree = SceneObjects.getNearest(9730);
        }
        tree.interact("Chop down");
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Players.getLocal().isAnimating();
            }
        }, 15000);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return !Players.getLocal().isAnimating();
            }
        }, 15000);
        Autotutorialisland.isWoodcuttingComplete = true;
        Log.fine("Finished woodcutting phase");
        return Random.nextInt(400,600);
    }
}