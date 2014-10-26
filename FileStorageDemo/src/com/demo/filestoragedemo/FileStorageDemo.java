package com.demo.filestoragedemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Scanner;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class FileStorageDemo extends Activity {

	private static final String FILE_NAME = "myfile.txt";
	private static final String DIR = "YorkDir";
	private TextView msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		msg = (TextView) this.findViewById(R.id.msg);
		
		// use openFileOutput openFileInput
		 readWriteWithFile();
		
		// save and read file in SD Card
		readWriteToSDCard();
		
		// use openRawResource read res file content
		readResourceFile();

	}

	private void readWriteWithFile() {
		// create and save file

		FileOutputStream outputStream = null;
		OutputStreamWriter writer = null;

		try {
			outputStream = super.openFileOutput(FILE_NAME,
					Activity.MODE_PRIVATE);
			writer = new OutputStreamWriter(outputStream);

			writer.write("姓名：YorkYong");
			writer.write("年龄：20");
			writer.write("Support Email：YorkYong@gamil.com");

			writer.flush();
			writer.close();

		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}

		// read file content
		FileInputStream inputStream = null;
		Scanner scanner = null;
		try {
			inputStream = super.openFileInput(FILE_NAME);
			scanner = new Scanner(inputStream);

			while (scanner.hasNext()) {
				this.msg.append(scanner.next() + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readWriteToSDCard() {
		// ~-------------------read SD Card  内容----------------------------------------------------
		// 判断手机上是否有SDCard:
		// Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + DIR + File.separator + FILE_NAME);
			if (!file.exists()) {
				Toast.makeText(this, "没有找到文件名为：" + FILE_NAME, Toast.LENGTH_LONG)
						.show();
			} else {
				Scanner scanner = null;

				try {
					scanner = new Scanner(new FileInputStream(file));
					while (scanner.hasNext()) {
						this.msg.append(scanner.next());
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (scanner != null) {
						scanner.close();
					}
				}
			}
		} else {
			Toast.makeText(this, "没有SD卡", Toast.LENGTH_LONG).show();
		}
		// !------------------- end read SD Card 内容  ----------------------------------------------------

		// ~-------------------save file 内容到 SD Card----------------------------------------------------

		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + DIR + File.separator + FILE_NAME);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			PrintStream out = null;
			try {
				out = new PrintStream(new FileOutputStream(file));
				out.append("姓名：YorkYong");
				out.append("年龄：20");
				out.append("Support Email：YorkYong@gamil.com");

			} catch (Exception e) {
				Log.e("ERROR", e.getMessage());
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} else {
			Toast.makeText(this, "没有SD卡", Toast.LENGTH_LONG).show();
		}
		// ~-------------------end save file 内容到 SD Card-------------------------------------------------
	}
	
	private void readResourceFile(){
		Resources resources = super.getResources();
		InputStream inputStream = resources.openRawResource(R.raw.daxue);
		Scanner scanner = new Scanner(inputStream);
		while(scanner.hasNext()){
			msg.append(scanner.next());
		}
		scanner.close();
	}
}
