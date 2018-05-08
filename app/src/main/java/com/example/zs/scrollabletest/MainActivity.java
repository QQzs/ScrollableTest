package com.example.zs.scrollabletest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cpoopc.scrollablelayoutlib.ScrollableLayout;
import com.example.zs.scrollabletest.util.ChannelUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ScrollableLayout scrollableLayout;

    private ViewPager viewPager;

    private List<ScrollAbleFragment> fgList;

    private TextView mTv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollableLayout = (ScrollableLayout) findViewById(R.id.scrollableLayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mTv = (TextView) findViewById(R.id.tv);
        mTv.setOnClickListener(this);
        fgList = new ArrayList<>();
        fgList.add(new RecyclerViewFragment());
        fgList.add(new RecyclerViewFragment());

        scrollableLayout.getHelper().setCurrentScrollableContainer(fgList.get(0));

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fgList.get(position);
            }

            @Override
            public int getCount() {
                return fgList.size();
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                scrollableLayout.getHelper().setCurrentScrollableContainer(fgList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onClick(View v) {

        String channel = ChannelUtil.getChannel(this,"def");
        Toast.makeText(MainActivity.this, "channel == " + channel, Toast.LENGTH_SHORT).show();

    }
}
