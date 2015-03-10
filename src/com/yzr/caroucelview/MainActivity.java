package com.yzr.caroucelview;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

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
        CaroucelOptions carouceloptions=new CaroucelOptions(5, uris, R.drawable.default_user_icon, R.drawable.default_user_icon, R.drawable.guanzhu_off, R.drawable.guanzhu_on);
        caroucelView.setOptions(carouceloptions);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

  
}
