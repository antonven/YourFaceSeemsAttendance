package myapps.wycoco.com.yourfaceseemsattendance.Adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import myapps.wycoco.com.yourfaceseemsattendance.Models.MyClassModel;
import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;
import myapps.wycoco.com.yourfaceseemsattendance.R;
import myapps.wycoco.com.yourfaceseemsattendance.StudentsSide.MyClassesFragment;

/**
 * Created by dell on 3/19/2017.
 */

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder>{


    private Context mContext;
    private ArrayList<SubjectModel> subjects;
    private FirebaseDatabase fireBaseDatabase;
    private DatabaseReference dr;
    private FirebaseAuth mAuth;

    Query query;
//    private DatabaseReference reference = fireBaseDatabase.getReference().chil/zd("Class") ;

    String text;


    public SubjectsAdapter(Context mContext, ArrayList<SubjectModel> subjects) {
        this.mContext = mContext;
        this.subjects = subjects;
    }


    @Override
    public SubjectsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subjects_students, parent,false);
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
        private CardView classCard;
        public ViewHolder(final View itemView) {
            super(itemView);

            subName = (TextView) itemView.findViewById(R.id.subjectName);
            subRoom = (TextView) itemView.findViewById(R.id.subjectRoom);
            subTeacher = (TextView) itemView.findViewById(R.id.subjectTeacher);
            subStart = (TextView) itemView.findViewById(R.id.timeStart);
            subEnd = (TextView) itemView.findViewById(R.id.timeEnd);
            subDate = (TextView) itemView.findViewById(R.id.subjectDate);
            subKey = (TextView) itemView.findViewById(R.id.subjectKey);
            classCard = (CardView)itemView.findViewById(R.id.classCard);

            classCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext = itemView.getContext();
                    mAuth = FirebaseAuth.getInstance();
                    fireBaseDatabase = FirebaseDatabase.getInstance();
                    dr = fireBaseDatabase.getReference();
                    final FirebaseUser user = mAuth.getCurrentUser();


                    query = dr.child("Users").child(user.getUid()).child("Role").orderByValue();
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() == null) {

                            }
                            else {
                                if(dataSnapshot.getValue().toString().equals("Teacher")) {

                                }
                                else if (dataSnapshot.getValue().toString().equals("Student")) {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                                    builder.setTitle("Enrollment key");
                                    final EditText enrollKey = new EditText(itemView.getContext());
                                    enrollKey.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                    builder.setView(enrollKey);

                                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            text = enrollKey.getText().toString();
                                            String subjectKey = subjects.get(getAdapterPosition()).getSubjectKey();
                                            String subjectName = subjects.get(getAdapterPosition()).getSubjectName();
                                            String subjectTeacher = subjects.get(getAdapterPosition()).getSubjectTeacher();
                                            String subjectRoom = subjects.get(getAdapterPosition()).getSubjectRoom();
                                            String subujectStart = subjects.get(getAdapterPosition()).getSubjectTimeStart();
                                            String subjectEnd = subjects.get(getAdapterPosition()).getSubjectTimeEnd();
                                            String subjectDate =  subjects.get(getAdapterPosition()).getSubjectDate();
                                            String userID = user.getUid();
                                            MyClassModel mc = new MyClassModel(subjectName, subjectRoom, subjectTeacher, subujectStart, subjectEnd, subjectDate, subjectKey, userID);

                                            if(text.equals(subjects.get(getAdapterPosition()).getSubjectKey())) {
                                                dr.child("Users").child(user.getUid()).child("MyClasses").push().setValue(mc);
                                                Toast.makeText(itemView.getContext(), "Subject added to My Classes!", Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Toast.makeText(itemView.getContext(), "Enrollment key invalid!", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
                                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    builder.show();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            });
        }

    }


}
