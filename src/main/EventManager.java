package main;

import constants.Event;
import interfaces.EventListener;

import java.util.HashMap;
import java.util.Vector;

public class EventManager {
    private HashMap<Event, EventListener> listeners;
    private static EventManager instance;

    private EventManager() {
        listeners = new HashMap<>();
    }

    public static EventManager getInstance() {
        if (instance == null) instance = new EventManager();
        return instance;
    }

    public void subscribe(EventListener listener, Event event) {
        listeners.put(event, listener);
    }

    public void unsubscribe(EventListener listener, Event event) {
        listeners.remove(event, listener);
    }

    public void notify(Event event) {
        listeners.get(event).update();
    }
}
