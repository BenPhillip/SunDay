package com.example.gzp.sunday.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.gzp.sunday.Api.WeatherService;
import com.example.gzp.sunday.Model.WeatherModel;
import com.example.gzp.sunday.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 百度定位 demo
 */
public class LocationActivity extends AppCompatActivity {
    public LocationClient mLocationClient;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        this.mTextView=findViewById(R.id.text_location);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());

        List<String> permisstionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission
                (LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permisstionList.add("Manifest.permission.ACCESS_FINE_LOCATION");
        }
        if (ContextCompat.checkSelfPermission
                (LocationActivity.this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            permisstionList.add("READ_PHONE_STATE");
        }
        if (ContextCompat.checkSelfPermission
                (LocationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permisstionList.add("WRITE_EXTERNAL_STORAGE");
        }
        if (!permisstionList.isEmpty()) {
            String [] permissions=permisstionList.toArray(new String[permisstionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, 1);
        }else{
            mLocationClient.start();
        }




    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permisstions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result:grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this,"must permit all ",Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                    }
                    mLocationClient.start();
                }else {
                    Toast.makeText(this,"unkonw error",Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:


        }
    }

    public class MyLocationListener extends BDAbstractLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            String l=bdLocation.getLongitude()+","+bdLocation.getLatitude();
            mTextView.setText(l);
            new WeatherModel().getCityInfo(l, WeatherService.key)
                    .subscribe(location -> {
                        ((TextView)findViewById(R.id.text_name)).setText(location.basic.city);
                    });
        }
    }

    private void setLocationClient(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        mLocationClient.setLocOption(option);
    }
}
