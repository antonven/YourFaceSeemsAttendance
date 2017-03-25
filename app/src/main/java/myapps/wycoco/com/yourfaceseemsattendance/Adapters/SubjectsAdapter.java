package myapps.wycoco.com.yourfaceseemsattendance.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import myapps.wycoco.com.yourfaceseemsattendance.LoginFragment;
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
//    private DatabaseReference reference = fireBaseDatabase.getReference().chil/zd("Class") ;

    String text;


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

                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setTitle("Enrollment key");
                    final EditText enrollKey = new EditText(itemView.getContext());
                    enrollKey.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    builder.setView(enrollKey);

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Bundle args = new Bundle();
                            text = enrollKey.getText().toString();
                            args.putString("enrollKey", text);
                            MyClassesFragment fs = new MyClassesFragment();
                            fs.setArguments(args);

                            Toast.makeText(itemView.getContext(), "Subject added to My Classes!", Toast.LENGTH_SHORT).show();

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
            });



        }
    }

//    public void showDialog(){
//        DialogFragment df = new AttendeesFragment().insta
//    }

}
