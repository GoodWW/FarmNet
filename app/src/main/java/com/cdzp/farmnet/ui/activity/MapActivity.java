package com.cdzp.farmnet.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseView;
import com.cdzp.farmnet.bean.MapBean;
import com.cdzp.farmnet.contract.map.MapContract;
import com.cdzp.farmnet.contract.map.MapPresenter;
import com.cdzp.farmnet.contract.map.MapRecyclerAdapter;
import com.cdzp.farmnet.utils.GpsUtil;
import com.cmonbaby.ioc.core.annotation.ContentView;
import com.cmonbaby.ioc.core.annotation.InjectView;
import com.cmonbaby.ioc.core.annotation.OnClick;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.activity_map)
public class MapActivity extends BaseView<MapPresenter, MapContract.View>  /*implements SensorEventListener */ {

    private static final String TAG = "地址信息";
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    private boolean isPermissionRequested;

    MapView mMapView;
    BaiduMap mBaiduMap;
    //    @InjectView(R.id.tvOk)
//    private TextView tvOk;
    @InjectView(R.id.llLayout)
    LinearLayout llLayout;
    @InjectView(R.id.img)
    private ImageView img;
    // UI相关
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private SwipeRecyclerView mRecyclerView;
    private MapRecyclerAdapter mAdapter;
    private List<Poi> mDataList;
    private String result;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!GpsUtil.isOPen(this)) {
            openGPS();
        }
        requestPermission();
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        initView();
        initMap();
    }


    @Override
    public MapContract.View getContract() {
        return new MapContract.View<MapBean>() {
            @Override
            public void handlerBackResult(MapBean mapBean) {

            }
        };
    }

    @Override
    public MapPresenter getPresenter() {
        return new MapPresenter();
    }

    private void initView() {
        // 地图初始化
        mMapView = findViewById(R.id.bmapView);
        mMapView.showScaleControl(false);  // 设置比例尺是否可见（true 可见/false不可见）
        mMapView.showZoomControls(false);  // 设置缩放控件是否可见（true 可见/false不可见）
        View child = mMapView.getChildAt(1); // 找到百度LOGO控件对象
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.GONE);  // 设置child这个控件是否可见（VISIBLE 可见/INVISIBLE不可见 /GONE 完全隐藏）
        }
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setOnItemClickListener(mItemClickListener); // RecyclerView Item点击监听。
        mAdapter = new MapRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);


    }

    @OnClick({/*R.id.username, R.id.password, R.id.tvOk, */ R.id.back, R.id.img})
    private void click(View view) {
        switch (view.getId()) {
//            case R.id.username:
//                Toast.makeText(LoginActivity.this, "uesrname", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.btnLogin:
//                startActivity(HomeActivity.class);
//                break;
//            case R.id.tvOk:
//                putIntent();
//                break;
            case R.id.back:
                finish();
                break;
            case R.id.img:
                comBack();
                break;
        }

    }

    private void comBack() {
        LatLng ll = new LatLng(locLoction.getLatitude(),
                locLoction.getLongitude());
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        initLocation();
        mAdapter.notifyDataSetChanged(mDataList);
    }

    private void putIntent() {
        Intent i = new Intent();
        Log.e(TAG, "click: 数据:    " + result);
        i.putExtra("result", result);
        this.setResult(300, i);
        finish();
    }


    /**
     * Item点击监听。
     */
    private OnItemClickListener mItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View itemView, int position) {
            result = mDataList.get(position).getName() + mDataList.get(position).getAddr();
            putIntent();
        }
    };


    private void initMap() {

        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        /**
         * 默认高精度，设置定位模式
         * LocationMode.Hight_Accuracy 高精度定位模式：这种定位模式下，会同时使用
         * LocationMode.Battery_Saving 低功耗定位模式：这种定位模式下，不会使用GPS，只会使用网络定位。
         * LocationMode.Device_Sensors 仅用设备定位模式：这种定位模式下，
         */
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true); // 打开gps
        /**
         * 默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
         * 目前国内主要有以下三种坐标系：
         1. wgs84：目前广泛使用的GPS全球卫星定位系统使用的标准坐标系；
         2. gcj02：经过国测局加密的坐标；
         3. bd09：为百度坐标系，其中bd09ll表示百度经纬度坐标，bd09mc表示百度墨卡托米制坐标；
         * 在国内获得的坐标系类型可以是：国测局坐标、百度墨卡托坐标 和 百度经纬度坐标。
         在海外地区，只能获得WGS84坐标。请在使用过程中注意选择坐标。
         */
        option.setCoorType("bd09ll");
        /**
         * 默认0，即仅定位一次；设置间隔需大于等于1000ms，表示周期性定位
         * 如果不在AndroidManifest.xml声明百度指定的Service，周期性请求无法正常工作
         * 这里需要注意的是：如果是室外gps定位，不用访问服务器，设置的间隔是3秒，那么就是3秒返回一次位置
         如果是WiFi基站定位，需要访问服务器，这个时候每次网络请求时间差异很大，设置的间隔是3秒，
         只能大概保证3秒左右会返回就一次位置，有时某次定位可能会5秒才返回
         */
        option.setScanSpan(3000);
        /**
         * 默认false，设置是否需要地址信息
         * 返回省、市、区、街道等地址信息，这个api用处很大，
         很多新闻类app会根据定位返回的市区信息推送用户所在市的新闻
         */
        option.setIsNeedAddress(true);
        /**
         * 默认false，设置是否需要位置语义化结果
         * 可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
         */
        option.setIsNeedLocationDescribe(true);
        /**
         * 默认false,设置是否需要设备方向传感器的方向结果
         * 一般在室外gps定位时，返回的位置信息是带有方向的，但是有时候gps返回的位置也不带方向，
         这个时候可以获取设备方向传感器的方向
         * wifi基站定位的位置信息是不带方向的，如果需要可以获取设备方向传感器的方向
         */
        option.setNeedDeviceDirect(false);
        /**
         * 默认false，设置是否当gps有效时按照设定的周期频率输出GPS结果
         * 室外gps有效时，周期性1秒返回一次位置信息，其实就是设置了
         locationManager.requestLocationUpdates中的minTime参数为1000ms，1秒回调一个gps位置
         * 如果设置了mOption.setScanSpan(3000)，那minTime就是3000ms了，3秒回调一个gps位置
         */
        option.setLocationNotify(false);
        /**
         * 默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
         * 如果你已经拿到了你要的位置信息，不需要再定位了，不杀死留着干嘛
         */
        option.setIgnoreKillProcess(true);
        /**
         * 默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
         * POI就是获取到的位置附近的一些商场、饭店、银行等信息
         */
        option.setIsNeedLocationPoiList(true);
        /**
         * 默认false，设置是否收集CRASH信息，默认收集
         */
        option.SetIgnoreCacheException(false);
        /**
         * 默认false，设置定位时是否需要海拔高度信息，默认不需要，除基础定位版本都可用
         */
        option.setIsNeedAltitude(false);
        mLocClient.setLocOption(option);
        mLocClient.start();
        initLocation();
    }

    /**
     * 定位到当前位置
     */
    private void initLocation() {
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        // 修改为自定义marker
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location);
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker));
        MapStatus.Builder builder1 = new MapStatus.Builder();
        builder1.overlook(0);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder1.build()));

    }

    /**
     * Android6.0之后需要动态申请权限
     */
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {

            isPermissionRequested = true;

            ArrayList<String> permissionsList = new ArrayList<>();

            String[] permissions = {
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
            };

            for (String perm : permissions) {
                if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(perm)) {
                    permissionsList.add(perm);
                    // 进入到这里代表没有权限.
                }
            }

            if (!permissionsList.isEmpty()) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), 0);
            }
        }
    }

    BDLocation locLoction;

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.e(TAG, "=============================");
            // map view 销毁后不在处理新接收的位置
            if (location != null && mMapView != null && location.getLocType() != BDLocation.TypeServerError) {
                mDataList = location.getPoiList();
//                Log.e(TAG, "大小: " + pois.size());
//                for (Poi poi : pois) {
//                    Log.e(TAG, "地址: " + poi.getName() + "=====" + poi.getAddr() + "====" + poi.getId()
//                            + "=====" + poi.getRank() + "=====" + poi.getTags());
//                }

                Log.e(TAG, "======: " + location.getLatitude() + "=====" + location.getLongitude());
                mCurrentAccracy = location.getRadius();
                locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(mCurrentDirection).latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
                result = mDataList.get(0).getName() + mDataList.get(0).getAddr();
                llLayout.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mAdapter.notifyDataSetChanged(mDataList);
                img.setClickable(true);
//                tvOk.setClickable(true);

                mBaiduMap.setMyLocationData(locData);
                if (isFirstLoc) {
                    mCurrentLat = location.getLatitude();
                    mCurrentLon = location.getLongitude();
                    locLoction = location;
                    isFirstLoc = false;
                    LatLng ll = new LatLng(mCurrentLat, mCurrentLon);
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(ll).zoom(18.0f);
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                }
            }
        }

    }

    AlertDialog.Builder builder;

    private void openGPS() {
        if (builder == null) {
            builder = new AlertDialog.Builder(MapActivity.this)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("提示")
                    .setMessage("您未开启GPS定位！")
                    .setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    })
                    .setPositiveButton("去开启", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, 887);
                            dialogInterface.dismiss();
                        }
                    })
                    .setCancelable(false);
        }
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!GpsUtil.isOPen(this)) {
            openGPS();
        }
        switch (requestCode) {
            case 887:
                //开启GPS，重新添加地理监听
                initLocation();
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        mDataList = null;
        super.onDestroy();
    }

}
