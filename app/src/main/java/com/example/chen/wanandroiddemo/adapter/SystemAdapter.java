package com.example.chen.wanandroiddemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.core.bean.System;
import com.example.chen.wanandroiddemo.ui.activity.SystemArticlesActivity;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 14:39
 */
public class SystemAdapter extends RecyclerView.Adapter<SystemAdapter.SystemHolder> {
    private List<System> mSystems;
    private Context mContext;

    public SystemAdapter(Context context, List<System> systems) {
        mContext = context;
        mSystems = systems;
    }

    @NonNull
    @Override
    public SystemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_system, viewGroup, false);
        return new SystemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemHolder systemHolder, final int i) {
        final System system = mSystems.get(i);
        systemHolder.title.setText(system.getName());
        StringBuilder s = new StringBuilder();
        for (System childrenSystem : system.getChildren()) {
            s.append(childrenSystem.getName() + "    ");
        }
        s.delete(s.length() - 4, s.length());
        systemHolder.content.setText(s.toString());
        systemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SystemArticlesActivity.class);
                intent.putExtra(Constants.SYSTEM, system);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSystems.size();
    }

    class SystemHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;

        public SystemHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }
}
