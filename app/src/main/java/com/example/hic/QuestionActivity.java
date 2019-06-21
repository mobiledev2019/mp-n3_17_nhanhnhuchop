package com.example.hic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    private TextView question, time, live, mark, questionCounting;
    //private TextView backdoor;
    private Button saver, saverImplement[], answer[];
    protected QuestionDAO quesDAO;
    private Question q;
    private CountDownTimer countdowner;
    private SharedPreferences preferences;
    private String sharedPrefFile = "currentMark";
    private int kiep = 5, quesNo = 0;
    private boolean saverUsed[];
    private final String CURRENT_MARK_KEY = "current";
    int diem, timeManager;
    private ConstraintLayout constraintLayout;
//    public static ArrayList<DiemCao> dsDiemCao;
//    private DiemCaoDAO diemCaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        quesDAO = new QuestionDAO(this);
        answer = new Button[4];
        question = findViewById(R.id.question);
        answer[0] = findViewById(R.id.answerA);
        answer[1] = findViewById(R.id.answerB);
        answer[2] = findViewById(R.id.answerC);
        answer[3] = findViewById(R.id.answerD);
        time = findViewById(R.id.countdowner);
        live = findViewById(R.id.living);
        live.setText(String.format("%s", kiep));
        questionCounting = findViewById(R.id.questionCounting);
        questionCounting.setText(String.format("%s", quesNo));
        mark = findViewById(R.id.mark);
        preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        diem = preferences.getInt(CURRENT_MARK_KEY, 0);
        mark.setText(String.format("%s", diem));
        saver = findViewById(R.id.saver);
        saverImplement = new Button[3];
        saverImplement[0] = findViewById(R.id.doiCH);
        saverImplement[1] = findViewById(R.id.themkiep);
        saverImplement[2] = findViewById(R.id.themTime);
        saverUsed = new boolean[3];
        createQuestion();
//        backdoor = findViewById(R.id.countDB);
//        backdoor.setText(String.format("%s", quesDAO.countQuestion()));
        timeManager = 120000;
        constraintLayout = findViewById(R.id.main);
        constraintLayout.setBackgroundResource(R.drawable.questionphay);
