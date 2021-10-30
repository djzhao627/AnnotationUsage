package cn.djzhao.annotationusage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.djzhao.annotationusage.anno.ViewInject;
import cn.djzhao.annotationusage.anno.ViewInjectUtil;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.tv)
    private TextView tv;
    @ViewInject(R.id.btn)
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewInjectUtil.injectView(this);
        tv.setText("Inject View Success!!");
        btn.setText("You Got It");
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("username", "djzhao");
            intent.putExtra("age", 27);
            intent.putExtra("info", "As is a tale, so is life.");
            startActivity(intent);
        });
    }
}