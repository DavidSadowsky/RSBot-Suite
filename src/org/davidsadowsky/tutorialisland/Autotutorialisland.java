package org.davidsadowsky.tutorialisland;
import org.davidsadowsky.tutorialisland.data.Location;
import org.davidsadowsky.tutorialisland.tasks.*;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

import java.util.ArrayList;

@ScriptMeta(developer = "David Sadowsky", name = "DAS Tutorial Island", desc = "Does Tutorial Island")

public class Autotutorialisland extends TaskScript {

    private static final Task[] TASKS = { new DisplayName(), new Settings(), new Fishing(), new Woodcutting(), new Firemaking(), new Cooking(), new Traverse()};

    public static boolean isCharacterDesignComplete = true; // TODO: debug, change this to false
    public static boolean isSettingsComplete = true; // TODO: debug, change this to false
    public static boolean isFishingComplete = false;
    public static boolean isWoodcuttingComplete = false;
    public static boolean isFiremakingComplete = false;
    public static boolean isCookingComplete = false;
    public static Location location = Location.START;

    @Override
    public void onStart() {
        submit(TASKS);
    }
}