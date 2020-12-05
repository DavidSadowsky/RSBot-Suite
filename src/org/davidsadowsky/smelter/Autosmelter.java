package org.davidsadowsky.smelter;
import org.davidsadowsky.firemaking.Autofiremaker;
import org.davidsadowsky.smelter.data.Location;
import org.davidsadowsky.smelter.data.Bar;
import org.davidsadowsky.smelter.data.Time;
import org.davidsadowsky.smelter.tasks.Banking;
import org.davidsadowsky.smelter.tasks.Smelt;
import org.davidsadowsky.smelter.tasks.Traverse;
import org.rspeer.runetek.event.listeners.ChatMessageListener;
import org.rspeer.runetek.event.listeners.SkillListener;
import org.rspeer.runetek.event.types.ChatMessageEvent;
import org.rspeer.runetek.event.types.SkillEvent;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

import java.util.EventListener;

@ScriptMeta(developer = "David Sadowsky", name = "Autosmelter", desc = "Smelter")

public class Autosmelter extends TaskScript implements ChatMessageListener {

    private static final Task[] TASKS = { new Banking(), new Traverse(), new Smelt() };

    public static boolean isSmelting = false;
    public static Time time;
    public static Location location;
    public static Bar bar;

    @Override
    public void onStart() {
        new AutosmelterGUI().setVisible(true);
        submit(TASKS);
    }

    @Override
    public void notify(ChatMessageEvent chatMessageEvent) {
        if(chatMessageEvent.getMessage().contains("Welcome")) isSmelting = false;
    }
}