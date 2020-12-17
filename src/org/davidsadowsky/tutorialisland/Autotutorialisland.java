package org.davidsadowsky.tutorialisland;

import org.davidsadowsky.tutorialisland.data.Location;
import org.davidsadowsky.tutorialisland.tasks.*;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(developer = "David Sadowsky", name = "DAS Tutorial Island", desc = "Does Tutorial Island")

public class Autotutorialisland extends TaskScript {

    private static final Task[] TASKS = {new DisplayName(), new Settings(), new Fishing(), new Woodcutting(), new Firemaking(), new Cooking(), new Traverse(), new CookBread()};

    public static boolean isCharacterDesignComplete = true; // TODO: debug, change this to false
    public static boolean isSettingsComplete = true; // TODO: debug, change this to false
    public static boolean isFishingComplete = true; // TODO: debug, change this to false
    public static boolean isWoodcuttingComplete = true; // TODO: debug, change this to false
    public static boolean isFiremakingComplete = true; // TODO: debug, change this to false
    public static boolean isCookingComplete = true; // TODO: debug, change this to false
    public static boolean isRangeComplete = false;
    public static Location location = Location.FISHING; // TODO: debug, change this to START

    @Override
    public void onStart() {
        submit(TASKS);
    }
}