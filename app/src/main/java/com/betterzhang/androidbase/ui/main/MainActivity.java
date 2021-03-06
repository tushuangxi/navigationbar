package com.betterzhang.androidbase.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.betterzhang.androidbase.R;
import com.betterzhang.androidbase.ui.market.HomeMarketFragment;
import com.betterzhang.androidbase.ui.personal.HomePersonalFragment;
import com.betterzhang.androidbase.ui.trade.HomeTradeFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottomBar)
    BottomNavigationBar bottomBar;
    @BindView(R.id.content)
    FrameLayout content;

    private HomePageFragment mPageFragment;
    private HomeMarketFragment mMarketFragment;
    private HomeTradeFragment mTradeFragment;
    private HomePersonalFragment mPersonalFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//绑定Activity 必须在setContentView之后

        bottomBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomBar.addItem(new BottomNavigationItem(R.drawable.selector_tab_homepage, "首页"))
                .setActiveColor(R.color.common_tab_text_selected)
                .setInActiveColor(R.color.common_tab_text_unselected)
                .addItem(new BottomNavigationItem(R.drawable.selector_tab_market, "行情"))
                .setActiveColor(R.color.common_tab_text_selected)
                .setInActiveColor(R.color.common_tab_text_unselected)
                .addItem(new BottomNavigationItem(R.drawable.selector_tab_trade, "交易"))
                .setActiveColor(R.color.common_tab_text_selected)
                .setInActiveColor(R.color.common_tab_text_unselected)
                .addItem(new BottomNavigationItem(R.drawable.selector_tab_personal, "我的"))
                .setActiveColor(R.color.common_tab_text_selected)
                .setInActiveColor(R.color.common_tab_text_unselected)
                .setFirstSelectedPosition(0)
                .initialise();

        setDefaultFragment();

        bottomBar.setTabSelectedListener(this);
    }

    // 设置默认的fragment
    private void setDefaultFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mPageFragment = new HomePageFragment();
        transaction.replace(R.id.content, mPageFragment);
        transaction.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {

            /*
            // 第一种退出App方式
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                Snackbar snackbar = Snackbar.make(content, getString(R.string.text_exit_app), Snackbar.LENGTH_SHORT);
                snackbar.setAction(getString(R.string.text_cancel), v -> exitTime = 0)
                        .setActionTextColor(ContextCompat.getColor(this, R.color.common_tab_text_selected));
                View snakebarView = snackbar.getView();
                TextView textView = (TextView) snakebarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(getResources().getColor(R.color.white));
                snackbar.show();
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    }
                }.start();
            }
            return true;
            */

            // 第二种App退出方式
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (position) {
            case 0:
                if (mPageFragment == null) {
                    mPageFragment = new HomePageFragment();
                }
                transaction.replace(R.id.content, mPageFragment);
                break;
            case 1:
                if (mMarketFragment == null) {
                    mMarketFragment = new HomeMarketFragment();
                }
                transaction.replace(R.id.content, mMarketFragment);
                break;
            case 2:
                if (mTradeFragment == null) {
                    mTradeFragment = new HomeTradeFragment();
                }
                transaction.replace(R.id.content, mTradeFragment);
                break;
            case 3:
                if (mPersonalFragment == null) {
                    mPersonalFragment = new HomePersonalFragment();
                }
                transaction.replace(R.id.content, mPersonalFragment);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
