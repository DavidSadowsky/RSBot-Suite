package org.davidsadowsky.tutorialisland;

import org.davidsadowsky.tutorialisland.data.Location;
import org.davidsadowsky.tutorialisland.tasks.*;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(developer = "David Sadowsky", name = "DAS Tutorial Island", desc = "Does Tutorial Island")

public class Autotutorialisland extends TaskScript {

    private static final Task[] TASKS = {new DisplayName(), new Settings(), new Fishing(), new Woodcutting(), new Firemaking(), new Cooking(), new Traverse(), new CookBread(), new QuestGuide(), new Mine(), new Combat()};

    public static boolean isCharacterDesignComplete = false; // TODO: debug, change this to false
    public static boolean isSettingsComplete = false; // TODO: debug, change this to false
    public static boolean isFishingComplete = false; // TODO: debug, change this to false
    public static boolean isWoodcuttingComplete = false; // TODO: debug, change this to false
    public static boolean isFiremakingComplete = false; // TODO: debug, change this to false
    public static boolean isCookingComplete = false; // TODO: debug, change this to false
    public static boolean isRangeComplete = false; // TODO: debug, change this to false
    public static boolean isQuestGuideComplete = false; // TODO: debug, change this to false
    public static boolean isMiningComplete = false; // TODO: debug, change this to false
    public static boolean isCombatComplete = false;
    public static Location location = Location.START; // TODO: debug, change this to START

    @Override
    public void onStart() {
        submit(TASKS);
    }
}