//        diemCaoDAO = new DiemCaoDAO(this);
//        diemCaoDAO.addDiemCao("",-1);
//        dsDiemCao = diemCaoDAO.getTatCaDiem();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setQuestion();
        countdowner = new CountDownTimer(timeManager, 1000) {
            public void onTick(long mil) {
                --timeManager;
                time.setText(String.format("%s", mil / 1000));
            }
            public void onFinish() {
                time.setText("0");
                Intent in = new Intent(QuestionActivity.this, GameOverActivity.class);
                startActivity(in);
            }
        }.start();
    }

    private void setQuestion() {
        q = quesDAO.getQuestion();
        question.setText(q.getQuestion());
        for (int i = 0; i < 4; i++) {
            answer[i].setText(q.getAnswer().get(i).getAnswer());
        }
        for (int i = 0; i < 3; i++) {
            saverUsed[i] = true;
        }
    }

    public void answerRespond(View view) {
        int buttonTaped = -1;
        for (int i = 0; i < 4; i++) {
            if (answer[i].getId() == view.getId()) buttonTaped = i;
        }
        final int tapped = buttonTaped;
        if (q.getAnswer().get(buttonTaped).isResult()) {
            setQuestion();
            ++quesNo;
            questionCounting.setText(String.format("%s", quesNo));
            if (quesNo == 10) {
                diem += Integer.parseInt(time.getText().toString());
                mark.setText(String.format("%s", diem));
//                int diemCao = Integer.parseInt(time.getText().toString());
//                if (dsDiemCao.size() < 10 || (dsDiemCao.size() >= 10 && diemCao > dsDiemCao.get(9).getDiem())) {
//                    int i = dsDiemCao.size() >= 10 ? 9 : dsDiemCao.size()-1;
//                    while (diemCao > dsDiemCao.get(i).getDiem()) {
//                        dsDiemCao.set(i,dsDiemCao.get(i-1));
//                        --i;
//                    }
//                    Intent intent = new Intent(QuestionActivity.this, RecordActivity.class);
//                    String key = "DIEM NE";
//                    intent.putExtra(key, time.getText().toString()+" "+i);
//                    startActivity(intent);
//                }
//                else {
                    Intent intent = new Intent(QuestionActivity.this, WinGameActivity.class);
                    startActivity(intent);
//                }
            }
        } else {
            --kiep;
            quesNo = 0;
            questionCounting.setText(String.format("%s", quesNo));
            live.setText(String.format("%s", kiep));
            if (kiep == 0) {
                Intent in = new Intent(QuestionActivity.this, GameOverActivity.class);
                startActivity(in);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putInt(CURRENT_MARK_KEY, diem);
        preferencesEditor.apply();
    }

    public void sos(View view) {
        saver.setVisibility(View.INVISIBLE);
        for (int i = 0; i < 3; i++) saverImplement[i].setVisibility(View.VISIBLE);
    }

    public void doiCH(View view) {
        saver.setVisibility(View.VISIBLE);
        for (int i = 0; i < 3; i++) saverImplement[i].setVisibility(View.INVISIBLE);
        if (diem > 50) {
            diem -= 50;
            mark.setText(String.format("%s", diem));
            setQuestion();
        }
    }

    public void themKiep(View view) {
        saver.setVisibility(View.VISIBLE);
        for (int i = 0; i < 3; i++) saverImplement[i].setVisibility(View.INVISIBLE);
        if (diem > 50) {
            diem -= 50;
            mark.setText(String.format("%s", diem));
            ++kiep;
            live.setText(String.format("%s", kiep));
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

    public void themThoiGian(View view) {
        saver.setVisibility(View.VISIBLE);
        for (int i = 0; i < 3; i++) saverImplement[i].setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this, TimeSaverActivity.class);
        String diemKey = "DIEM NE";
        intent.putExtra(diemKey, diem);
        countdowner.cancel();
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int timePhanHoi = data.getIntExtra("THOI GIAN DUOC LUA CHON", 0);
                timeManager += timePhanHoi * 1000;
                diem -= timePhanHoi * 5;
                mark.setText(String.format("%s", diem));
                countdowner = new CountDownTimer(timeManager, 1000) {
                    public void onTick(long mil) {
                        --timeManager;
                        time.setText(String.format("%s", mil / 1000));
                    }
                    public void onFinish() {
                        time.setText("0");
                        Intent in = new Intent(QuestionActivity.this, GameOverActivity.class);
                        startActivity(in);
                    }
                }.start();
            }
        }
    }

    private void createQuestion() {
        if (!quesDAO.checkNotNull()) {
            quesDAO.addQuestion("Linh là từ Hán Việt để chỉ số bao nhiêu?", "0", "1", "2", "3", "A");
            quesDAO.addQuestion("Phần mở rộng của 1 tệp dữ liệu được tạo bởi Microsoft Excel 2007 là gì?", "docx", "xls", "pptx", "xlsx", "D");
            quesDAO.addQuestion("Fujiko F. Fujio là tác giả bộ truyện tranh nào?", "Thám tử lừng danh Conan", "Doreamon", "Vua trò chơi Yugi-oh!", "Naruto", "B");
            quesDAO.addQuestion("Hiện nay bảng tuần hoàn có tất cả bao nhiêu nguyên tố hóa học?", "103", "109", "118", "114", "C");
            quesDAO.addQuestion("Kết tủa lam Fe4[Fe(CN)6]3 có tên gọi là gì?", "Xanh Paris", "Xanh Berlin", "Xanh London", "Xanh Barcelona", "B");
            quesDAO.addQuestion("Tảo thuộc giới nào?", "Khởi sinh", "Thực vật", "Động vật", "Nguyên sinh", "D");
            quesDAO.addQuestion("\"Em gái mưa\", \"Sống xa anh chẳng dễ dàng\", \"Yêu một người vô tâm\" là những bài hát do ai sáng tác?", "Mr. Siro", "Khắc Hưng", "Tiên Cookie", "Masew", "A");
            quesDAO.addQuestion("Liên bang Micronesia có tất cả bao nhiêu bang?", "5", "6", "3", "4", "D");
            quesDAO.addQuestion("Lươn là loài động vật thuộc lớp nào?", "Giun đốt", "Thân mềm", "Cá xương", "Côn trùng", "C");
            quesDAO.addQuestion("Trong các họ thực vật có hoa, họ nào có số loài lớn nhất?", "Lan", "Cúc", "Đậu", "Bồ hòn", "A");
            quesDAO.addQuestion("Việt Nam có bao nhiêu tỉnh vừa giáp biển, vừa có biên giới trên đất liền?", "6", "7", "8", "9", "D");
            quesDAO.addQuestion("Múi giờ xa nhất cách múi GMT bao nhiêu giờ đồng hồ?", "11", "12", "13", "14", "D");
            quesDAO.addQuestion("\"Vô tình\", \"Túy âm\" là những ca khúc nổi tiếng của ca sĩ nào?", "Natri", "Xesi", "Rubidi", "Kali", "B");
            quesDAO.addQuestion("Sân nhà của câu lạc bộ Everton nằm ở thành phố nào?", "Liverpool", "London", "Manchester", "Cardiff", "A");
            quesDAO.addQuestion("Bạn Cá Tra năm nay 17 tuổi. Hỏi Bạn Cá Tra có bao nhiêu ngày sinh?", "1", "2", "3", "4", "A");
            quesDAO.addQuestion("Quốc gia nào có diện tích nhỏ nhất Đông Nam Á?", "Singapore", "Đông Timor", "Palau", "Maldives", "A");
            quesDAO.addQuestion("Quốc gia nào có diện tích nhỏ nhất châu Á?", "Maldives", "Singapore", "Bahrain", "Brunei", "A");
            quesDAO.addQuestion("Liên đoàn bóng đá cấp châu lục nào có số thành viên ít nhất?", "Nam Mĩ", "Châu Đại Dương", "Châu Nam Cực", "Bắc, Trung, Mĩ và Caribbean", "A");
            quesDAO.addQuestion("Có bao nhiêu quốc gia và vùng lãnh thổ châu Đại Dương trực thuộc Liên đoàn bóng đá châu Á?", "1", "2", "3", "4", "C");
            quesDAO.addQuestion("Khoảng cách giữa nốt cao nhất và nốt thấp nhất trong 1 quãng báo là bao nhiêu cung?", "6", "7", "8", "9", "A");
            quesDAO.addQuestion("Số huyện đảo tối đa trực thuộc một tỉnh Việt Nam là bao nhiêu?", "1", "2", "3", "4", "C");
            quesDAO.addQuestion("Khí nào dưới đây không phải là 1 vũ khí hóa học?", "Sarin", "Photgen", "Clo", "Lưu huỳnh dioxit", "D");
            quesDAO.addQuestion("Từ 2015-2020, giải Copa America tổ chức bao nhiêu lần?", "1", "2", "3", "4", "D");
            quesDAO.addQuestion("Thuật toán Miller-Rabin dùng để kiểm tra tính chất gì của số tự nhiên?", "Số hoàn hảo", "Số nguyên tố", "Số kì quặc", "Số bất khả xâm phạm", "B");
            quesDAO.addQuestion("2^(n-1)*(2^n-1) với 2^n-1 là số nguyên tố là công thức tìm ra loại số nào?", "Số hoàn hảo", "Số nguyên tố", "Số kì quặc", "Số bất khả xâm phạm", "A");
            quesDAO.addQuestion("Đảo nào có diện tích lớn nhất Biển Đông?", "Hải Nam", "Cát Bà", "Phú Quốc", "Phú Lâm", "A");
            quesDAO.addQuestion("Dung dịch nào tạo tủa khi nhỏ amoniac dư vô?", "FeCl2", "ZnCl2", "CuCl2", "NiCl2", "A");
            quesDAO.addQuestion("Dòng họ nào có thời gian cầm quyền ở Việt Nam dưới 200 năm?", "Lý", "Nguyễn Phúc", "Trịnh", "Trần", "D");
            quesDAO.addQuestion("\"Those were the days\" là tên tiếng Anh của bài hát nào?", "Tình ca du mục", "Đôi mắt huyền", "Tình nhạt phai", "Ánh trăng nói hộ lòng tôi", "A");
            quesDAO.addQuestion("Quận nào có diện tích rộng nhất Hà Nội?", "Hà Đông", "Long Biên", "Hoàng Mai", "Nam Từ Liêm", "A");
            quesDAO.addQuestion("Vòng bảng UEFA Europa League có bao nhiêu đội tham dự?", "16", "24", "32", "48", "D");
            quesDAO.addQuestion("Năm nào có nhiều bão trên biển Đông nhất?", "2013", "2017", "2009", "2006", "B");
            quesDAO.addQuestion("\"Mưa trên biển vắng\" là bài hát được đặt lời Việt từ bài hát của quốc gia nào?", "Trung Quốc", "Ý", "Nga", "Pháp", "D");
        }
    }
}