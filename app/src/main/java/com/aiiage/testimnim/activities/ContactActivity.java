package com.aiiage.testimnim.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aiiage.testimnim.adapter.ContactAdapter;
import com.aiiage.testimnim.adapter.MessageAdapter;
import com.aiiage.testimnim.R;
import com.aiiage.testimnim.entities.UserInfo;

import java.util.ArrayList;

/**
 * Created By HuangQing on 2018/7/23 11:00
 **/
public class ContactActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<UserInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);//绑定联系人页面
        list = getUserList();
        mRecyclerView = findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new ContactAdapter(list);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        //使用默认的间隔线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {//选择聊天人
                Intent intent = new Intent(ContactActivity.this, ChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", list.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Intent intent = new Intent(ContactActivity.this, MultiChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", list.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private ArrayList<UserInfo> getUserList() {
        ArrayList<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo("lin", "lin"));
        list.add(new UserInfo("SaikiASH", "SaikiASH"));
        list.add(new UserInfo("123456","123456"));
        list.add(new UserInfo("群聊","147258369"));
        return list;
    }
}
