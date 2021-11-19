package com.wf.lifecycle.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wf.lifecycle.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wf
 */
public class RoomActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private StudentAdapter mStudentAdapter;
    private List<Student> mStudents;
    private StudentViewModel mStudentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        mRecyclerView = findViewById(R.id.recycleview);
        mStudents = new ArrayList<>();
        mStudentAdapter = new StudentAdapter(mStudents);
        mRecyclerView.setAdapter(mStudentAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mStudentViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(StudentViewModel.class);
        mStudentViewModel.queryAllStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                mStudentAdapter.setStudents(students);
                mStudentAdapter.notifyDataSetChanged();
            }
        });
    }

    public void add(View view) {
        Student student0 = new Student("张无忌", 22);
        Student student1 = new Student("殷素素", 18);
        mStudentViewModel.addStudent(student0, student1);
    }


    public void delete(View view) {
        mStudentViewModel.deleteAllStudent();
        //Student student1 = new Student(66);
        //mStudentViewModel.deleteStudent(student1);
    }


    public void update(View view) {
        Student student0 = new Student(1, "faker", 18);
        mStudentViewModel.updateAllStudent(student0);
    }


    public void query(View view) {
        mStudentViewModel.queryAllStudent();
    }


}