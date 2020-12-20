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
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;

public class Mine extends Task {

    private static boolean isRunning = false;

    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && Autotutorialisland.location.getName().equals("Mining Area") && Autotutorialisland.location.getPrimaryArea().contains(Players.getLocal()) && !Autotutorialisland.isMiningComplete && !isRunning;
    }

    @Override
    public int execute() {
        isRunning = true;
        Npc miningInstructor = Npcs.getNearest(3311);
        while (miningInstructor == null) {
            miningInstructor = Npcs.getNearest(3311);
        }

        Log.info("Talking to Mining Instructor");
        miningInstructor.interact(ActionOpcodes.NPC_ACTION_0);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Dialog.canContinue();
            }
        }, 4000);

        while (Dialog.canContinue()) {
            Dialog.processContinue();
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Dialog.canContinue();
                }
            }, 2000);
        }

        Log.info("Mining tin");
        SceneObjects.getNearest(10080).interact("Mine");
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

        Log.info("Mining copper");
        SceneObjects.getNearest(10079).interact("Mine");
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

        Movement.walkTo(Autotutorialisland.location.getSecondaryArea().getCenter());
        Log.info("Smelting...");
        SceneObjects.getNearest(10082).interact("Use");
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

        Log.info("Talking to Mining Instructor");
        miningInstructor.interact(ActionOpcodes.NPC_ACTION_0);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Dialog.canContinue();
            }
        }, 4000);

        while (Dialog.canContinue()) {
            Dialog.processContinue();
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Dialog.canContinue();
                }
            }, 2000);
        }

        Movement.walkTo(Autotutorialisland.location.getSecondaryArea().getCenter());
        SceneObjects.getNearest(2097).interact("Smith");
        Time.sleep(3000,4000);
        Interfaces.getComponent(312, 9).interact("Smith");
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
        Log.fine("Mining/Smithing phase complete");
        Autotutorialisland.isMiningComplete = true;
        isRunning = false;
        return Random.nextInt(400, 600);
    }
}