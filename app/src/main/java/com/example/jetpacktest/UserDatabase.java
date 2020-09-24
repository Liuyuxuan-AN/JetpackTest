package com.example.jetpacktest;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(version = 2,entities = {User.class,Book.class})
public abstract class UserDatabase extends RoomDatabase {
    public abstract  UserDao userDao();
    public abstract  BookDao bookDao();

    private static UserDatabase instance;

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("create table if not exists Book (id integer primary key autoincrement not null, name text not null,pages integer not null)");
        }
    };

    public static synchronized UserDatabase getInstance(Context context){
        if(instance == null){
            instance =  Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,"user_database").addMigrations(MIGRATION_1_2).build();
        }
        return instance;
    }
}
