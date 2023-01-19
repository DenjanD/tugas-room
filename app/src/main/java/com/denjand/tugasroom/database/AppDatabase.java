package com.denjand.tugasroom.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.denjand.tugasroom.database.model.Product;
import com.denjand.tugasroom.database.daos.ProductDao;

@Database(entities = {Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}