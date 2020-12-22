package org.davidsadowsky.tutorialisland.tasks;

import org.davidsadowsky.tutorialisland.Autotutorialisland;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;

public class Combat extends Task {

    private static boolean isRunning = false;

    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && Autotutorialisland.location.getName().equals("Combat Area") && Autotutorialisland.location.getPrimaryArea().contains(Players.getLocal()) && !Autotutorialisland.isCombatComplete && !isRunning;
    }

    @Override
    public int execute() {
        isRunning = true;
        Npc combatInstructor = Npcs.getNearest(3307);
        while (combatInstructor == null) {
            combatInstructor = Npcs.getNearest(3307);
        }

        Log.info("Talking to Combat Instructor");
        combatInstructor.interact(ActionOpcodes.NPC_ACTION_0);
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

        Log.info("Opening equipment tab");
        Interfaces.getComponent(164, 57).interact(ActionOpcodes.INTERFACE_ACTION);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Tab.EQUIPMENT.isOpen();
            }
        }, 10000);

        Time.sleep(500,1000);

        Log.info("Opening worn equipment interface");
        while(Interfaces.getComponent(387, 1) == null);
        Interfaces.getComponent(387,1).interact(ActionOpcodes.INTERFACE_ACTION);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Interfaces.getComponent(387,1) == null;
            }
        }, 10000);

        Time.sleep(1000,2000);

        Log.info("Closing worn equipment interface");
        while(Interfaces.getComponent(84, 3, 11) == null);
        Interfaces.getComponent(84, 3, 11).interact(ActionOpcodes.INTERFACE_ACTION);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Interfaces.getComponent(84, 3, 11) == null;
            }
        }, 10000);

        Log.info("Equipping dagger");
        Inventory.getFirst(1205).interact("Wield");
        Time.sleep(1000,2000);

        Log.info("Talking to Combat Instructor");
        combatInstructor.interact(ActionOpcodes.NPC_ACTION_0);
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

        Time.sleep(500,1000);
        Log.info("Equipping sword and shield");
        Inventory.getFirst(1277).interact("Wield");
        Time.sleep(1000,2000);
        Inventory.getFirst(1171).interact("Wield");
        Time.sleep(1000,2000);

        Log.info("Opening combat tab");
        while(Interfaces.getComponent(164,53) == null);
        Interfaces.getComponent(164, 53).interact(ActionOpcodes.INTERFACE_ACTION);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Tab.COMBAT.isOpen();
            }
        }, 10000);

        Time.sleep(1000, 2000);

        Log.info("Fighting rat");
        Movement.walkTo(Autotutorialisland.location.getSecondaryArea().getCenter());
        while(Npcs.getNearest(3313) == null);
        Npcs.getNearest(3313).interact("Attack");
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Players.getLocal().isHealthBarVisible();
            }
        }, 30000);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return !Players.getLocal().isAnimating() && !Players.getLocal().isHealthBarVisible();
            }
        }, 30000);

        Log.info("Talking to Combat Instructor");
        Movement.walkTo(Autotutorialisland.location.getPrimaryArea().getCenter());
        combatInstructor.interact(ActionOpcodes.NPC_ACTION_0);
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

        Log.info("Equipping bow and arrows");
        Inventory.getFirst(841).interact("Wield");
        Time.sleep(1000,2000);
        Inventory.getFirst(882).interact("Wield");

        while(Npcs.getNearest(3313) == null);
        Npc rat = Npcs.getNearest(3313);
        rat.interact("Attack");
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Players.getLocal().isAnimating() && rat.isHealthBarVisible();
            }
        }, 10000);
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return !Players.getLocal().isAnimating() && !rat.isHealthBarVisible();
            }
        }, 10000);

        Log.fine("Combat phase complete");
        Autotutorialisland.isCombatComplete = true;
        isRunning = false;
        return Random.nextInt(400, 600);
    }
}