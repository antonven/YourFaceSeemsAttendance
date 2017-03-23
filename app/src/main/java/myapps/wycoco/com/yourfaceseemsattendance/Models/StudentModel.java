package myapps.wycoco.com.yourfaceseemsattendance.Models;

/**
 * Created by dell on 3/24/2017.
 */

public class StudentModel {

    private int studentPicture;
    private String studentName;
    private String studentTime;
    private String studentKey;

    public StudentModel() {
    }

    public StudentModel(int studentPicture, String studentName, String studentTime, String studentKey) {
        this.studentPicture = studentPicture;
        this.studentName = studentName;
        this.studentTime = studentTime;
        this.studentKey = studentKey;
    }

    public int getStudentPicture() {
        return studentPicture;
    }

    public void setStudentPicture(int studentPicture) {
        this.studentPicture = studentPicture;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentTime() {
        return studentTime;
    }

    public void setStudentTime(String studentTime) {
        this.studentTime = studentTime;
    }

    public String getStudentKey() {
        return studentKey;
    }

    public void setStudentKey(String studentKey) {
        this.studentKey = studentKey;
    }
}
