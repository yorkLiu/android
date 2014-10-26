package com.demo.xmlstoragedemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class JSONDemo extends Activity {

	private EditText name;
	private EditText address;
	private EditText workAddress;
	private EditText phone;
	private Button saveBtn;
	private Button readBtn;
	
	private static final String DIR = "jsons";
	private static final String FILE_NAME = "myJSON.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);

		// find view by id
		name = (EditText) this.findViewById(R.id.name);
		address = (EditText) this.findViewById(R.id.address);
		workAddress = (EditText) this.findViewById(R.id.workAddress);
		phone = (EditText) this.findViewById(R.id.phone);
		saveBtn = (Button) this.findViewById(R.id.saveBtn);
		readBtn = (Button) this.findViewById(R.id.readBtn);
		
		/// set click listeners for save button
		saveBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				JSONDemo.this.createJSON();
			}
		});
		
		/// set click listeners for read file button to read data from json file
		readBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				JSONDemo.this.readDataFromFile();
			}
		});
	}
	
	private void createJSON(){
		if (Environment.MEDIA_UNMOUNTED.equals(Environment.getExternalStorageState())) {
			Dialog dialog = new AlertDialog.Builder(this)
					.setTitle("SD卡错误")
					.setMessage("请检查该手机的SD卡")
					.setNegativeButton("Yes",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// finish this app
									JSONDemo.this.finish();
								}
							}).create();
			dialog.show();

		}

		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + DIR + File.separator + FILE_NAME);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		
		
		///////构造json
		//// {data:[{object}....], success: true}
		JSONArray jsonArray = new JSONArray();
		JSONObject obj = new JSONObject();
		try {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("name", this.name.getText().toString());
			jsonObj.put("address", this.address.getText().toString());
			jsonObj.put("workAddress", this.workAddress.getText().toString());
			jsonObj.put("phone", this.phone.getText().toString());
			jsonArray.put(jsonObj);
			obj.put("data", jsonArray).put("success", Boolean.TRUE);
			
			
			//// 将json object 写到到SDCard中
			PrintWriter writer = new PrintWriter(file);
			writer.append(obj.toString());
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void readDataFromFile(){
		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + DIR + File.separator + FILE_NAME);
		
		try {
			Scanner scanner = new Scanner(file);
			String jsonStr = "";
			if(scanner.hasNext()){
				jsonStr += scanner.nextLine();
			}
			
			if(!"".equals(jsonStr.trim())){
				///读取json文件中的内容
				///json 文件内容如下： {data:[{name: name1, address: addressValue ....}....], success: true}
				JSONObject jsonObject = new JSONObject(jsonStr);
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				for(int i = 0;i < jsonArray.length();i++){
					JSONObject object = (JSONObject) jsonArray.get(i);
					this.name.setText(object.get("name").toString());
					this.address.setText(object.get("address").toString());
					this.workAddress.setText(object.get("workAddress").toString());
					this.phone.setText(object.get("workAddress").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
