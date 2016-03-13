package com.example.shuweizhao.mywokba;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    private MapFragment mapFragment;
    private OrderFragment orderFragment;
    private ShakeFragment shakeFragment;
    private ProfileFragment profileFragment;

    private ImageView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
    }
}
