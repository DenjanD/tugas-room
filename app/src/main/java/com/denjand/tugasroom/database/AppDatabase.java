package com.denjand.tugasroom.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.denjand.tugasroom.database.model.Product;
import com.denjand.tugasroom.database.daos.ProductDao;
import com.denjand.tugasroom.database.model.Kasir;
import com.denjand.tugasroom.database.daos.KasirDao;

@Database(entities = {Product.class, Kasir.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract KasirDao kasirDao();
}