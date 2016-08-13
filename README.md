# CustomToast
一个类似于Toast的控件，可设置子控件点击事件和整体点击事件
###
##使用方法
####第一步：下载项目到本地
####第二步：将custom_toast作为library导入项目并添加道工程
####第三步：
    CustomToast.set*(*).show;
##
    CustomToast ct=new CustomToast(context);
    ct.*;
##
CustomToast的点击事件

    custom.setOnCustomToastClickListener(new     CustomToast.CustomT() {
            @Override
            public void OnClick() {
                CustomToast.setText(MainActivity.this,"哦？",CustomToast.LENGTH_SHORT).show();
            }
        });
#版本更替
###第一版：v0.1.0
#####功能：
添加了点击事件和子控件的点击事件
#####bug：
同时设置点击事件和子控件的点击事件会冲突。
