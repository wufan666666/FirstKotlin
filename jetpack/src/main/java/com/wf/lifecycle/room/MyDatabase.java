package com.wf.lifecycle.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * @author : wf
 * @date : 2021年11月10日 15:21
 */
@Database(entities = {Student.class}, version = 4, exportSchema = true)
public abstract class MyDatabase extends RoomDatabase {
    private final static String DATABASE_NAME = "my_db.db";

    private static MyDatabase instance = null;

    public static MyDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (MyDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context,
                            MyDatabase.class,
                            DATABASE_NAME)
                            .addMigrations(MIGRATION_1_2,MIGRATION_2_3,MIGRATION_3_4)
                            //.allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            //.createFromAsset("my_db.db")
                            .build();
                }
            }
        }
        return instance;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student add COLUMN sex INTEGER NOT NULL DEFAULT 1");
        }
    };
     static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student add COLUMN bar_ball INTEGER NOT NULL DEFAULT 1");
        }
    };
    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE temp_student (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                    "name TEXT," +
                    "age INTEGER NOT NULL," +
                    "sex TEXT DEFAULT 'M'," +
                    "bar_ball INTEGER NOT NULL DEFAULT 1" +
                    ")");
            database.execSQL("INSERT INTO temp_student (name,age,sex,bar_ball)" +
                    "SELECT name,age,sex,bar_ball FROM student");
            database.execSQL("DROP TABLE student");
            database.execSQL("ALTER TABLE temp_student RENAME TO student");
        }
    };


    public abstract StudentDao getStudentDao();
}