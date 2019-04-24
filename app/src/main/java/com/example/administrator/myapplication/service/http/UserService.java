package com.example.administrator.myapplication.service.http;



import com.example.administrator.myapplication.service.entity.UserBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface UserService {
    @GET("users/{user_name}/repos")
    Observable<List<UserBean>> getUsers(
            @Path ("user_name") String userName,
            @Query("page") int page,
            @Query("per_page")  int count

    );

//    @GET("api/data/{type}/{count}/{page}")
//    Observable<ResultBean> getDatas(
//            @Path("type") String type,
//            @Path("count") int count,
//            @Path("page") int page
//    );

}
