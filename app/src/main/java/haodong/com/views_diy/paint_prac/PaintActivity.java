package haodong.com.views_diy.paint_prac;

import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import haodong.com.views_diy.R;

public class PaintActivity extends AppCompatActivity {
    CusFontView fontView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        fontView=findViewById(R.id.font_view);
        fontView.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
