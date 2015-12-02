package com.example.tkq;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;



public class MainActivity extends Activity {
	private TextView TextView;
	
	private LocationClient mLocationClient = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView=(TextView)findViewById(R.id.dizhi);
		MyLocationListener mylisListener = new MyLocationListener();
		mLocationClient = new LocationClient(this);
		LocationClientOption option = new LocationClientOption();
		option.setIsNeedAddress(true);
		option.setAddrType("all");
		mLocationClient.setLocOption(option);
		mLocationClient.registerLocationListener(mylisListener);
		mLocationClient.start();
	}
	
	@Override
	protected void onDestroy() {
		mLocationClient.stop();	
		super.onDestroy();
	}
	
	private class MyLocationListener implements BDLocationListener{

		@Override
		public void onReceiveLocation(BDLocation location) {
			String province = location.getProvince();
			
			String city = location.getCity();
			String area = location.getDistrict();
			String street = location.getStreet();
			String streetnumber = location.getStreetNumber();
			
			TextView.setText("您当前的位置为："+province+","+city+","+area+","+street+","+streetnumber);
			Toast.makeText(getApplicationContext(), "您当前位置为"+province+","+city+","+area+","+street+","
					+streetnumber, Toast.LENGTH_LONG).show();
		}
		
	}
}