package org.davidsadowsky.mining.tasks;

import org.davidsadowsky.mining.Autominer;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.DepositBox;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.time.LocalTime;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public class Banking extends Task {
    private static final Predicate<Item> PICKAXE_PREDICATE = item -> item.getName().contains("pickaxe");

    @Override
    public boolean validate() {
        return (Autominer.rock != null && Autominer.location != null) && Inventory.isFull() && Autominer.location.getBankArea().contains(Players.getLocal());
    }

    @Override
    public int execute() {
        if(Autominer.location.isDepositBox()) {
            if(DepositBox.isOpen()) {
                DepositBox.depositAllExcept(PICKAXE_PREDICATE);
                Time.sleepUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() {
                        return Inventory.isEmpty();
                    }
                }, 5000);
            }
            else {
                DepositBox.open();
            }
        }
        else {
            if (Bank.isOpen()) {
                Bank.depositAllExcept(PICKAXE_PREDICATE);
                Time.sleepUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() {
                        return Inventory.isEmpty();
                    }
                }, 5000);
            } else {
                Bank.open();
            }
        }
        if(LocalTime.now().getSecond() - Autominer.time.getStartTime().getSecond() > Autominer.time.getSeconds()) {
            Log.info("Time limit of " + Autominer.time.getTime() + " reached. Exiting script.");
            Game.logout();
            return -1;
        }
        return Random.nextInt(400,600);
    }
}