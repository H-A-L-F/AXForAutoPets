package main;

import constants.Event;
import interfaces.EventListener;
import models.Pet;
import models.Team;

import java.util.HashMap;
import java.util.Vector;

public class EventManager {
    private HashMap<Event, HashMap<Pet, EventListener>> listeners;
    private Team team;

    public EventManager( Team team ) {
        listeners = new HashMap<>();
        this.team = team;
    }

    public void subscribe(EventListener listener, Event event, Pet pet) {
        if( listeners.containsKey( event ) ) {
            listeners.get( event ).put( pet, listener );
        } else {
            HashMap<Pet, EventListener> temp = new HashMap<>();
            temp.put( pet, listener );
            listeners.put(event, temp);
        }
    }

    public void unsubscribe(EventListener listener, Event event) {
        listeners.remove(event, listener);
    }

    public void notify(Event event, Pet pet) {
        listeners.get(event).get(pet).update(event);
    }
}
