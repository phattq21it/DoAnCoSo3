package com.example.myapplication.DatabaseHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Order;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper
{
    private static final String DBName="EatItDB.db";
    private static final String TableName="OrderDetail";
    private static final String COL1="ProductID";
    private static final String COL2="ProductName";
    private static final String COL3="Quantity";
    private static final String COL4="Price";
    private static final String COL5="Discount";
    private static final String COL6="Phone";

    public DbHelper(@Nullable Context context) {
        super(context, DBName, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TableName+" ("+COL1+" integer primary key autoincrement,"
                +COL2+" text, "+COL3+" text, "+COL4+" text, "+COL5+" text,"+COL6+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TableName);
        onCreate(db);
    }
    public void insertData(Order order){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String name = order.getProductName();
        int quantity = Integer.parseInt(order.getQuantity());

// Kiểm tra xem tên đã tồn tại trong bảng hay chưa
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TableName + " WHERE " + COL2 + " = ?", new String[]{name});

        if(cursor.moveToFirst()) {
            // Nếu tên đã tồn tại, cập nhật số lượng
            @SuppressLint("Range") int oldQuantity = cursor.getInt(cursor.getColumnIndex(COL3));
            String newQuantity=String.valueOf( oldQuantity + quantity);

            ContentValues values = new ContentValues();
            values.put(COL3, newQuantity);

            sqLiteDatabase.update(TableName, values, COL2 + " = ?", new String[]{name});
        } else {
            // Nếu tên chưa tồn tại, chèn một dòng mới
            ContentValues values = new ContentValues();
            values.put(COL2, name);
            values.put(COL3, quantity);
            values.put(COL4, order.getPrice());

            sqLiteDatabase.insert(TableName, null, values);
        }

        cursor.close();

    }
    public void update(Order order){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COL2, order.getProductName());
        values.put(COL3, order.getQuantity());
        values.put(COL4, order.getPrice());
        values.put(COL5,order.getDiscount());
        String sql = "UPDATE " + TableName + " SET " + COL3 + " = COALESCE((SELECT " + COL3 + " FROM " + TableName + " WHERE " + COL2 + " = '" + order.getProductName() + "'), 0) + " + order.getQuantity() + " WHERE " + COL2 + " = '" + order.getProductName() + "'";
        sqLiteDatabase.execSQL(sql);
    }
//    public long deleteData(SanPham s){
//        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
//        long result=sqLiteDatabase.delete(TableName,COL1+"=?",new String[]{s.getMaSp()});
//
//        sqLiteDatabase.close();
//        return result;
//    }
    @SuppressLint("Range")
    public List<Order> getAllOrder(){
        SQLiteDatabase db=this.getReadableDatabase();
        List<Order> list= new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TableName;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                Order Order = new Order();
                Order.setProductId(Integer.parseInt(c.getString(c.getColumnIndex(COL1))));
                Order.setProductName(c.getString(c.getColumnIndex(COL2)));
                Order.setQuantity(c.getString(c.getColumnIndex(COL3)));
                Order.setPrice(c.getString(c.getColumnIndex(COL4)));
                Order.setDiscount(c.getString(c.getColumnIndex(COL5)));

                list.add(Order);
                c.moveToNext();
            }
        }
        c.close();
        return  list;
    }
    public void deleteAllDataInOrderDetailTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM OrderDetail");
        db.close();
    }
}