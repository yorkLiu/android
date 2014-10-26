package com.demo.listviewsimpleadapter2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class ListViewDemo extends Activity {
	ListView listView = null;
	TextView selectedMsg = null;
	List<AnimalCommand> animals = new ArrayList<AnimalCommand>();
	List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
	SimpleAdapter adapter = null;
	
	// start level array
	int[] starts = {R.drawable.icon_1_star, R.drawable.icon_2_star, R.drawable.icon_3_star, R.drawable.icon_4_star, R.drawable.icon_5_star};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		// 初始化数据
		initData();
		
		//////
		listView = (ListView) this.findViewById(R.id.listView);
		selectedMsg = (TextView) this.findViewById(R.id.selectedMsg);
		
		for(AnimalCommand animal : animals){
			Map<String, String> map  = new HashMap<String, String>();
			map.put("profileIcon", String.valueOf(animal.getProfileIcon()));
			map.put("title", animal.getTitle());
			map.put("name", animal.getName());
			map.put("age", String.valueOf(animal.getAge()) + (animal.getAge() > 1 ? " years": " year") + " old");
			map.put("score", String.valueOf(this.starts[animal.getScore()-1]));
			map.put("realScore", String.valueOf(animal.getScore()));
			dataList.add(map); 
		}
		
		adapter = new SimpleAdapter(this, this.dataList, 
				R.layout.row_tpl,  // 模板文件 layout--> row_tpl.xml
				new String[]{"profileIcon", "title", "name", "age", "score"},  // map中的key
				new int[]{R.id.profileIcon,R.id.title, R.id.name, R.id.age, R.id.score}); // 模板文件中与map中的key所对应的控件的Id
		
		this.listView.setAdapter(this.adapter);
		
		// add onItems click event for list view
		this.listView.setOnItemClickListener(new OnItemClickListenerImpl());
	}
	
	private void initData(){
		this.animals.add(new AnimalCommand("Hello Bird", "Ketty Bird", 1, R.drawable.icon_bird, 1));
		this.animals.add(new AnimalCommand("This is evernote", "B.S EverNote", 4, R.drawable.icon_evernote, 2));
		this.animals.add(new AnimalCommand("I am fox", "York Fox", 3, R.drawable.icon_fox, 3));
		this.animals.add(new AnimalCommand("So pretty", "Wonderful Frog", 2, R.drawable.icon_frog, 3));
		this.animals.add(new AnimalCommand("Idot Pig", "Purple Pig", 3, R.drawable.icon_pig, 4));
		this.animals.add(new AnimalCommand("A Sheep", "Zit Sheep", 3, R.drawable.icon_sheep, 5));
	}

	
	private class OnItemClickListenerImpl implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			Map<String, String> selectedRow =(Map<String, String> )ListViewDemo.this.listView.getItemAtPosition(position);
			StringBuffer sb = new StringBuffer();
			sb.append("I am ").append(selectedRow.get("name"))
			.append(" and ").append(selectedRow.get("age")).append(" years old ")
			.append("my score is: ").append(selectedRow.get("realScore"));
			
			ListViewDemo.this.selectedMsg.setText(sb.toString());
			
		}
		
	}
}
