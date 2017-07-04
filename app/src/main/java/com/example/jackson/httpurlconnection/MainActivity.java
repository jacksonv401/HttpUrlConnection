package com.example.jackson.httpurlconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter mAdapter;
    List<ModelClass> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareData();
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mAdapter = new MyAdapter(mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void prepareData() {
        ModelClass a;
        for(int i = 0; i<10 ; i++) {
            a = new ModelClass("Jackson"+i, "Varghese"+i);
            mList.add(a);
        }
    }
}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    List<ModelClass> mList;
    public MyAdapter(List<ModelClass> mList) {
        this.mList = mList ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtfName.setText(mList.get(position).getName());
        holder.txtlName.setText(mList.get(position).getSecondName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtfName, txtlName;
        public ViewHolder(View itemView) {
            super(itemView);
            txtfName = (TextView)itemView.findViewById(R.id.txtfName);
            txtlName = (TextView)itemView.findViewById(R.id.txtlName);
        }
    }
}
