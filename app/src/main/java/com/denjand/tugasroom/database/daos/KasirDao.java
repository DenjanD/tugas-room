package com.denjand.tugasroom.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.denjand.tugasroom.database.model.Kasir;

import java.util.List;

@Dao
public interface KasirDao {

    @Query("SELECT * FROM Kasir")
    List<Kasir> getAll();

    @Insert
    void insert(Kasir task);

    @Delete
    void delete(Kasir task);

    @Update
    void update(Kasir task);

}
