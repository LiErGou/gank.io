package com.example.administrator.myapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.service.entity.Book;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service.presenter.BookPresenter;
import com.example.administrator.myapplication.service.view.BookView;

public class MainActivity extends AppCompatActivity {

    private Button sendBtn;
    private TextView tv;
    private BookPresenter mBookPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBtn=(Button)findViewById(R.id.connect_btn);
        tv=(TextView)findViewById(R.id.tv);
        mBookPresenter=new BookPresenter(this);
        mBookPresenter.onCreate();
//        mBookPresenter.attachMyView(mBookView);mBookVie
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
            }
        });

    }

    private BookView mBookView=new BookView() {
        @Override
        public void onSuccess(Book book) {
            tv.setText(book.toString());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this,result, Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mBookPresenter.onStop();
    }
}
