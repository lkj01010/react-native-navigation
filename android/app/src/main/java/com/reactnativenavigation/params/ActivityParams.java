package com.reactnativenavigation.params;

import android.graphics.drawable.Drawable;

import java.util.List;

public class ActivityParams {
    public enum Type {
        SingleScreen, TabBased
    }

    public Type type;
    public ScreenParams screenParams;
    public List<ScreenParams> tabParams;
    public Drawable midTabIcon;
    public SideMenuParams sideMenuParams;
    public boolean animateShow;
}
