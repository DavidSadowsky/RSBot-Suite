package org.davidsadowsky.tutorialisland.tasks;

import org.davidsadowsky.tutorialisland.Autotutorialisland;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.input.Keyboard;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.GameAccount;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public class DisplayName extends Task {


    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && Autotutorialisland.location.getName().equals("Display Name") && Players.getLocal() != null && Game.isLoggedIn() && !Autotutorialisland.isCharacterDesignComplete; //&& Autotutorialisland.location.getPrimaryArea().contains(Players.getLocal());
    }

    @Override
    public int execute() {
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Interfaces.getComponent(558,0) != null;
            }
        }, 10000);
        java.util.Random rand = new java.util.Random();
        int randomNum = rand.nextInt(4);

        Log.info  ("Selecting random name");

        String alphabet = "ok";
        for(int i = 0; i < randomNum; i++) {
            Keyboard.sendKey(alphabet.charAt(rand.nextInt(alphabet.length()))); // types in gibberish
            Time.sleep(100, 250);
        }
        Keyboard.pressEnter(); // enter the gibberish
        Time.sleep(2500, 4000);
        Interfaces.getComponent(558,14).interact(ActionOpcodes.INTERFACE_ACTION); // selects 'look up name'
        Time.sleep(2500,4000);

        Log.info("Setting name");
        Interfaces.getComponent(558,18).interact(ActionOpcodes.INTERFACE_ACTION); // sets name
        Time.sleepUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return Interfaces.getComponent(679,0) != null;
            }
        }, 10000);

        Log.info("Selecting randomized character design");
        // set random character design
        for(int i = 0; i < 7; i++) {
            randomNum = rand.nextInt(5);
            for (int j = 0; j < randomNum; j++) {
                Interfaces.getComponent(679, (i * 4) + 13).interact(ActionOpcodes.INTERFACE_ACTION);
                Time.sleep(50, 100);
            }
        }

        for(int i = 0; i < 5; i++) {
            randomNum = rand.nextInt(5);
            for (int j = 0; j < randomNum; j++) {
                Interfaces.getComponent(679, (i * 4) + 44).interact(ActionOpcodes.INTERFACE_ACTION);
                Time.sleep(50, 100);
            }
        }


        Interfaces.getComponent(679,68).interact(ActionOpcodes.INTERFACE_ACTION); // submit character design
        Log.fine("Character design complete.");
        Autotutorialisland.isCharacterDesignComplete = true;
        return Random.nextInt(400,600);
    }
}