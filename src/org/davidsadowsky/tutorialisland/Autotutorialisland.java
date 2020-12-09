package org.davidsadowsky.tutorialisland;
import org.davidsadowsky.tutorialisland.data.Location;
import org.davidsadowsky.tutorialisland.tasks.DisplayName;
import org.davidsadowsky.tutorialisland.tasks.Settings;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(developer = "David Sadowsky", name = "DAS Tutorial Island", desc = "Does Tutorial Island")

public class Autotutorialisland extends TaskScript {

    private static final Task[] TASKS = { new DisplayName(), new Settings() };


    public static boolean isCharacterDesignComplete = false;
    public static boolean isSettingsComplete = false;
    public static Location location = Location.START;

    @Override
    public void onStart() {
        submit(TASKS);
    }
}