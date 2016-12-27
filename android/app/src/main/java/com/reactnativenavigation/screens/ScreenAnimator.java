package com.reactnativenavigation.screens;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.reactnativenavigation.NavigationApplication;
import com.reactnativenavigation.params.StyleParams;
import com.reactnativenavigation.utils.ViewUtils;

import javax.annotation.Nullable;

public class ScreenAnimator {
    private final float translationX, translationY;
    private Screen screen;
    private StyleParams.AnimType animType;

    public ScreenAnimator(Screen screen, StyleParams.AnimType animType) {
        this.screen = screen;
        this.animType = animType;
        translationY = 1.f * ViewUtils.getScreenHeight();
        translationX = 1.f * ViewUtils.getScreenWidth();
    }

    public void show(boolean animate, final Runnable onAnimationEnd) {
        if (animate && this.animType != StyleParams.AnimType.NONE) {
            createShowAnimator(onAnimationEnd).start();
        } else {
            screen.setVisibility(View.VISIBLE);
            NavigationApplication.instance.runOnMainThread(onAnimationEnd, 200);
        }
    }

    public void showBackScreen() {
        createBackScreenAnimator(true, null).start();
    }

    public void show(boolean animate) {
        if (animate && this.animType != StyleParams.AnimType.NONE) {
            createShowAnimator(null).start();
        } else {
            screen.setVisibility(View.VISIBLE);
        }
    }

    public void hide(boolean animate, Runnable onAnimationEnd) {
        if (animate && this.animType != StyleParams.AnimType.NONE) {
            createHideAnimator(onAnimationEnd).start();
        } else {
            screen.setVisibility(View.INVISIBLE);
            onAnimationEnd.run();
        }
    }

    public void hideBackScreen() {
        createBackScreenAnimator(false, null).start();
    }

    private Animator createShowAnimator(final @Nullable Runnable onAnimationEnd) {
        ObjectAnimator translation;
        if (this.animType == StyleParams.AnimType.RIGHT) {
            translation = ObjectAnimator.ofFloat(screen, View.TRANSLATION_X, this.translationX, 0);
            translation.setInterpolator(new DecelerateInterpolator());
            translation.setDuration(250);
        } else { // (this.animType == StyleParams.AnimType.UP)

            translation = ObjectAnimator.ofFloat(screen, View.TRANSLATION_Y, this.translationY, 0);
            translation.setInterpolator(new DecelerateInterpolator());
            translation.setDuration(300);
        }

//        ObjectAnimator translationX = ObjectAnimator.ofFloat(screen, View.TRANSLATION_X, this.translationX, 0);
//        translationX.setInterpolator(new DecelerateInterpolator());
//        translationX.setDuration(200);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(translation);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                screen.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (onAnimationEnd != null) {
                    onAnimationEnd.run();
                }
            }
        });
        return set;
    }

    private Animator createHideAnimator(final Runnable onAnimationEnd) {
        AnimatorSet set = new AnimatorSet();

        if (this.animType == StyleParams.AnimType.RIGHT) {
            ObjectAnimator translation = ObjectAnimator.ofFloat(screen, View.TRANSLATION_X, this.translationX);
            translation.setInterpolator(new DecelerateInterpolator());
            translation.setDuration(250);

            set.playTogether(translation);

        } else { // (this.animType == StyleParams.AnimType.UP)
//            ObjectAnimator alpha = ObjectAnimator.ofFloat(screen, View.ALPHA, 1, 0);
//            alpha.setInterpolator(new DecelerateInterpolator());
//            alpha.setStartDelay(350);
//            alpha.setDuration(150);

            ObjectAnimator translation = ObjectAnimator.ofFloat(screen, View.TRANSLATION_Y, this.translationY);
            translation.setInterpolator(new DecelerateInterpolator());
            translation.setDuration(300);

            set.playTogether( translation);
        }

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (onAnimationEnd != null) {
                    onAnimationEnd.run();
                }
            }
        });
        return set;
    }

    public void showModal() {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(screen, View.ALPHA, 0, 1);
        alpha.setInterpolator(new DecelerateInterpolator());
        alpha.setDuration(300);

        ObjectAnimator translationY = ObjectAnimator.ofFloat(screen, View.TRANSLATION_Y, this.translationY, 0);
        translationY.setInterpolator(new DecelerateInterpolator());
        translationY.setDuration(280);


        AnimatorSet set = new AnimatorSet();
        set.playTogether(translationY);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                screen.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        set.start();
    }

    private Animator createBackScreenAnimator(boolean isShow, final @Nullable Runnable onAnimationEnd) {
        ObjectAnimator translation;
        if (isShow) {
            translation = ObjectAnimator.ofFloat(screen, View.TRANSLATION_X, -this.translationX, 0);
        }
        else {
            translation = ObjectAnimator.ofFloat(screen, View.TRANSLATION_X, 0, -this.translationX);
        }
        translation.setInterpolator(new DecelerateInterpolator());
        translation.setDuration(250);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(translation);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                screen.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (onAnimationEnd != null) {
                    onAnimationEnd.run();
                }
            }
        });
        return set;
    }
}
