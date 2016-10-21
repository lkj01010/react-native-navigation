package com.reactnativenavigation.controllers;

import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.facebook.react.bridge.Callback;
import com.reactnativenavigation.events.EventBus;
import com.reactnativenavigation.events.ModalDismissedEvent;
import com.reactnativenavigation.layouts.ScreenStackContainer;
import com.reactnativenavigation.params.ContextualMenuParams;
import com.reactnativenavigation.params.ScreenParams;
import com.reactnativenavigation.params.TitleBarButtonParams;
import com.reactnativenavigation.params.TitleBarLeftButtonParams;
import com.reactnativenavigation.screens.ScreenStack;

import java.util.List;
import java.util.Stack;

public class VModalController {
    private final AppCompatActivity activity;
    private ScreenStack screenStack;

    public VModalController(AppCompatActivity activity) {
        this.activity = activity;
//        screenStack = new ScreenStack();
    }


}
