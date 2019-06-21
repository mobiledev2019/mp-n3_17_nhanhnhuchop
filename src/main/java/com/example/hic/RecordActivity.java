//package com.example.hic;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//
//public class RecordActivity extends AppCompatActivity {
//
//    private DiemCaoDAO diemCaoDAO;
//
//    private EditText name;
//    private TextView diem;
//    private String data, gt, vt;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_record);
//        diem = findViewById(R.id.diemSo);
//        name = findViewById(R.id.nhapTen);
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("DIEM NE");
//        gt = data.substring(0, data.indexOf(" "));
//        vt = data.substring(data.indexOf(" ")+1, data.length());
//        diem.setText(gt);
//        QuestionActivity.dsDiemCao.set(Integer.parseInt(vt), new DiemCao(Integer.parseInt(gt),name.getText().toString()));
//        Intent sth = new Intent(RecordActivity.this, WinGameActivity.class);
//        startActivity(sth);
//    }
//
//    protected void onDestroy() {
//        super.onDestroy();
//        diemCaoDAO = new DiemCaoDAO(this);
//        diemCaoDAO.truncate();
//        for (int i = 0; i < 10; i++) {
//            diemCaoDAO.addDiemCao(QuestionActivity.dsDiemCao.get(i).getNguoi(), QuestionActivity.dsDiemCao.get(i).getDiem());
//        }
//    }
//}
