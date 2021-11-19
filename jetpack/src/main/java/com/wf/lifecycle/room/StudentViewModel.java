package com.wf.lifecycle.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * @author : wf
 * @date : 2021年11月10日 16:50
 */
public class StudentViewModel extends AndroidViewModel {

    private final StudentRepository mStudentRepository;

    public StudentViewModel(Application application) {
        super(application);
        mStudentRepository = new StudentRepository(application);
    }

    public void addStudent(Student... students) {
        mStudentRepository.addStudent(students);
    }

    public void deleteStudent(Student... students) {
        mStudentRepository.deleteStudent(students);
    }

    public void deleteAllStudent() {
        mStudentRepository.deleteAllStudent();
    }

    public void updateAllStudent(Student... students) {
        mStudentRepository.updateAllStudent(students);
    }

    public LiveData<List<Student>> queryAllStudent() {
        return mStudentRepository.queryAllStudent();
    }
}