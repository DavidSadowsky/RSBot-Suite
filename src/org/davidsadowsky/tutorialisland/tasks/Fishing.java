package org.davidsadowsky.tutorialisland.tasks;

import org.davidsadowsky.tutorialisland.Autotutorialisland;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;

public class Fishing extends Task {

    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && Autotutorialisland.location.getName().equals("Fishing Area") && Autotutorialisland.location.getSecondaryArea().contains(Players.getLocal()) && !Autotutorialisland.isFishingComplete;
    }

    @Override
    public int execute() {
        Npc survivalExpert = Npcs.getNearest(8503);
        while (survivalExpert == null) {
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

        while (Dialog.canContinue()) {
            Dialog.processContinue();
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Dialog.canContinue();
                }
            }, 2000);
        }

        Log.info("Interacting with inventory tab");
        Interfaces.getComponent(164, 56).interact(ActionOpcodes.INTERFACE_ACTION);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Tab.INVENTORY.isOpen();
            }
        }, 10000);

        Log.info("Fishing");
        Npc fishingSpot = null;
        while (fishingSpot == null) {
            fishingSpot = Npcs.getNearest(3317);
        }
        fishingSpot.interact(ActionOpcodes.NPC_ACTION_0);
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
        Log.info("Interacting with skills tab");
        Tabs.open(Tab.SKILLS);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Tab.SKILLS.isOpen();
            }
        }, 10000);

        Log.info("Talking to survival expert");
        survivalExpert.interact(ActionOpcodes.NPC_ACTION_0);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Dialog.canContinue();
            }
        }, 2000);

        Log.fine("Fishing phase complete");
        Autotutorialisland.isFishingComplete = true;
        return Random.nextInt(400, 600);
    }
}