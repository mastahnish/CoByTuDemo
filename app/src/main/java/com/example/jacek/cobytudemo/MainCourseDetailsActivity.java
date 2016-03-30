package com.example.jacek.cobytudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainCourseDetailsActivity extends AppCompatActivity {
    public static final String MAIN_COURSE_OBJECT = "main_course_object";
    private MainCourse singleMainCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_course_details);

        TextView tv = (TextView) findViewById(R.id.tv_main_courses_details);
        singleMainCourse = (MainCourse) getIntent().getSerializableExtra(MAIN_COURSE_OBJECT);
        tv.setText(singleMainCourse.getMainCourseName());
    }
}
