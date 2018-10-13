package haodong.com.views_diy.prac_animator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    }
}
