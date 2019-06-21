//package com.example.hic;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.Queue;
//
//public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.RecordViewHolder> {
//
//    private LayoutInflater inflater;
//
//    @Override
//    public int getItemCount() {
//        return QuestionActivity.dsDiemCao.size() >= 10 ? 10 : QuestionActivity.dsDiemCao.size();
//    }
//
//    public RecordListAdapter(Context context) {
//        inflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public RecordListAdapter.RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = inflater.inflate(R.layout.activity_one_record, parent, false);
//        return new RecordViewHolder(itemView, this);
//    }
//
//    @Override
//    public void onBindViewHolder(RecordViewHolder holder, int position) {
//        DiemCao current = QuestionActivity.dsDiemCao.get(position - 1);
//        holder.tv1.setText(position);
//        holder.tv2.setText(current.getNguoi());
//        holder.tv3.setText(current.getDiem());
//    }
//
//    class RecordViewHolder extends RecyclerView.ViewHolder {
//
//        public final TextView tv1, tv2, tv3;
//        final RecordListAdapter adapter;
//
//        public RecordViewHolder(View itemView, RecordListAdapter adapter) {
//            super(itemView);
//            tv1 = itemView.findViewById(R.id.stt);
//            tv2 = itemView.findViewById(R.id.ten);
//            tv3 = itemView.findViewById(R.id.diem);
//            this.adapter = adapter;
//        }
//    }
//}
