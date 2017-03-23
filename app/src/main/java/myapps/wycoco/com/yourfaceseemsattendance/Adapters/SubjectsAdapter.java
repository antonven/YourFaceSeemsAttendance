package myapps.wycoco.com.yourfaceseemsattendance.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

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
        holder.subName.setText(subjects.get(position).getSubjectName());
        holder.subRoom.setText(subjects.get(position).getSubjectRoom());
        holder.subTeacher.setText(subjects.get(position).getSubjectTeacher());
        holder.subStart.setText(subjects.get(position).getSubjectTimeStart());
        holder.subEnd.setText(subjects.get(position).getSubjectTimeEnd());
        holder.subDate.setText(subjects.get(position).getSubjectDate());
        holder.subKey.setText(subjects.get(position).getSubjectKey());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView subName, subRoom, subTeacher, subStart, subEnd, subDate, subKey;

        public ViewHolder(final View itemView) {
            super(itemView);

            subName = (TextView) itemView.findViewById(R.id.subjectName);
            subRoom = (TextView) itemView.findViewById(R.id.subjectRoom);
            subTeacher = (TextView) itemView.findViewById(R.id.subjectTeacher);
            subStart = (TextView) itemView.findViewById(R.id.timeStart);
            subEnd = (TextView) itemView.findViewById(R.id.timeEnd);
            subDate = (TextView) itemView.findViewById(R.id.subjectDate);
            subKey = (TextView) itemView.findViewById(R.id.subjectKey);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    
                }
            });



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
