package cn.djzhao.annotationusage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import cn.djzhao.annotationusage.anno.Autowired;
import cn.djzhao.annotationusage.anno.AutowiredUtil;
import cn.djzhao.annotationusage.anno.ViewInject;
import cn.djzhao.annotationusage.anno.ViewInjectUtil;

public class MainActivity2 extends AppCompatActivity {

@ViewInject(R.id.textView)
private TextView textView;

@Autowired("username")
private String username;
@Autowired("age")
private int age;
@Autowired("info")
private String slogan;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);

    ViewInjectUtil.injectView(this);

    AutowiredUtil.injectValue(this);

    String msg = "Autowried info success:\n"
            + "username is " + username
            + "\nage is " + age
            + "\nslogan is " + slogan;
    textView.setText(msg);

}
}