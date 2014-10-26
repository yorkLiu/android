package com.demo.sqlitedemo;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListViewSQLiteDemo extends Activity {
	
	/**main.xml 中的 LinearLayout**/
	private LinearLayout myLayout = null;
	private ListView listView = null;
	private SimpleAdapter simpleAdapter = null;
	/**显示 ‘Loading Data...’ 的LinearLayout**/
	private LinearLayout loadInfoLayout = null;
	private TextView loadInfo = null;
	
	/**Search text & button component**/
	private EditText searchText = null;
	private Button searchBtn = null;
	
	SQLiteOpenHelper helper = null;
	
	/**查询结果**/
	List<Map<String, Object>> dataRecs = null;
	
	/**每页显示25面数据**/
	private int pageSize = 25;
	/**当前所在页**/
	private int currentPage = 1;
	/**数据表中总共有多少条满足条件的记录**/
	private int total = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		
		myLayout = (LinearLayout) this.findViewById(R.id.myLayout);
		loadInfoLayout = new LinearLayout(this);
		loadInfo = new TextView(this);
		loadInfo.setText("Loading Data ...");
		loadInfo.setTextSize(20);
		loadInfoLayout.addView(loadInfo, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		loadInfo.setGravity(Gravity.CENTER);
		
		searchText = (EditText) this.findViewById(R.id.searchText);
		searchBtn = (Button) this.findViewById(R.id.searchBtn);
		
		////SQLiteOpenHelper实例
		helper = new MySQLiteHelper(this);
		
		//// load first page data
		loadData(null);
		
		//// add click listeners for search button
		searchBtn.setOnClickListener(new SearchButtonClickListenersImpl());
		
	}
	
	private void loadData(String queryText){
		if(this.listView == null){
			this.listView = new ListView(this);
		}
		MyTableCursor tableCursor = new MyTableCursor(this.helper.getReadableDatabase());
		this.total = tableCursor.getTotalCount(queryText);
		this.dataRecs = tableCursor.find(queryText, this.currentPage, this.pageSize);
		
		this.simpleAdapter = new SimpleAdapter(this, this.dataRecs, R.layout.row_tpl, 
				new String[]{"id", "name", "birthday"}, new int[]{R.id.id, R.id.name, R.id.birthday});
		
		///如果是第一次加载，则对其进行初始化
		if(queryText == null){
			////  在ListView最小面增加一条名为：”Loading Data…” 的提示
			this.listView.addFooterView(this.loadInfoLayout);
			this.listView.setAdapter(this.simpleAdapter);
			this.myLayout.addView(this.listView);
			
			// add listeners for listView
			this.listView.setOnScrollListener(new ScrollListenerImpl());
		} else {
			////如果是能过点击 Search 按钮查询而来，则不需要初始化了
			this.loadInfoLayout.setVisibility(pageSize < total ? View.VISIBLE : View.INVISIBLE);
			this.listView.setAdapter(this.simpleAdapter);
		}
	}
	
	private void appendData(){
		this.dataRecs.addAll(new MyTableCursor(this.helper.getReadableDatabase()).find(this.searchText.getText().toString(), currentPage, pageSize));
		/// 通知simpleAdater 数据已经变了，simpleAdapter会自动的去处理新的数据，将它们加到其中
		this.simpleAdapter.notifyDataSetChanged();
	}
	
	private class ScrollListenerImpl implements OnScrollListener{
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			int lastRecIdx = view.getFirstVisiblePosition() + view.getChildCount() -1;
			
			if((lastRecIdx == simpleAdapter.getCount() || scrollState == SCROLL_STATE_IDLE)
				&& lastRecIdx < total){
				currentPage ++;
				////在指针移动到lastRecIdx位置，之后追加的记录就在lastRecIdx记录之后
				listView.setSelection(lastRecIdx);
				///向ListView 的SimpleAdater中追加数据，前面的数据依然在
				appendData();
			} else {
				loadInfoLayout.setVisibility(View.INVISIBLE);
			}	
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
		}
	}
	
	private class SearchButtonClickListenersImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			loadData(searchText.getText().toString());
		}
	}

}
