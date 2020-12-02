package org.davidsadowsky.firemaking.tasks;

import org.davidsadowsky.firemaking.Autofiremaker;
import org.davidsadowsky.firemaking.data.Logs;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.time.LocalTime;
import java.util.function.BooleanSupplier;

public class Banking extends Task {

    @Override
    public boolean validate() {
        return (Autofiremaker.logs != null && Autofiremaker.location != null) && (Inventory.getCount(Autofiremaker.logs.getLogID()) == 0 || Inventory.getCount(Autofiremaker.TINDERBOX_PREDICATE) == 0) && Autofiremaker.location.getBankArea().contains(Players.getLocal());
    }

    @Override
    public int execute() {
        if (Autofiremaker.assessLogType) {
            assessLogType();
            Autofiremaker.assessLogType = false;
        }
        if (Bank.isOpen()) {
            Bank.depositAllExcept(new int[]{Autofiremaker.TINDERBOX_ID, Autofiremaker.logs.getLogID()});
            while (!Inventory.contains(Autofiremaker.TINDERBOX_PREDICATE)) {
                Log.info("Withdrawing tinderbox");
                Bank.withdraw(Autofiremaker.TINDERBOX_PREDICATE, 1);
            }
            if(!Bank.contains(Autofiremaker.logs.getLogID())) {
                Log.severe("No more logs to burn found. Exiting.");
                Game.logout();
                return -1;
            }
            Bank.withdraw(Autofiremaker.logs.getName(), 27);
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Inventory.isFull();
                }
            }, 5000);
        } else {
            Bank.open();
        }
        if (Autofiremaker.time.getSeconds() != -1 && LocalTime.now().getSecond() - Autofiremaker.time.getStartTime().getSecond() > Autofiremaker.time.getSeconds()) {
            Log.fine("Time limit of " + Autofiremaker.time.getTime() + " reached. Exiting script.");
            Game.logout();
            return -1;
        }
        return Random.nextInt(400, 600);
    }

    private void assessLogType() {
        if (Skills.getCurrentLevel(Skill.FIREMAKING) >= 30) {
            Autofiremaker.logs = Logs.WILLOW;
        } else if (Skills.getCurrentLevel(Skill.FIREMAKING) >= 15) {
            Autofiremaker.logs = Logs.OAK;
        } else {
            Autofiremaker.logs = Logs.LOGS;
        }
    }
}