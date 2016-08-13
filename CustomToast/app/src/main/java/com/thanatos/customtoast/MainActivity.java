package com.thanatos.customtoast;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.thanatos.custom_toast.CustomToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast toast = new Toast(this);
//        toast.setView(v);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.TOP,60,0);
//        toast.show();

    }

    public void onClick(View v) {
        View view = View.inflate(MainActivity.this, R.layout.toast, null);


//        CustomToast.setText(this, "哈哈，我是setText", CustomToast.LENGTH_SHORT).show();
//
//
//
//        CustomToast customToast=new CustomToast(this);
//        customToast.setText("我是设置文字的");
//        customToast.show();
        view.findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.setText(MainActivity.this,"我是图片？",CustomToast.LENGTH_SHORT).show();
            }
        });
//
        CustomToast custom = new CustomToast(this);
        custom.setView(view);
        custom.setDuration(5000);
        custom.show();
        //整体的点击事件，但是会拦截子控件的点击事件
//        custom.setOnCustomToastClickListener(new CustomToast.CustomT() {
//            @Override
//            public void OnClick() {
//                CustomToast.setText(MainActivity.this,"哦？",CustomToast.LENGTH_SHORT).show();
//            }
//        });
        AlertDialog

//
//        TextView tv1=new TextView(this);
//        tv1.setText("我是设置view的");
//        CustomToast customToast1=new CustomToast(this,tv1);
//        customToast1.show();
//
//        CustomToast c=new CustomToast(this,"我是文字");
//        c.show();
//
//        CustomToast cus=new CustomToast(this);
//        cus.show();

    }
}
