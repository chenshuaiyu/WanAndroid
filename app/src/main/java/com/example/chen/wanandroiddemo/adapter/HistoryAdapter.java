package com.example.chen.wanandroiddemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/23 9:33
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {


    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder historyHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HistoryHolder extends RecyclerView.ViewHolder{

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
