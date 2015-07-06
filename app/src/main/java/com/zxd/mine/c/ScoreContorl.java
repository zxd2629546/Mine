package com.zxd.mine.c;

import android.app.Activity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.zxd.mine.R;

/**
 * Created by zxd on 2015/7/4.
 */
public class ScoreContorl {
    private TextView scoreView;
    private TextView tempScoreView;
    private Activity activity;
    private AnimationSet scoreAnim;
    private int score;
    private int mulityMine;

    public ScoreContorl(Activity activity){
        this.activity = activity;
        initViews();
        initScore();
        initAnim();
    }

    public void initScore(){
        score = 0;
        mulityMine = 0;
        scoreView.setText(score + "");
    }

    private void initViews(){
        scoreView = (TextView)activity.findViewById(R.id.score);
        tempScoreView = (TextView)activity.findViewById(R.id.temp_score);
    }

    private void initAnim(){
        //初始化 Translate动画
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f,0.1f,-100.0f);
        //初始化 Alpha动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);

        //动画集
        scoreAnim = new AnimationSet(true);
        scoreAnim.addAnimation(translateAnimation);
        scoreAnim.addAnimation(alphaAnimation);

        //设置动画时间 (作用到每个动画)
        scoreAnim.setDuration(1000);
        scoreAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tempScoreView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tempScoreView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void updateScore(int temp){
        if (score + temp < 0){
            temp = -score;
        }
        score += temp;
        if (temp == 0) {
            tempScoreView.setText("-" + temp);
        } else if(temp < 0){
            tempScoreView.setText(temp + "");
        } else {
            tempScoreView.setText("+" + temp);
        }
        tempScoreView.startAnimation(scoreAnim);
        scoreView.setText(score + "");
    }

    public int val2score(int val){
        int ans = 0;
        if (val < 0){
            mulityMine += 1;
            ans = (5 + mulityMine) * mulityMine / 2;
        } else {
            mulityMine = 0;
            ans = -1 * Math.min(val, 8);
        }
        return ans;
    }

    public void reset(){
        initScore();
    }

    public int getScore() {
        return score;
    }
}
