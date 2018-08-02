package com.example.hh.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText etSend;
    private EditText etReceive;

    private Button btnSend;
    private Button btnReceive;

    private RecyclerView msgRecyclerView;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsgs();

        etSend=findViewById(R.id.et_send);
        etReceive=findViewById(R.id.et_Receive);
        btnSend=findViewById(R.id.btn_send);
        btnReceive=findViewById(R.id.btn_Receive);
        msgRecyclerView=findViewById(R.id.msg_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = etSend.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    etSend.setText("");
                }
            }
        });

        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = etReceive.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_RECEIVED);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    etReceive.setText("");
                }
            }
        });

    }

    private void initMsgs() {
        Msg msg1 = new Msg("您好，李先生。",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("您好，请问那个人是谁？",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("这是张三，很高兴和您聊天。",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
