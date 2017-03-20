package myapps.wycoco.com.yourfaceseemsattendance.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;
import myapps.wycoco.com.yourfaceseemsattendance.R;

/**
 * Created by dell on 3/19/2017.
 */

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder>{


    private Context mContext;
    private ArrayList<SubjectModel> subjects;
    private DatabaseReference reference;

    public SubjectsAdapter(Context mContext, ArrayList<SubjectModel> subjects) {
        this.mContext = mContext;
        this.subjects = subjects;
    }

    @Override
    public SubjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent,false);
        ViewHolder view = new ViewHolder(v);
        return view;
    }

    @Override
    public void onBindViewHolder(SubjectsAdapter.ViewHolder holder, int position) {
        holder.subjectName.setText(subjects.get(position).getSubjectName());
        holder.teacherName.setText(subjects.get(position).getSubjectId());
        holder.teacherName.setText(subjects.get(position).getSubjectSchedule());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView subjectName, teacherName, day, time;

        public ViewHolder(View itemView) {
            super(itemView);

            subjectName = (TextView) itemView.findViewById(R.id.subjectName1);
            teacherName = (TextView) itemView.findViewById(R.id.teacherName);
            day = (TextView) itemView.findViewById(R.id.day);
            time = (TextView) itemView.findViewById(R.id.timeSchedule);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });



        }
        public void setName(String name){
            subjectName = (TextView) itemView.findViewById(R.id.subjectName1);
            subjectName.setText(name);
        }
    }

//    public SubjectsAdapter(DatabaseReference reference){
//
//        subjects = new ArrayList<>();
//
////        reference.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                subjects.clear();
////                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
////                    SubjectModel sm = postSnapshot.getValue(SubjectModel.class);
////                    subjects.add(sm);
////                }
////                notifyDataSetChanged();
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////                Toast.makeText(mContext, "Data retrieval failed!", Toast.LENGTH_SHORT).show();
////            }
////        });
//        reference.addChildEventListener(new Onchild)
//    }
}
