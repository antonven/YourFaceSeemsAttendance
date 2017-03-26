package myapps.wycoco.com.yourfaceseemsattendance.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import myapps.wycoco.com.yourfaceseemsattendance.Models.MyClassModel;
import myapps.wycoco.com.yourfaceseemsattendance.R;

/**
 * Created by dell on 3/26/2017.
 */

public class MyClassesAdapter extends RecyclerView.Adapter<MyClassesAdapter.ViewHolder>{

    Context mContext;
    ArrayList<MyClassModel> classes = new ArrayList<>();

    public MyClassesAdapter(Context mContext, ArrayList<MyClassModel> classes) {
        this.classes = classes;
        this.mContext = mContext;
    }

    @Override
    public MyClassesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent,false);
        ViewHolder view = new ViewHolder(v);

        return view;
    }

    @Override
    public void onBindViewHolder(MyClassesAdapter.ViewHolder holder, int position) {
        holder.subName.setText(classes.get(position).getSubjectName());
        holder.subRoom.setText(classes.get(position).getSubjectRoom());
        holder.subTeacher.setText(classes.get(position).getSubjectTeacher());
        holder.subStart.setText(classes.get(position).getSubjectTimeStart());
        holder.subEnd.setText(classes.get(position).getSubjectTimeEnd());
        holder.subDate.setText(classes.get(position).getSubjectDate());
        holder.subKey.setText(classes.get(position).getSubjectKey());
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView subName, subRoom, subTeacher, subStart, subEnd, subDate, subKey;
        private CardView classCard;
        public ViewHolder(View itemView) {
            super(itemView);

            subName = (TextView) itemView.findViewById(R.id.subjectName);
            subRoom = (TextView) itemView.findViewById(R.id.subjectRoom);
            subTeacher = (TextView) itemView.findViewById(R.id.subjectTeacher);
            subStart = (TextView) itemView.findViewById(R.id.timeStart);
            subEnd = (TextView) itemView.findViewById(R.id.timeEnd);
            subDate = (TextView) itemView.findViewById(R.id.subjectDate);
            subKey = (TextView) itemView.findViewById(R.id.subjectKey);
            classCard = (CardView)itemView.findViewById(R.id.classCard);
        }
    }
}
