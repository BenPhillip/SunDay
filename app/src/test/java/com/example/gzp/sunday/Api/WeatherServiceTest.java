package com.example.gzp.sunday.Api;

import com.example.gzp.sunday.Util.HttpUtil;
import com.example.gzp.sunday.Util.LogUtil;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by BenPhillip on 2018/2/7.
 */
public class WeatherServiceTest {

    @Test
    public void getLocation(){
        HttpUtil.getInstance().getWeatherService().getLocation("auto_ip",
                WeatherService.key).subscribe((cityLocation -> {
                    assertEquals(cityLocation.locations.get(0).status,"ok");
                    //assertEquals(cityLocation.basic.cityId,"CN101210706");

        }));
    }
}