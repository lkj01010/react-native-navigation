package com.reactnativenavigation.controllers;

import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.facebook.react.bridge.Callback;
import com.reactnativenavigation.events.EventBus;
import com.reactnativenavigation.events.ModalDismissedEvent;
import com.reactnativenavigation.layouts.HoBoxScreenLayout;
import com.reactnativenavigation.layouts.ScreenStackContainer;
import com.reactnativenavigation.layouts.SingleScreenLayout;
import com.reactnativenavigation.params.ContextualMenuParams;
import com.reactnativenavigation.params.ScreenParams;
import com.reactnativenavigation.params.TitleBarButtonParams;
import com.reactnativenavigation.params.TitleBarLeftButtonParams;
import com.reactnativenavigation.screens.SingleScreen;
import com.reactnativenavigation.views.LeftButtonOnClickListener;

import java.util.List;
import java.util.Stack;

public class HoBoxController implements LeftButtonOnClickListener {
    private final AppCompatActivity activity;
    private final RelativeLayout parent;
    private Stack<HoBoxScreenLayout> stack = new Stack<HoBoxScreenLayout>();

    public HoBoxController(AppCompatActivity activity, RelativeLayout parent) {
        this.activity = activity;
        this.parent = parent;
    }

    public void showBox(ScreenParams screenParams) {
        HoBoxScreenLayout layout = new HoBoxScreenLayout(activity, null, screenParams, this);
        parent.addView(layout);

        stack.peek
    }

    public void dismissBox() {

    }

    @Override
    public void destroy() {
        layout.destroy();
    }

    @Override
    public void onBackPressed() {
        if (!layout.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onTitleBarBackButtonClick() {
//        if (!layout.onBackPressed()) {
//            onBackPressed();
//        }
        return true;
    }

    @Override
    public void onSideMenuButtonClick() {
    }
}
