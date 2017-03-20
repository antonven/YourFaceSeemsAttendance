package myapps.wycoco.com.yourfaceseemsattendance.Models;

import android.content.Context;

/**
 * Created by dell on 3/19/2017.
 */

public class SubjectModel {

    private String subjectName;
    private String subjectId;
    private String subjectSchedule;

    Context mContext;

    public SubjectModel(Context context) {
        mContext = context;
    }

    public SubjectModel(String subjectName, String subjectId, String subjectSchedule) {
        this.subjectName = subjectName;
        this.subjectId = subjectId;
        this.subjectSchedule = subjectSchedule;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectSchedule() {
        return subjectSchedule;
    }

    public void setSubjectSchedule(String subjectSchedule) {
        this.subjectSchedule = subjectSchedule;
    }
}
