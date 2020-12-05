package org.davidsadowsky.smelter.tasks;

import org.davidsadowsky.smelter.Autosmelter;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.time.LocalTime;
import java.util.function.BooleanSupplier;

public class Banking extends Task {

    @Override
    public boolean validate() {
        return (Autosmelter.bar != null && Autosmelter.location != null) && !Inventory.contains(Autosmelter.bar.getOreNames()) && Autosmelter.location.getBankArea().contains(Players.getLocal());
    }

    @Override
    public int execute() {
            if (Bank.isOpen()) {
                Bank.depositInventory();
                Time.sleepUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() {
                        return Inventory.isEmpty();
                    }
                }, 5000);
                Time.sleep(200, 300);
                if(Autosmelter.bar.getName().equals("Bronze")) {
                    Bank.withdraw(Autosmelter.bar.getOreIds()[0], 14);
                    Time.sleepUntil(new BooleanSupplier() {
                        @Override
                        public boolean getAsBoolean() {
                            return Inventory.contains(Autosmelter.bar.getOreIds()[0]);
                        }
                    }, 2500);
                    Time.sleep(200, 300);
                    Bank.withdraw(Autosmelter.bar.getOreIds()[1], 14);
                    Time.sleepUntil(new BooleanSupplier() {
                        @Override
                        public boolean getAsBoolean() {
                            return Inventory.contains(Autosmelter.bar.getOreIds()[1]);
                        }
                    }, 2500);
                    Time.sleep(200, 300);

                }
                else {
                    Bank.withdraw(Autosmelter.bar.getOreIds()[0], 28);
                    Time.sleepUntil(new BooleanSupplier() {
                        @Override
                        public boolean getAsBoolean() {
                            return Inventory.contains(Autosmelter.bar.getOreIds()[0]);
                        }
                    }, 2500);
                    Time.sleep(200, 300);
                }
                if(!Inventory.isFull()) {
                    Log.fine("Nothing left to smelt. Logging out.");
                    Game.logout();
                    return -1;
                }
            } else {
                Bank.open();
            }

        if(Autosmelter.time.getSeconds() != -1 && LocalTime.now().getSecond() - Autosmelter.time.getStartTime().getSecond() > Autosmelter.time.getSeconds()) {
            Log.info("Time limit of " + Autosmelter.time.getTime() + " reached. Exiting script.");
            Game.logout();
            return -1;
        }
        return Random.nextInt(400,600);
    }
}