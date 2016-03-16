package com.example.shuweizhao.mywokba;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback,LocationSource.OnLocationChangedListener {
    private FragmentManager fragmentManager;

    private GoogleMap mgoogleMap;


    private com.google.android.gms.maps.MapFragment mapFragment;
    private OrderFragment orderFragment;
    private ShakeFragment shakeFragment;
    private ProfileFragment profileFragment;

    private RelativeLayout mapLayout;
    private RelativeLayout orderLayout;
    private RelativeLayout shakeLayout;
    private RelativeLayout profileLayout;

    private ImageView mapImage;
    private ImageView orderImage;
    private ImageView shakeImage;
    private ImageView profileImage;

    private TextView mapText;
    private TextView orderText;
    private TextView shakeText;
    private TextView profileText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
    }

    private void initView() {

        mapLayout = (RelativeLayout) findViewById(R.id.tab_map_layout);
        orderLayout = (RelativeLayout) findViewById(R.id.tab_order_layout);
        shakeLayout = (RelativeLayout) findViewById(R.id.tab_shake_layout);
        profileLayout = (RelativeLayout) findViewById(R.id.tab_profile_layout);

        mapImage = (ImageView) findViewById(R.id.tab_map_image);
        orderImage = (ImageView) findViewById(R.id.tab_order_image);
        shakeImage = (ImageView) findViewById(R.id.tab_shake_image);
        profileImage = (ImageView) findViewById(R.id.tab_profile_image);

        mapText = (TextView) findViewById(R.id.tab_map_text);
        orderText = (TextView) findViewById(R.id.tab_order_text);
        shakeText = (TextView) findViewById(R.id.tab_shake_text);
        profileText = (TextView) findViewById(R.id.tab_profile_text);

        mapLayout.setOnClickListener(this);
        orderLayout.setOnClickListener(this);
        shakeLayout.setOnClickListener(this);
        profileLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tab_map_layout:
                setTabSelection(0);
                break;
            case R.id.tab_order_layout:
                setTabSelection(1);
                break;
            case R.id.tab_shake_layout:
                setTabSelection(2);
                break;
            case R.id.tab_profile_layout:
                setTabSelection(3);
                break;
        }
    }

    private void setTabSelection(int id) {
        clearSelection();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (id) {
            case 0:
                mapLayout.setBackgroundColor(Color.parseColor("#03A9F4"));
                mapText.setTextColor(Color.WHITE);
                if (mapFragment == null) {
                    mapFragment = MapFragment.newInstance();
                    fragmentTransaction.add(R.id.content, mapFragment);
                } else {
                    fragmentTransaction.show(mapFragment);
                }
                mapFragment.getMapAsync(this);
                break;
            case 1:
                orderLayout.setBackgroundColor(Color.parseColor("#03A9F4"));
                orderText.setTextColor(Color.WHITE);
                if (orderFragment == null) {
                    orderFragment = new OrderFragment();
                    fragmentTransaction.add(R.id.content, orderFragment);
                } else {
                    fragmentTransaction.show(orderFragment);
                }
                break;
            case 2:
                shakeLayout.setBackgroundColor(Color.parseColor("#03A9F4"));
                shakeText.setTextColor(Color.WHITE);
                if (shakeFragment == null) {
                    shakeFragment = new ShakeFragment();
                    fragmentTransaction.add(R.id.content, shakeFragment);
                } else {
                    fragmentTransaction.show(shakeFragment);
                }
                break;
            case 3:
                profileLayout.setBackgroundColor(Color.parseColor("#03A9F4"));
                profileText.setTextColor(Color.WHITE);
                if (profileFragment == null) {
                    profileFragment = new ProfileFragment();
                    fragmentTransaction.add(R.id.content, profileFragment);
                } else {
                    fragmentTransaction.show(profileFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void clearSelection() {
        mapLayout.setBackgroundColor(Color.WHITE);
        mapText.setTextColor(Color.BLACK);
        orderLayout.setBackgroundColor(Color.WHITE);
        orderText.setTextColor(Color.BLACK);
        shakeLayout.setBackgroundColor(Color.WHITE);
        shakeText.setTextColor(Color.BLACK);
        profileLayout.setBackgroundColor(Color.WHITE);
        profileText.setTextColor(Color.BLACK);
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mapFragment != null) {
            transaction.hide(mapFragment);
        }
        if (orderFragment != null) {
            transaction.hide(orderFragment);
        }
        if (shakeFragment != null) {
            transaction.hide(shakeFragment);
        }
        if (profileFragment != null) {
            transaction.hide(profileFragment);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mgoogleMap.setMyLocationEnabled(true);
        } else {

            Toast.makeText(this, "failure in acquiring the permission", Toast.LENGTH_SHORT).show();

            return;
            // Show rationale and request permission.
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }


    @Override
    public void onLocationChanged(Location location) {

    }
}
