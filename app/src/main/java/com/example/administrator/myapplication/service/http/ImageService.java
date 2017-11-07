package com.example.administrator.myapplication.service.http;



import com.example.administrator.myapplication.service.entity.ResultBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/29.
 */

public interface ImageService {
    @GET("api/data/福利/{count}/{page}")
    Observable<ResultBean> getGirls(
            @Path("count") int count,
            @Path("page") int page
    );

    @GET("api/data/{type}/{count}/{page}")
    Observable<ResultBean> getDatas(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );

}
