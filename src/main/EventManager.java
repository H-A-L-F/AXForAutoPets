package main;

import constants.Event;
import interfaces.EventListener;
import models.Team;

import java.util.HashMap;
import java.util.Vector;

public class EventManager {
    private HashMap<Event, EventListener> listeners;
    private Team team;

    public EventManager( Team team ) {
        listeners = new HashMap<>();
        this.team = team;
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
