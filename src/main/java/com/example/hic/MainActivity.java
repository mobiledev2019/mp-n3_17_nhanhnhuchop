package com.example.hic;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    //private DiemCaoDAO diemCaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.peelPage);
        layout.setBackgroundResource(R.drawable.main);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

    public void record(View view) {
//        Intent intent = new Intent(this, RecordDisplayActivity.class);
//        startActivity(intent);
    }

//    protected void onDestroy() {
//        super.onDestroy();
//        diemCaoDAO = new DiemCaoDAO(this);
//        diemCaoDAO.truncate();
//        for (int i = 0; i < 10; i++) {
//            diemCaoDAO.addDiemCao(QuestionActivity.dsDiemCao.get(i).getNguoi(), QuestionActivity.dsDiemCao.get(i).getDiem());
//        }
//    }
}
