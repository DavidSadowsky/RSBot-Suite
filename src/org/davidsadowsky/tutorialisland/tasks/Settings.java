package org.davidsadowsky.tutorialisland.tasks;

import org.davidsadowsky.tutorialisland.Autotutorialisland;
import org.davidsadowsky.tutorialisland.data.Location;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;

public class Settings extends Task {

    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && Autotutorialisland.location.getName().equals("Display Name") && Players.getLocal() != null && Autotutorialisland.isCharacterDesignComplete && !Autotutorialisland.isSettingsComplete; //&& Autotutorialisland.location.getPrimaryArea().contains(Players.getLocal());
    }

    @Override
    public int execute() {
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Interfaces.getComponent(679,68) == null;
            }
        }, 10000);

        Npc gielinorGuide = Npcs.getNearest(3308);
        while (gielinorGuide == null) {
            gielinorGuide = Npcs.getNearest(3308);
        }

        Log.info("Talking to Gielinor Guide");
        gielinorGuide.interact(ActionOpcodes.NPC_ACTION_0);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Dialog.canContinue();
            }
        }, 10000);

        while(Dialog.canContinue() || Dialog.isViewingChatOptions()) {
            if(Dialog.isViewingChatOptions()) {
                Dialog.process(0);
            }
            else Dialog.processContinue();
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Dialog.canContinue() || Dialog.isViewingChatOptions();
                }
            },2500);
        }

        Log.info("Interacting with settings tab");
        Interfaces.getComponent(164,41).interact(ActionOpcodes.INTERFACE_ACTION);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Tab.OPTIONS.isOpen();
            }
        }, 10000);

        Log.info("Talking to Gielinor Guide");
        gielinorGuide.interact(ActionOpcodes.NPC_ACTION_0);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Dialog.canContinue();
            }
        },10000);

        while(Dialog.canContinue()) {
            Dialog.processContinue();
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Dialog.canContinue();
                }
            }, 5000);
        }

        Log.fine("Room 1 complete");
        Autotutorialisland.isSettingsComplete = true;
        return Random.nextInt(400,600);
    }
}