//package com.example.hic;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//
//public class DiemCaoDAO extends SQLiteOpenHelper {
//
//    private Context context;
//
//    public DiemCaoDAO(Context context) {
//        super(context, "nhanh_nhu_chop", null, 1);
//        this.context = context;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String sqlQuery = "CREATE TABLE diemRatCao ( " +
//                "id integer primary key, " +
//                "ten nvarchar(1000), " +
//                "diem integer" +
//                ")";
//        db.execSQL(sqlQuery);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//    }
//
//    public void addDiemCao(String ten, int diem) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("ten", ten);
//        values.put("diem", diem);
//        db.insert("diemRatCao", null, values);
//        db.close();
//    }
//
//    public void truncate() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "DROP TABLE diemRatCao";
//        db.execSQL(query);
//        onCreate(db);
//    }
//
//    public ArrayList<DiemCao> getTatCaDiem() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT * FROM diemRatCao";
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        DiemCao dc = new DiemCao();
//        ArrayList<DiemCao> listDiem = new ArrayList<>();
//        if (cursor.moveToFirst()) {
//            do {
//                dc.setNguoi(cursor.getString(1));
//                dc.setDiem(cursor.getInt(2));
//                listDiem.add(dc);
//            } while (cursor.moveToNext());
//        }
//        return listDiem;
//    }
//
//    public boolean checkNotNull() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT id FROM diemRatCao LIMIT 1";
//        Cursor cursor = db.rawQuery(query, null);
//        return cursor.moveToFirst();
//    }
//
//    public int countDiemCao() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT count(id) FROM diemRatCao";
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            return cursor.getInt(0);
//        }
//        return -1;
//    }
//}