package com.example.hic;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameOverActivity extends AppCompatActivity {

    //private DiemCaoDAO diemCaoDAO;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        constraintLayout = findViewById(R.id.gameover);
        constraintLayout.setBackgroundResource(R.drawable.game_over);
    }

    public void replay(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

    public void comeBackHome(View view) {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
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
