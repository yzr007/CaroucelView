package com.yzrsample.CaroucelViewDemo;

import java.util.ArrayList;
import java.util.List;

import com.yzr.caroucelview.CaroucelOptions;
import com.yzr.caroucelview.CaroucelView;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> uris=new ArrayList<String>();
        uris.add("http://img5.imgtn.bdimg.com/it/u=1316140783,33004756&fm=21&gp=0.jpg");
        uris.add("http://img2.imgtn.bdimg.com/it/u=622204480,1309555750&fm=21&gp=0.jpg");
        uris.add("http://img2.imgtn.bdimg.com/it/u=3180631282,3460019780&fm=21&gp=0.jpg");
        uris.add("http://img4.imgtn.bdimg.com/it/u=3718980873,3069925967&fm=21&gp=0.jpg");
        uris.add("http://img2.imgtn.bdimg.com/it/u=2930995583,2882074465&fm=21&gp=0.jpg");
        CaroucelView caroucelView=(CaroucelView) findViewById(R.id.caroucelView);
        CaroucelOptions carouceloptions=new CaroucelOptions(uris.size(), uris, R.drawable.default_img, R.drawable.default_img, R.drawable.index_normal, R.drawable.index_selected);
//        carouceloptions.setScaleType(ScaleType.CENTER_CROP);//default
//        carouceloptions.setPeriod(2000);//default
//        carouceloptions.setSubScriptViewMargin(10);//default
//        carouceloptions.setSubScriptViewSize(20);//default
        caroucelView.setOptions(carouceloptions);
       
    }



}
