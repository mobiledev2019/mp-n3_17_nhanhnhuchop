package com.example.hic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionDAO extends SQLiteOpenHelper {

    private Context context;
    private static int MAX_ID = 33;

    public QuestionDAO(Context context) {
        super(context, "nhanh_nhu_chop", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE question ( " +
                "id integer primary key, " +
                "question nvarchar(1000), " +
                "A nvarchar(150), " +
                "B nvarchar(150), " +
                "C nvarchar(150), " +
                "D nvarchar(150)," +
                "correctAnswer varchar(1)" +
                ")";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addQuestion(String question, String a, String b, String c, String d, String correctAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", question);
        values.put("a", a);
        values.put("b", b);
        values.put("c", c);
        values.put("d", d);
        values.put("correctAnswer", correctAnswer);
        db.insert("question", null, values);
        db.close();
    }

    public int updateQuestion(String question, String a, String b, String c, String d, String correctAnswer, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (question.length() > 0)
            values.put("question", question);
        if (question.length() > 0)
            values.put("a", a);
        if (question.length() > 0)
            values.put("b", b);
        if (question.length() > 0)
            values.put("c", c);
        if (question.length() > 0)
            values.put("d", d);
        if (question.length() > 0)
            values.put("correctAnswer", correctAnswer);
        return db.update("question", values, "id=?", new String[]{
                "" + id
        });
    }

    public int deleteQuestion(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("question", "id=?", new String[]{
                "" + id
        });
    }

    public Question getQuestion() {
        SQLiteDatabase db = this.getReadableDatabase();
        int idRan = new Random().nextInt(MAX_ID) + 1;
        String selectQuery = "SELECT * FROM question WHERE id >= " + idRan + " LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, null);
        Question q = new Question();
        if (cursor.moveToFirst()) {
            q.setQuestion(cursor.getString(1));
            ArrayList<Answer> answer = new ArrayList<>();
            for (int i = 0; i < 4; i++)
                answer.add(new Answer());
            answer.get(0).setAnswer(cursor.getString(2));
            answer.get(1).setAnswer(cursor.getString(3));
            answer.get(2).setAnswer(cursor.getString(4));
            answer.get(3).setAnswer(cursor.getString(5));
            char dapAn = cursor.getString(6).charAt(0);
            for (int i = 0; i < 4; i++) {
                if (dapAn - 'A' == i || dapAn - 'a' == i)
                    answer.get(i).setResult(true);
                else answer.get(i).setResult(false);
            }
            Collections.shuffle(answer);
            q.setAnswer(answer);
            return q;
        }
        db.close();
        return q;
    }

    public boolean checkNotNull() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM question LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        return cursor.moveToFirst();
    }

    public int countQuestion() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT count(id) FROM question";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return -1;
    }
}


