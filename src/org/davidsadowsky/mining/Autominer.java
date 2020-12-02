package org.davidsadowsky.mining;
import org.davidsadowsky.mining.data.Location;
import org.davidsadowsky.mining.data.Rock;
import org.davidsadowsky.mining.data.Time;
import org.davidsadowsky.mining.tasks.Banking;
import org.davidsadowsky.mining.tasks.Drop;
import org.davidsadowsky.mining.tasks.Mine;
import org.davidsadowsky.mining.tasks.Traverse;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

import java.awt.*;

@ScriptMeta(developer = "David Sadowsky", name = "Autominer", desc = "Miner")

public class Autominer extends TaskScript {

    private static final Task[] TASKS = { new Banking(), new Drop(), new Traverse(), new Mine() };

    public static Time time;
    public static Location location;
    public static Rock rock;

    @Override
    public void onStart() {
        new AutominerGUI().setVisible(true);
        submit(TASKS);
    }
}