package com.codac.admin.familyhistoryapp.aModel;

import android.media.audiofx.BassBoost;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Filter;
import model.person;
import model.event;
import results.personsResult;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.*;

/**
 * Created by Admin on 4/3/17.
 */

public class aModel {

    private String authToken;
    private HashMap<String, person> people;
    private HashMap <String, event> events;
    private HashMap <String, ArrayList<event>> personEvents;
    private BassBoost.Settings settings;
    private Filter filter;
    private ArrayList<event> eventList;
    private HashMap<String, Integer> eventTypes;
    private HashMap<String, Boolean> filterMap;
    private person user;
    private HashMap<String, person> paternalAncestors;
    private HashMap<String, person> maternalAncestors;
    private HashMap <String, ArrayList<person>> personChildren;
    private Float[] colors = {HUE_AZURE, HUE_BLUE, HUE_CYAN, HUE_GREEN, HUE_MAGENTA, HUE_ORANGE, HUE_RED, HUE_ROSE, HUE_VIOLET, HUE_YELLOW};
    private ArrayList<String> filters;

    private static aModel mInstance = null;
    private HashMap<String, ArrayList<event>> eventFilters;


    public static aModel getInstance(){
        if(mInstance == null)
        {
            mInstance = new aModel();
        }
        return mInstance;
    }

    public HashMap<String, Boolean> getFilterMap() {
        return filterMap;
    }

    public void setFilterMap(HashMap<String, Boolean> filterMap) {
        this.filterMap = filterMap;
    }

    public ArrayList<String> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<String> filters) {
        this.filters = filters;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Float[] getColors() {
        return colors;
    }

    public void setColors(Float[] colors) {
        this.colors = colors;
    }

    public void setEventList(ArrayList<event> eventList) {
        this.eventList = eventList;
    }

    public ArrayList<event> getEventList() {
        return eventList;
    }

    public void setPeople(HashMap<String, person> people) {
        this.people = people;
    }

    public void setEvents(HashMap<String, event> events) {
        this.events = events;
    }

    public void setPersonEvents(HashMap<String, ArrayList<event>> personEvents) {
        this.personEvents = personEvents;
    }

    public void setSettings(BassBoost.Settings settings) {
        this.settings = settings;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void setEventTypes(HashMap<String, Integer> eventTypes) {
        this.eventTypes = eventTypes;
    }

    public void setUser(person user) {
        this.user = user;
    }

    public void setPaternalAncestors(HashMap<String, person> paternalAncestors) {
        this.paternalAncestors = paternalAncestors;
    }

    public void setMaternalAncestors(HashMap<String, person> maternalAncestors) {
        this.maternalAncestors = maternalAncestors;
    }

    public void setPersonChildren(HashMap<String, ArrayList<person>> personChildren) {
        this.personChildren = personChildren;
    }

    public HashMap<String, person> getPeople() {
        return people;
    }

    public HashMap<String, event> getEvents() {
        return events;
    }

    public HashMap<String, ArrayList<event>> getPersonEvents() {
        return personEvents;
    }

    public BassBoost.Settings getSettings() {
        return settings;
    }

    public Filter getFilter() {
        return filter;
    }

    public HashMap<String, Integer> getEventTypes() {
        return eventTypes;
    }

    public person getUser() {
        return user;
    }

    public HashMap<String, person> getPaternalAncestors() {
        return paternalAncestors;
    }

    public HashMap<String, person> getMaternalAncestors() {
        return maternalAncestors;
    }

    public HashMap<String, ArrayList<person>> getPersonChildren() {
        return personChildren;
    }


    public void setEventFilters(HashMap<String,ArrayList<event>> eventFilters) {
        this.eventFilters = eventFilters;
    }

    public HashMap<String, ArrayList<event>> getEventFilters() {
        return eventFilters;
    }
}

