package com.example.hic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TimeSaverActivity extends AppCompatActivity {

    private TextView markDisplayer;
    private EditText timeChooser;
    int currentMark;
    //private DiemCaoDAO diemCaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_saver);
        markDisplayer = findViewById(R.id.markDisplayer);
        timeChooser = findViewById(R.id.timeChooser);
        Intent intent = getIntent();
        currentMark = intent.getIntExtra("DIEM NE", 0);
        markDisplayer.setText(String.format("Tối đa %s điểm", (currentMark / 5)));
    }

    public void send(View view) {
        int timeChosen = Integer.parseInt(timeChooser.getText().toString());
        if (timeChosen * 5 <= currentMark) {
            Intent intent = new Intent();
            intent.putExtra("THOI GIAN DUOC LUA CHON", timeChosen);
            setResult(RESULT_OK, intent);
            finish();
        }
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
