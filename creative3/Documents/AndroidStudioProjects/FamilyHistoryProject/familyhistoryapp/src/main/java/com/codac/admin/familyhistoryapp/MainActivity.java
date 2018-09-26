package com.codac.admin.familyhistoryapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.codac.admin.familyhistoryapp.aModel.aModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import model.event;
import model.person;
import model.user;
import results.eventResult;
import results.loginResult;
import results.personsResult;
import results.registerResult;

public class MainActivity extends AppCompatActivity {

    //private

    private RadioButton mMaleGender;
    private RadioButton mFemaleGender;
    private FragmentManager fm;
    private String evntID = "";
    aModel m = aModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = this.getSupportFragmentManager();

        if(m.getAuthToken() != null)
            mapFragSwtich();
        else
            loginFragSwtich();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(m.getAuthToken() != null) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.toolbar, menu);
        }
        return true;
    }

    public void loginFragSwtich(){
        loginFragment loginFrag = new loginFragment();
        fm.beginTransaction()
                .replace(R.id.container, loginFrag)
                .commit();
    }

    public void mapFragSwtich()
    {
        mapFragment mapFrag = new mapFragment();
        fm.beginTransaction()
                .replace(R.id.container, mapFrag)
                .commit();
    }

    public String getEvntID() {
        return evntID;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.male_gender_input:
                if (checked)
                    Toast.makeText(this, "male", Toast.LENGTH_SHORT).show();
                break;
            case R.id.female_gender_input:
                if (checked)
                    Toast.makeText(this, "female", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public void setModelData(personsResult prsnResult, personsResult prsnsResult, eventResult evntResult, loginResult logResult, registerResult regResult)
    {
        aModel m = aModel.getInstance();

        HashMap<String, event> events = new HashMap<String, event>();
        HashMap<String, Integer> eventType = new HashMap<String, Integer>();
        ArrayList<event> eventsList = new ArrayList<event>();
        HashMap<String, person> persons = new HashMap<String, person>();
        ArrayList<person>  people = new ArrayList<>();
        HashMap<String, ArrayList<person>> personChildren = new HashMap<String, ArrayList<person>>();
        HashMap <String, ArrayList<event>> personEvents = new HashMap<String, ArrayList<event>>();
        ArrayList<String> filters = new ArrayList<>();
        HashMap<String, ArrayList<event>> eventFilters = new HashMap<>();
        ArrayList<event> maleEvents = new ArrayList<>();
        ArrayList<event> femaleEvents = new ArrayList<>();
        ArrayList<event> fatherEvents = new ArrayList<>();
        ArrayList<event> motherEvents = new ArrayList<>();
        HashMap<String, Boolean> filterMap = new HashMap<>();


        person usr = prsnResult.getPersons().get(0);
        m.setUser(usr);

        String fatherID = usr.getFather();
        String motherID = usr.getMother();

        for(person prsn : prsnsResult.getPersons())
        {
            persons.put(prsn.getPersonID(), prsn);
        }

        if(logResult != null)
            m.setAuthToken(logResult.getAuthToken());
        else if(regResult != null)
            m.setAuthToken(regResult.getAuthToken());

        int eventTypeNumber = 0;
        for(event evnt : evntResult.getEvents())
        {
            if(!eventType.containsKey(evnt.getEventType())) {
                eventType.put(evnt.getEventType(), eventTypeNumber);
                eventTypeNumber++;
            }

            events.put(evnt.getEventID(), evnt);
            eventsList.add(evnt);

            //Split events by type for mapping purposes
            ArrayList<event> eFilters = eventFilters.get(evnt.getEventType());
            if(eFilters == null) {
                eFilters = new ArrayList<event>();
                eFilters.add(evnt);
                personEvents.put(evnt.getEventType(), eFilters);
            } else {
                // add if item is not already in list
                if(!eFilters.contains(evnt))
                    eFilters.add(evnt);
            }
            // Puts types into list
            if(!filters.contains(evnt.getEventType())) {
                filters.add(evnt.getEventType());
            }

            if(!filterMap.containsKey(evnt.getEventType()))
            {
                filterMap.put(evnt.getEventType(), true);
            }

            //Forms Arrays of events based on gender
            if(persons.get(evnt.getPersonID()).getGender().equals("male"))
                maleEvents.add(evnt);
            else
                femaleEvents.add(evnt);

            ArrayList<event> eventList = personEvents.get(evnt.getPersonID());

            // if list does not exist create it
            if(eventList == null) {
                eventList = new ArrayList<event>();
                eventList.add(evnt);
                personEvents.put(evnt.getPersonID(), eventList);
            } else {
                // add if item is not already in list
                if(!eventList.contains(evnt)) eventList.add(evnt);
            }
        }

        eventFilters.put("Father's Side", fatherEvents);
        eventFilters.put("Mother's Side", motherEvents);
        eventFilters.put("Male Events", maleEvents);
        eventFilters.put("Female Events", femaleEvents);


        filters.add("Father's Side");
        filters.add("Mother's Side");
        filters.add("Male Events");
        filters.add("Female Events");

        filterMap.put("Father's Side", true);
        filterMap.put("Mother's Side", true);
        filterMap.put("Male Events", true);
        filterMap.put("Female Events", true);



        m.setMaternalAncestors(calculateMothersSide(usr.getMother()));
        m.setMaternalAncestors(calculateFathersSide(usr.getFather()));

        m.setFilterMap(filterMap);
        m.setFilters(filters);
        m.setEventFilters(eventFilters);
        m.setEvents(events);
        m.setEventList(eventsList);
        m.setEventTypes(eventType);

        m.setPersonEvents(personEvents);
        m.setPersonChildren(personChildren);
        m.setPeople(persons);
    }

    public HashMap<String, person> calculateFathersSide(String fatherID)
    {

        return null;
    }

    public HashMap<String, person> calculateMothersSide(String motherID)
    {
        return null;
    }
}
