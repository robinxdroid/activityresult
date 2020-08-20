package net.robinx.activityresult.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.robinx.activityresult.ActivityResult;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/**
 * @author wangbin@huami.com
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvReturn = findViewById(R.id.tv_return);

        findViewById(R.id.btn_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = OtherActivity.getIntent(MainActivity.this);
                ActivityResult.with(MainActivity.this)
                        .start(intent, 100, new Function3<Integer, Integer, Intent, Unit>() {
                            @Override
                            public Unit invoke(Integer requestCode, Integer resultCode, Intent intent) {
                                if (intent != null) {
                                    String data = intent.getStringExtra("data");
                                    tvReturn.setText(data);
                                }

                                return null;
                            }
                        });
            }
        });

        findViewById(R.id.btn_kotlin_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KtMainActivity.class));
            }
        });
    }
}
