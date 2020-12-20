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

public class QuestGuide extends Task {

    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && Autotutorialisland.location.getName().equals("Quest Guide Area") && Autotutorialisland.location.getSecondaryArea().contains(Players.getLocal()) && !Autotutorialisland.isQuestGuideComplete;
    }

    @Override
    public int execute() {
        Npc questGuide = Npcs.getNearest(3312);
        while (questGuide == null) {
            questGuide = Npcs.getNearest(3312);
        }

        Log.info("Talking to Quest Guide");
        questGuide.interact(ActionOpcodes.NPC_ACTION_0);
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

        Log.info("Interacting with quest tab");
        Interfaces.getComponent(164, 55).interact(ActionOpcodes.INTERFACE_ACTION);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Tab.QUEST_LIST.isOpen();
            }
        }, 10000);

        Log.info("Talking to Quest Guide");
        questGuide.interact(ActionOpcodes.NPC_ACTION_0);
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
        Log.fine("Quest guide phase complete");
        Autotutorialisland.isQuestGuideComplete = true;
        return Random.nextInt(400, 600);
    }
}