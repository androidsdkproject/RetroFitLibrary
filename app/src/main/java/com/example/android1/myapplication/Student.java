package com.example.android1.myapplication;

/**
 * Created by Android1 on 7/4/2017.
 */

public class Student {
    String StudentId;
    String StudentName;
    String StudentMarks;

    public String getStudentId() {
        return StudentId;
    }

    public String getStudentMarks() {
        return StudentMarks;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public void setStudentMarks(String studentMarks) {
        StudentMarks = studentMarks;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

}
