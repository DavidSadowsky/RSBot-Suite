package org.davidsadowsky.smelter.tasks;

import org.davidsadowsky.smelter.Autosmelter;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;

public class Smelt extends Task {

    @Override
    public boolean validate() {
        return (Autosmelter.bar != null && Autosmelter.location != null) && Autosmelter.bar.isAccessible() && Inventory.contains(Autosmelter.bar.getOreNames()) && Autosmelter.location.getFurnaceArea().contains(Players.getLocal());
    }

    @Override
    public int execute() {
        if(Dialog.isOpen()) Autosmelter.isSmelting = false;
        SceneObject furnace = SceneObjects.getNearest("Furnace");
        if(furnace != null && !Autosmelter.isSmelting) {
            furnace.interact("Smelt");
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Interfaces.getComponent(Autosmelter.bar.getComponentGroup(), Autosmelter.bar.getComponentNum()) != null;
                }
            }, 5000);
            InterfaceComponent barSelector = Interfaces.getComponent(Autosmelter.bar.getComponentGroup(), Autosmelter.bar.getComponentNum());
            barSelector.interact(ActionOpcodes.INTERFACE_ACTION);
            Time.sleepUntil(new BooleanSupplier() {
                @Override
                public boolean getAsBoolean() {
                    return Players.getLocal().isAnimating();
                }
            }, 5000);
            Autosmelter.isSmelting = true;
        }
        return Random.nextInt(400,600);
    }
}