package com.aiiage.testimnim.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aiiage.testimnim.R;
import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

public class MainActivity extends AppCompatActivity {
//声明控件
    private EditText et1, et2;
    private Button btn;
    private SharedPreferences sp;
    private Gson gson=new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {//activity生命周期
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//连接登录页面
        sp=getSharedPreferences("userinfo",MODE_PRIVATE);
        et1 = findViewById(R.id.et1);//绑定xml中的控件
        et2 = findViewById(R.id.et2);
        btn = findViewById(R.id.btn);//绑定xml中的控件
        btn.setOnClickListener(new View.OnClickListener() {//为button声明点击事件
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        //封装登录信息.
        LoginInfo info = new LoginInfo(et1.getText().toString(), et2.getText().toString());//两个参数是账号密码
        //请求服务器的回调
        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo param) {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("userLogin",gson.toJson(param));
                        //跳转到消息页面
                        startActivity(new Intent(MainActivity.this, ContactActivity.class));//跳转到ContactActivity
                        //NimUIKit.startP2PSession(MainActivity.this, "1234");
                        finish();
                    }

                    @Override
                    public void onFailed(int code) {
                        Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onException(Throwable exception) {
                        Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
                    }

                };
        //发送请求.
        NIMClient.getService(AuthService.class).login(info)
                .setCallback(callback);

    }
}
