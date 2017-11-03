package com.example.administrator.myapplication.service.http;



import com.example.administrator.myapplication.service.entity.Book;
import com.example.administrator.myapplication.service.entity.GImageBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/29.
 */

public interface ImageService {
    @GET("api/data/福利/{count}/{page}")
    Observable<GImageBean> getGirls(
            @Path("count") int count,
            @Path("page") int page
    );
}
