package com.cyl.cylserver.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.cyl.cylserver.R;

/**
 * Created by cyl
 * on 2016/7/30.
 * email:670654904@qq.com
 */
public class TwoViewActivity extends AppCompatActivity {
    private View topContainer,bottomContainer,indexContainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_view_layout);

        topContainer = findViewById(R.id.top_container);
        bottomContainer = findViewById(R.id.bottom_container);
        indexContainer = findViewById(R.id.index);

        indexContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        moveViewWithFinger(v, event.getRawX(), event.getRawY());
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 设置View的布局属性，使得view随着手指移动 注意：view所在的布局必须使用RelativeLayout 而且不得设置居中等样式
     *
     * @param view
     * @param rawX
     * @param rawY
     */
    private void moveViewWithFinger(View view, float rawX, float rawY) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) bottomContainer
                .getLayoutParams();
//        params.leftMargin = (int) rawX - indexContainer.getWidth() / 2;
        params.topMargin = (int) rawY - 23 - indexContainer.getHeight() / 2;

        bottomContainer.setLayoutParams(params);

        RelativeLayout.LayoutParams topParams = (RelativeLayout.LayoutParams) topContainer.getLayoutParams();
        params.bottomMargin = (int) rawY - 23 - indexContainer.getHeight() / 2;
        topContainer.setLayoutParams(topParams);

    }

}
