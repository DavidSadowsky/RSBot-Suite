package org.davidsadowsky.firemaking;

import org.davidsadowsky.firemaking.data.Location;
import org.davidsadowsky.firemaking.data.Logs;
import org.davidsadowsky.firemaking.data.Time;
import org.davidsadowsky.firemaking.tasks.Banking;
import org.davidsadowsky.firemaking.tasks.Light;
import org.davidsadowsky.firemaking.tasks.Traverse;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.event.listeners.ChatMessageListener;
import org.rspeer.runetek.event.types.ChatMessageEvent;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@ScriptMeta(developer = "David Sadowsky", name = "Autofiremaker", desc = "Firemaker")

public class Autofiremaker extends TaskScript implements ChatMessageListener {

    private static final Task[] TASKS = { new Banking(), new Traverse(), new Light() };

    public static final List<Integer> FIREIDS = new ArrayList<Integer>(){{ add(26185); }};
    public static final Predicate<Item> TINDERBOX_PREDICATE = item -> item.getName().contains("Tinder");
    public static final int TINDERBOX_ID = 590;
    public static boolean inOpenLane;
    public static boolean assessLogType;

    public static Time time;
    public static Location location;
    public static Logs logs;

    @Override
    public void onStart() {
        new AutofiremakerGUI().setVisible(true);
        submit(TASKS);
    }

    @Override
    public void notify(ChatMessageEvent chatMessageEvent) {
        if(chatMessageEvent.getMessage().contains("You can't")) inOpenLane = false;
        else if(chatMessageEvent.getMessage().contains("Congratulations") && Autofiremaker.logs.toString().equals("BESTAVAILABLE")) assessLogType = true;
    }
}