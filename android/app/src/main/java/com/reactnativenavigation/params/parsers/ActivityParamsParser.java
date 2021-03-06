package com.reactnativenavigation.params.parsers;

import android.os.Bundle;

import com.reactnativenavigation.params.ActivityParams;
import com.reactnativenavigation.params.AppStyle;
import com.reactnativenavigation.react.ImageLoader;

public class ActivityParamsParser extends Parser {
    public static ActivityParams parse(Bundle params) {
        ActivityParams result = new ActivityParams();

        AppStyle.setAppStyle(params);

        if (hasKey(params, "screen")) {
            result.type = ActivityParams.Type.SingleScreen;
            result.screenParams = ScreenParamsParser.parse(params.getBundle("screen"));
        }

        if (hasKey(params, "tabs")) {
            result.type = ActivityParams.Type.TabBased;
            result.tabParams = new ScreenParamsParser().parseTabs(params.getBundle("tabs"));
        }

        if (hasKey(params, "sideMenu")) {
            result.sideMenuParams = SideMenuParamsParser.parse(params.getBundle("sideMenu"));
        }

        result.animateShow = params.getBoolean("animateShow", true);

        if (hasKey(params, "midTabIcon")) {
            result.midTabIcon = ImageLoader.loadImage(params.getString("midTabIcon"));
        }

        return result;
    }
}
