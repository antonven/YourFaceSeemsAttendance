package myapps.wycoco.com.yourfaceseemsattendance.Models;

import android.content.Context;

/**
 * Created by dell on 3/19/2017.
 */

public class SubjectModel {

    private String subjectName;
    private String subjectRoom;
    private String subjectTeacher;
    private String subjectTimeStart;
    private String subjectTimeEnd;
    private String subjectDate;
    private String subjectKey;

    Context mContext;

    public SubjectModel(Context context) {
        mContext = context;
    }

    public SubjectModel() {
    }

    public SubjectModel(String subjectName, String subjectRoom, String subjectTeacher, String subjectTimeStart, String subjectTimeEnd, String subjectDate, String subjectKey) {
        this.subjectName = subjectName;
        this.subjectRoom = subjectRoom;
        this.subjectTeacher = subjectTeacher;
        this.subjectTimeStart = subjectTimeStart;
        this.subjectTimeEnd = subjectTimeEnd;
        this.subjectDate = subjectDate;
        this.subjectKey = subjectKey;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectRoom() {
        return subjectRoom;
    }

    public void setSubjectRoom(String subjectRoom) {
        this.subjectRoom = subjectRoom;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public String getSubjectTimeStart() {
        return subjectTimeStart;
    }

    public void setSubjectTimeStart(String subjectTimeStart) {
        this.subjectTimeStart = subjectTimeStart;
    }

    public String getSubjectTimeEnd() {
        return subjectTimeEnd;
    }

    public void setSubjectTimeEnd(String subjectTimeEnd) {
        this.subjectTimeEnd = subjectTimeEnd;
    }

    public String getSubjectDate() {
        return subjectDate;
    }

    public void setSubjectDate(String subjectDate) {
        this.subjectDate = subjectDate;
    }

    public String getSubjectKey() {
        return subjectKey;
    }

    public void setSubjectKey(String subjectKey) {
        this.subjectKey = subjectKey;
    }
}
