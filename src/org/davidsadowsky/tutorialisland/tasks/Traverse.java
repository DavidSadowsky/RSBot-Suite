package org.davidsadowsky.tutorialisland.tasks;

import org.davidsadowsky.tutorialisland.Autotutorialisland;
import org.davidsadowsky.tutorialisland.data.Location;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Traverse extends Task {
    @Override
    public boolean validate() {
        return (Autotutorialisland.location != null) && (traverseToFishingArea() || traverseToCookingArea() || traverseToQuestGuide() || traverseToMine() || traverseToCombat());
    }

    @Override
    public int execute() {
        if (traverseToFishingArea()) {
            Autotutorialisland.location = Location.FISHING;
            Movement.walkTo(Autotutorialisland.location.getSecondaryArea().getCenter());
        }
        else if (traverseToCookingArea()) {
            Autotutorialisland.location = Location.COOKING;
            Movement.walkTo(Autotutorialisland.location.getSecondaryArea().getCenter());
        }
        else if (traverseToQuestGuide()) {
            Autotutorialisland.location = Location.QUEST;
            Movement.walkTo(Autotutorialisland.location.getSecondaryArea().getCenter());
        }
        else if(traverseToMine()) {
            Autotutorialisland.location = Location.MINING;
            Movement.walkTo(Autotutorialisland.location.getSecondaryArea().getCenter());
        }
        else if(traverseToCombat()) {
            Autotutorialisland.location = Location.COMBAT;
            Movement.walkTo(Autotutorialisland.location.getPrimaryArea().getCenter());
        }
        return Random.nextInt(400, 600);
    }

    private boolean traverseToFishingArea() {
        return (Autotutorialisland.location.getName().equals("Display Name")) && Autotutorialisland.location.getPrimaryArea().contains(Players.getLocal()) && Autotutorialisland.isSettingsComplete;
    }

    private boolean traverseToCookingArea() {
        return (Autotutorialisland.location.getName().equals("Fishing Area")) && Autotutorialisland.location.getPrimaryArea().contains(Players.getLocal()) && Autotutorialisland.isCookingComplete;
    }

    private boolean traverseToQuestGuide() {
        return (Autotutorialisland.location.getName().equals("Cooking Area")) && Autotutorialisland.location.getSecondaryArea().contains(Players.getLocal()) && Autotutorialisland.isRangeComplete;
    }

    private boolean traverseToMine() {
        return (Autotutorialisland.location.getName().equals("Quest Guide Area")) && Autotutorialisland.location.getSecondaryArea().contains(Players.getLocal()) && Autotutorialisland.isQuestGuideComplete;
    }

    private boolean traverseToCombat() {
        return (Autotutorialisland.location.getName().equals("Mining Area")) && Autotutorialisland.location.getPrimaryArea().contains(Players.getLocal()) && Autotutorialisland.isMiningComplete;
    }
}