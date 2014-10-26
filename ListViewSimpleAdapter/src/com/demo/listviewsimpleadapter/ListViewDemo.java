package com.demo.listviewsimpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListViewDemo extends Activity {

	List<InfoCommand> itemList = new ArrayList<InfoCommand>();
	List<Map<String, String>> adpaterList = new ArrayList<Map<String, String>>();
	ListView listView = null;
	SimpleAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		initData();
		
		listView = (ListView) this.findViewById(R.id.listView);

		for (InfoCommand command : itemList) {
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("_id", (command.getId() < 10 ? ("0" + command.getId())
					: command.getId().toString()));
			map.put("name", command.getName());
			this.adpaterList.add(map);
		}

		this.adapter = new SimpleAdapter(this, 
				this.adpaterList,
				R.layout.data_tpl, // 模板文件即我们定义的layout-->data_tpl.xml
				new String[] { "_id", "name" }, // from map 中的key
				new int[] { R.id._id, R.id.name }); // 模板文件layout-->data_tpl.xml需要map的id
		this.listView.setAdapter(this.adapter);
	}

	private void initData() {
		itemList.add(new InfoCommand(1, "Country: China"));
		itemList.add(new InfoCommand(2, "Province: SC"));
		itemList.add(new InfoCommand(3, "City: CD"));
		itemList.add(new InfoCommand(4, "Name: York"));
		itemList.add(new InfoCommand(5, "Email: York@dev.com"));
		itemList.add(new InfoCommand(6, "Age: 20"));
		itemList.add(new InfoCommand(7, "Home Address: adsl"));
		itemList.add(new InfoCommand(8, "Work Address: cdma"));
		itemList.add(new InfoCommand(9, "Other Address: N/A"));
		itemList.add(new InfoCommand(10, "Home Phone: 234565"));
		itemList.add(new InfoCommand(11, "Work Phone: 9999999"));
	}

}
