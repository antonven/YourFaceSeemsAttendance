package myapps.wycoco.com.yourfaceseemsattendance.Models;

/**
 * Created by dell on 3/19/2017.
 */

public class TeacherModel {

    private String teacherName;

    public TeacherModel(String teacherName) {
        this.teacherName = teacherName;
    }

    public TeacherModel() {
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
