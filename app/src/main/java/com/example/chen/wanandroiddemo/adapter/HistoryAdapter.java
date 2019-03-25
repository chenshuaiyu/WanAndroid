package com.example.chen.wanandroiddemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.core.dao.HistoryRecord;
import com.example.chen.wanandroiddemo.ui.activity.SearchArticlesActivity;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/23 9:33
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    private Context mContext;
    private List<HistoryRecord> mHistoryRecords;

    private Callback mCallback;

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
    public void onBindViewHolder(@NonNull HistoryHolder historyHolder, final int i) {
        final HistoryRecord record = mHistoryRecords.get(i);
        historyHolder.key.setText(record.getData());
        historyHolder.key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.searchArticle(record.getData(), i);
            }
        });
        historyHolder.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.deleteHistoryRecord(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHistoryRecords.size();
    }

    class HistoryHolder extends RecyclerView.ViewHolder {
        TextView key;
        ImageView clear;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.key);
            clear = itemView.findViewById(R.id.clear);
        }
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void searchArticle(String data, int index);

        void deleteHistoryRecord(int index);
    }
}
