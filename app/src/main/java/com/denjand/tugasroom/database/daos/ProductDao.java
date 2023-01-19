package com.denjand.tugasroom.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.denjand.tugasroom.database.model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Insert
    void insert(Product task);

    @Delete
    void delete(Product task);

    @Update
    void update(Product task);

}
