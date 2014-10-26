package com.demo.dialogdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DialogDemo extends Activity {
     private Button button = null;
     private TextView info = null;
     private int selectedItem = 0;
    
     private String[] items = {"北京", "天津", "重庆", "成都", "西安"};
    
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          super.setContentView(R.layout.main);
         
          button = (Button)this.findViewById(R.id.button);
          info = (TextView)this.findViewById(R.id.info);
         
          button.setOnClickListener(new OnButtonClickListenerImpl());
     }
    
     private class OnButtonClickListenerImpl implements OnClickListener{
          @Override
          public void onClick(View v) {
               Dialog dialog = new AlertDialog.Builder(DialogDemo.this)
               .setIcon(R.drawable.icon_info)
               .setTitle("请选择你最喜欢的城市")
               .setNegativeButton("取消", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         // TODO something on click cancel
                    }
               })
               .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         info.setText("您最喜欢的城市是：" + DialogDemo.this.items[DialogDemo.this.selectedItem]);
                    }
               })
               .setSingleChoiceItems(DialogDemo.this.items, 0, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         DialogDemo.this.selectedItem = which; // 做一个临时保存，当点击“确定”的时候再改变info的Text
                    }
               }) .create();
              
               dialog.show();
          }
     }
}
