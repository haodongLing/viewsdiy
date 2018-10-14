package haodong.com.views_diy.prac_animator;

import android.animation.Animator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import haodong.com.views_diy.R;

public class Anim1Activity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim1);
        btn=findViewById(R.id.btn_anim_anim1);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.translation_to_right_circle);
        btn.startAnimation(animation);
        ValueAnimator valueAnimator=ValueAnimator.ofInt(btn.getLayoutParams().height,100);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue= (int) animation.getAnimatedValue();
                btn.getLayoutParams().height=currentValue;
                btn.requestLayout();
            }
        });
        valueAnimator.start();

    }
    /**
     * 解决width,height等属性无法改变的三种方式
     */
    //1.给你的对象添加get和set方法，如果权限够。
    // 2. 使用一个类来包装原始对象，简介提供get和set方法。
    @SuppressLint("WrongConstant")
    private void performAnimateByTwo(){
        ViewWrapper wrapper=new ViewWrapper(btn);
        ObjectAnimator animator=ObjectAnimator.ofInt(wrapper,"width",500);
        animator.setStartDelay(500);
        animator.setRepeatMode(ObjectAnimator.INFINITE);
        animator.setRepeatCount(0);
        animator.start();

    }
    private static  class ViewWrapper{
        private View myTarget;

        public ViewWrapper(View myTarget) {
            this.myTarget = myTarget;
        }
        private int getWidth(){
            return myTarget.getLayoutParams().width;
        }
        private void setWidth(int width){
            myTarget.getLayoutParams().width=width;
            myTarget.requestLayout();
        }


    }

    // 3. 采用ValueAnimator，监听动画过程，自己实现属性的改变。
    private void performAnimByvalue(final View viewTarget) {
        ValueAnimator valueAnimator=ValueAnimator.ofInt(btn.getLayoutParams().width,500);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private IntEvaluator evaluator=new IntEvaluator();
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int currentValue= (int) animation.getAnimatedValue();
                float fraction=animation.getAnimatedFraction();
                viewTarget.getLayoutParams().width=evaluator.evaluate(fraction,viewTarget.getLayoutParams().width,500);
                viewTarget.requestLayout();



            }
        });

    }
}
