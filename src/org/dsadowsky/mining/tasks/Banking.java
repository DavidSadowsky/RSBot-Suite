package org.dsadowsky.mining.tasks;

import org.dsadowsky.mining.Autominer;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

import java.util.function.Predicate;

public class Banking extends Task {
    private static final Predicate<Item> PICKAXE_PREDICATE = item -> item.getName().contains("pickaxe");

    @Override
    public boolean validate() {
        return Inventory.isFull() && Autominer.location.getBankArea().contains(Players.getLocal());
    }

    @Override
    public int execute() {
        if(Bank.isOpen()) {
            Bank.depositAllExcept(PICKAXE_PREDICATE);
        }
        else {
            // Open bank
        }
        return Random.nextInt(400,600);
    }
}
