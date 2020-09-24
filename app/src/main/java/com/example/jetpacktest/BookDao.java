package com.example.jetpacktest;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertBook(Book book);

    @Query("select * from Book")
    List<Book> loadALLBooks();
}
