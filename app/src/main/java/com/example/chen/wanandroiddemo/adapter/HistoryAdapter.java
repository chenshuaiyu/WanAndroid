package com.example.chen.wanandroiddemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/23 9:33
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    private Context mContext;
    private List<HistoryRecord> mHistoryRecords;

    public HistoryAdapter(Context context, List<HistoryRecord> historyRecords) {
        mContext = context;
        mHistoryRecords = historyRecords;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history, viewGroup, false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder historyHolder, int i) {
        historyHolder.key.setText(mHistoryRecords.get(i).getData());
        historyHolder.key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        historyHolder.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mHistoryRecords.size();
    }

    class HistoryHolder extends RecyclerView.ViewHolder{
        TextView key;
        ImageView clear;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.key);
            clear = itemView.findViewById(R.id.clear);
        }
    }
}
