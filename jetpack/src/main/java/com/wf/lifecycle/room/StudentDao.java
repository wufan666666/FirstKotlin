package com.wf.lifecycle.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author : wf
 * @date : 2021年11月10日 15:18
 */
@Dao
public interface StudentDao {
    @Insert
    void insert(Student... students);

    @Delete
    void delete(Student... students);

    @Query("delete from student")
    void deleteAllStudent();

    @Update
    void update(Student... students);

    @Query("Select * from student")
    LiveData<List<Student>> selectAllStudent();

    @Query("Select * from Student where id = :id")
    List<Student> selectStudentById(int id);
}