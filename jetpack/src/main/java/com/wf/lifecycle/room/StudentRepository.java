package com.wf.lifecycle.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * @author : wf
 * @date : 2021年11月10日 16:40
 */
public class StudentRepository {

    private Context mContext;
    private final StudentDao mStudentDao;

    public StudentRepository(Context context) {
        mContext = context;
        mStudentDao = MyDatabase.getInstance(context).getStudentDao();
    }

    public void addStudent(Student... students) {
        new AddStudent(mStudentDao).execute(students);
    }

    class AddStudent extends AsyncTask<Student, Void, Void> {
        private StudentDao mStudentDao;

        public AddStudent(StudentDao studentDao) {
            mStudentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            this.mStudentDao.insert(students);
            return null;
        }
    }

    public void deleteStudent(Student... students) {
        new DeleteStudent(mStudentDao).execute(students);
    }

    class DeleteStudent extends AsyncTask<Student, Void, Void> {
        private StudentDao mStudentDao;

        public DeleteStudent(StudentDao studentDao) {
            mStudentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            this.mStudentDao.delete(students);
            return null;
        }
    }

    public void deleteAllStudent() {
        new DeleteAllStudent(mStudentDao).execute();
    }

    class DeleteAllStudent extends AsyncTask<Void, Void, Void> {
        private StudentDao mStudentDao;

        public DeleteAllStudent(StudentDao studentDao) {
            mStudentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... Void) {
            this.mStudentDao.deleteAllStudent();
            return null;
        }
    }

    public void updateAllStudent(Student... students) {
        new UpdateStudent(mStudentDao).execute(students);
    }

    class UpdateStudent extends AsyncTask<Student, Void, Void> {
        private StudentDao mStudentDao;

        public UpdateStudent(StudentDao studentDao) {
            mStudentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            this.mStudentDao.update(students);
            return null;
        }
    }

    public LiveData<List<Student>> queryAllStudent() {
        return mStudentDao.selectAllStudent();
    }

}