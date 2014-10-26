package com.demo.xmlstoragedemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class XmlStoreageDemo extends Activity {
	
	private static final String DIR = "xmls";
	private static final String FILE_NAME = "myxmlData.xml";
	
	private TextView msg = null;
	
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		msg = (TextView)this.findViewById(R.id.msg);
		
		if (Environment.MEDIA_UNMOUNTED.equals(Environment.getExternalStorageState())) {
			Dialog dialog = new AlertDialog.Builder(this)
					.setTitle("SD卡错误")
					.setMessage("请检查该手机的SD卡")
					.setNegativeButton("Yes",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									//finish this app 
									XmlStoreageDemo.this.finish();
								}
							}).create();
			dialog.show();
			
		}
		
		File file = new File(Environment.getExternalStorageDirectory()
				+ File.separator + DIR + File.separator + FILE_NAME);
		
		
		/////// create xml file
		createXmlFile(file);
		
		///// read xml file content
		readXmlFile(file);
	}
	
	private void createXmlFile(File file){
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(new Date());
			// // create xml file
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			
			Element rootEle = document.createElement("Contacts");
			Element contactEle = document.createElement("Contact");
			Element nameEle = document.createElement("name");
			Element phoneNumEle = document.createElement("phoneNum");
			Element contactTimeEle = document.createElement("contactedTime");
			//set text for element
			nameEle.appendChild(document.createTextNode("York"));
			phoneNumEle.appendChild(document.createTextNode("9000000090"));
			contactTimeEle.appendChild(document.createTextNode(today));
			
			contactEle.appendChild(nameEle);
			contactEle.appendChild(phoneNumEle);
			contactEle.appendChild(contactTimeEle);
			rootEle.appendChild(contactEle);
			document.appendChild(rootEle);
			
			
			/// 保存xml 文件到SDCard上
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.setOutputProperty(OutputKeys.ENCODING, "UTF-8") ;	// 指定文件字符编码方式
			DOMSource source = new DOMSource(document);			
			StreamResult result = new StreamResult(file);		
			t.transform(source, result);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readXmlFile(File file){
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			
			NodeList nodes = document.getElementsByTagName("Contacts");
			for(int i = 0,len = nodes.getLength();i<len;i++){
				Element ele = (Element)nodes.item(i);
				this.msg.append("Name: " + ele.getElementsByTagName("name").item(0).getFirstChild().getNodeValue() + "\n");
				this.msg.append("phoneNumber: " + ele.getElementsByTagName("phoneNum").item(0).getFirstChild().getNodeValue() + "\n");
				this.msg.append("contactedTime: " + ele.getElementsByTagName("contactedTime").item(0).getFirstChild().getNodeValue() + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if( inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
