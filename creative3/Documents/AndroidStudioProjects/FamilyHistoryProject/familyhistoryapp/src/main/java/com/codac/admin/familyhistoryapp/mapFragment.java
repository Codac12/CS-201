package com.codac.admin.familyhistoryapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codac.admin.familyhistoryapp.aModel.aModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import model.event;
import model.person;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 *
 * create an instance of this fragment.
 */
public class mapFragment extends Fragment implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    private GoogleMap mMap;
    TextView name;
    TextView eventText;
    ImageView icon;
    LinearLayout eventInfo;
    person prsn;
    String evntID;

    aModel m = aModel.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        name = (TextView) v.findViewById(R.id.person_name);
        eventText = (TextView) v.findViewById(R.id.event_text);
        icon = (ImageView) v.findViewById(R.id.gender_icon);
        eventInfo = (LinearLayout) v.findViewById(R.id.event_info);
        //evntID = getArguments().getString("evntID");

        super.onCreate(savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
            case R.id.settings:
                launchSettings();
                return true;
            case R.id.filter:
                launchFilter();
                return true;
            case R.id.search:
                launchSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        for(event evnt : m.getEventList())
        {
            if(m.getFilterMap().get(evnt.getEventType())) {

//                if(m.getPaternalAncestors().containsKey(evnt.getPersonID()) && !m.getFilterMap().get("Father's Side"))
//                    continue;
//                if(m.getMaternalAncestors().containsKey(evnt.getPersonID()) && !m.getFilterMap().get("Mother's Side"))
//                    continue;
                if(m.getPeople().get(evnt.getPersonID()).getGender().equals("male") && !m.getFilterMap().get("Male Events"))
                    continue;
                if(m.getPeople().get(evnt.getPersonID()).getGender().equals("female") && !m.getFilterMap().get("Female Events"))
                    continue;

                    LatLng postn = new LatLng(evnt.getLatitude(), evnt.getLongitude());
                    int typeIndex = m.getEventTypes().get(evnt.getEventType());
                    Float color = m.getColors()[typeIndex];
                    Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(postn)
                            .title(evnt.getEventType())
                            .icon(BitmapDescriptorFactory.defaultMarker(color)));
                    marker.setTag(evnt);
                }

            }

//            if(evnt.getEventType().equals("birth") && pEvnt != null) {
//
//                LatLng pMarriage = new LatLng(pEvnt.getLatitude(), pEvnt.getLongitude());
//
//                Polyline line = mMap.addPolyline(new PolylineOptions()
//                        .add(postn, pMarriage)
//                        .width(5)
//                        .color(Color.RED));
//            }


//        if(!evntID.equals(""))
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom());


        mMap.setOnMarkerClickListener(this);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        event evnt = (event) marker.getTag();
        prsn = m.getPeople().get(evnt.getPersonID());


        Drawable genderIcon;

        if(prsn.getGender().equals("male"))
        {
            genderIcon = new IconDrawable(getActivity(), Iconify.IconValue.fa_male).
                    colorRes(R.color.colorPrimary).sizeDp(40);
        }
        else {
            genderIcon = new IconDrawable(getActivity(), Iconify.IconValue.fa_female).
                    colorRes(R.color.colorAccent).sizeDp(40);
        }
        icon.setImageDrawable(genderIcon);

        name.setText(prsn.getFirstName() + " "
                + prsn.getLastName());
        eventText.setText(evnt.getEventType() + ": "
                + evnt.getCity() + ", "
                + evnt.getCountry() + " ("
                + evnt.getYear() + ")");

        eventInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              startPersonActivity();
            }
        });

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }


    private void startPersonActivity() {
        Intent intent = new Intent(getActivity(), personActivity.class);
        intent.putExtra("personID",  prsn.getPersonID());
        startActivity(intent);
    }

    private void launchSettings() {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);

        startActivity(intent);
    }

    private void launchFilter() {
        Intent intent = new Intent(getActivity(), filterActivity.class);

        startActivity(intent);
    }

    private void launchSearch() {
        Intent intent = new Intent(getActivity(), searchActivity.class);

        startActivity(intent);
    }


}

