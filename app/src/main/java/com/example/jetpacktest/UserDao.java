package com.example.jetpacktest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * 用于将对数据库User进行操作的语句进行封装
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)//当插入有冲突时的解决策略，这里是直接替换
    Long insertUser(User user);

    @Update
    int updateUser(User newUser);

    @Query("select * from User")
    List<User> loadAllUsers();

    @Query("select * from User where age > :age")
    List<User> loadUserOlderThan(int age);

    @Delete
    void deleteUser(User user);

    @Query("delete from User where lastName = :lastName")
    int deleteUserByLastName(String lastName);
}
