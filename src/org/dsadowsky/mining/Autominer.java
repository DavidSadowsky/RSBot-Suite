package org.dsadowsky.mining;
import org.dsadowsky.mining.data.Location;
import org.dsadowsky.mining.data.Rock;
import org.dsadowsky.mining.tasks.Banking;
import org.dsadowsky.mining.tasks.Drop;
import org.dsadowsky.mining.tasks.Mine;
import org.dsadowsky.mining.tasks.Traverse;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

import java.awt.*;

@ScriptMeta(developer = "David Sadowsky", name = "Autominer", desc = "Miner")

public class Autominer extends TaskScript implements RenderListener {

    private static final Task[] TASKS = { new Banking(), new Drop(), new Traverse(), new Mine() };

    public static Location location;
    public static Rock rock;

    @Override
    public void onStart() {
        submit(TASKS);
        System.out.println("Tin/Copper Powerminer Started");
    }

    @Override
    public void onStop() {

    }

    @Override
    public void notify(RenderEvent renderEvent) {
        Graphics g = renderEvent.getSource();
        g.drawString("My animation: " + Players.getLocal().getAnimation(), 30,30);
    }
}
