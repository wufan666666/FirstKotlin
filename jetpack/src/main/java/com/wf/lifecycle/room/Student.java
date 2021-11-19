package com.wf.lifecycle.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author : wf
 * @date : 2021年11月10日 15:14
 */
@Entity(tableName = "Student")
public class Student {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    public String name;

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    public int age;

    /**
     * V3.0.0
     */
   /* @ColumnInfo(name = "sex", typeAffinity = ColumnInfo.INTEGER)
    public int sex;*/
    /**
     * V4.0.0
     */
    @ColumnInfo(name = "sex", typeAffinity = ColumnInfo.TEXT)
    public String sex;

    @ColumnInfo(name = "bar_ball", typeAffinity = ColumnInfo.INTEGER)
    public int barBall;


    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Ignore
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Ignore
    public Student(int id) {
        this.id = id;
    }
}