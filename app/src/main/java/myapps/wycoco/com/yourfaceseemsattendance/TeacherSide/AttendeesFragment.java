package myapps.wycoco.com.yourfaceseemsattendance.TeacherSide;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import myapps.wycoco.com.yourfaceseemsattendance.Adapters.AttendeesAdapter;
import myapps.wycoco.com.yourfaceseemsattendance.Models.StudentModel;
import myapps.wycoco.com.yourfaceseemsattendance.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttendeesFragment extends DialogFragment {

    RecyclerView recView;
    ArrayList<StudentModel> sm = new ArrayList<>();
    AttendeesAdapter mAdapter;
    public AttendeesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendees, container, false);



        recView = (RecyclerView)view.findViewById(R.id.recyclerViewAttendee);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recView.setLayoutManager(layoutManager);
        recView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new AttendeesAdapter(getActivity(), sm);
        recView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();







        return view;
    }

}
