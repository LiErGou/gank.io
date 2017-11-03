package com.example.administrator.myapplication.service.view;

import com.example.administrator.myapplication.service.entity.Book;

/**
 * Created by Administrator on 2017/10/31.
 */

public interface BookView extends MyView {
    void onSuccess(Book book);
    void onError(String result);
}
