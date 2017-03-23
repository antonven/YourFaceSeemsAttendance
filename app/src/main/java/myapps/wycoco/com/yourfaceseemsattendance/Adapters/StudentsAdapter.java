package myapps.wycoco.com.yourfaceseemsattendance.Adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.firebase.ui.storage.images.FirebaseImageLoader;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import myapps.wycoco.com.yourfaceseemsattendance.Models.StudentModel;
import myapps.wycoco.com.yourfaceseemsattendance.R;

/**
 * Created by dell on 3/24/2017.
 */

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {

    Context mContext;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabaseReference;
    ArrayList<StorageReference> photoRef = new ArrayList<>();
    ArrayList<StudentModel> sm = new ArrayList<>();


    @Override
    public StudentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendees, parent, false);
        ViewHolder v = new ViewHolder(view);

        return v;
    }

    @Override
    public void onBindViewHolder(StudentsAdapter.ViewHolder holder, int position) {

        firebaseDatabase = firebaseDatabase.getInstance();
        mDatabaseReference = firebaseDatabase.getReference();

        Glide.with(mContext).using(new FirebaseImageLoader()).load(photoRef.get(position)).into(holder.attendeePicture);
        holder.attendeeName.setText(sm.get(position).getStudentName());
        holder.attendeeTime.setText(sm.get(position).getStudentTime());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView attendeePicture;
        TextView attendeeName, attendeeTime;

        public ViewHolder(View itemView) {
            super(itemView);

            attendeePicture = (ImageView)itemView.findViewById(R.id.attendeePicture);
            attendeeName = (TextView)itemView.findViewById(R.id.attendeeName);
            attendeeTime = (TextView)itemView.findViewById(R.id.attendeeTime);
        }
    }
}
