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
        HttpUtil.getInstance().getWeatherService().getLocation("CN101210706",
                WeatherService.key).subscribe((cityLocation -> {
                    assertThat(cityLocation.basic.cityId,is("CN101210706"));

        }));
    }
}