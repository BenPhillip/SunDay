package com.example.gzp.sunday.Api;

import com.example.gzp.sunday.db.City;
import com.example.gzp.sunday.db.County;
import com.example.gzp.sunday.db.Province;

import java.util.List;



import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Ben on 2017/12/10.
 */

public interface AreaService {
    @GET("china")
    Observable<List<Province>> getProvince();

    @GET("china/{provinceCode}")
    Observable<List<City>>getCity(@Path("provinceCode")int code);

    @GET("china/{provinceCode}/{cityCode}")
    Observable<List<County>>getCounty(@Path("provinceCode")int code, @Path("cityCode")int code1);

}
