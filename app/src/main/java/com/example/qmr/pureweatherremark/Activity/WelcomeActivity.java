package com.example.qmr.pureweatherremark.Activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.qmr.pureweatherremark.BaseAppCompatActivity;
import com.example.qmr.pureweatherremark.R;
import com.example.qmr.pureweatherremark.fragment.EmptyFragment;
import com.example.qmr.pureweatherremark.interfaces.IBaseView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "WelcomeActivity";

    @BindView(R.id.vp_indicate)
    ViewPager vp_indicate;
    @BindView(R.id.rl_bg)
    RelativeLayout rl_bg;
    @BindView(R.id.btn_in)
    Button btn_in;


    FragmentStatePagerAdapter pagerAdapter;
    EmptyFragment[] emptyFragments;
    ValueAnimator bgColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initView();
        initAnim();
        initAdapter();
    }

    void initView() {
        btn_in.setOnClickListener(this);
        btn_in.setVisibility(View.GONE);
        btn_in.setAlpha(0);
    }

    void initAdapter() {
        emptyFragments = new EmptyFragment[4];
        for (int i = 0; i < 4; i++)
            emptyFragments[i] = new EmptyFragment();

        pagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return emptyFragments[position];
            }

            @Override
            public int getCount() {
                return emptyFragments.length;
            }
        };

        vp_indicate.setAdapter(pagerAdapter);
        vp_indicate.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrolled: " + position + " " + positionOffset + " " + positionOffsetPixels);
                bgColor.setCurrentPlayTime((long) (position * 3000 + positionOffset * 3000));
                if (position == 2) {
                    btn_in.setVisibility(View.VISIBLE);
                    btn_in.setAlpha(positionOffset);
                } else if (position < 2) {
                    btn_in.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    void initAnim() {
        bgColor = ObjectAnimator.ofInt(rl_bg, "backgroundColor", Color.parseColor("#039be5"), Color.parseColor("#0a8f08"), Color.CYAN,
                getResources().getColor(R.color.orange));
        bgColor.setDuration(9000);
        bgColor.setEvaluator(new ArgbEvaluator());
        bgColor.setRepeatCount(ValueAnimator.INFINITE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_in:
                Toast.makeText(this, "1212121212", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,ShowWeatherActivity.class));
                break;
        }
    }
}
