package www.chinaott.net.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import www.chinaott.net.R;
import www.chinaott.net.adapter.GalleryAdapter;
import www.chinaott.net.adapter.GalleryAdapter.OnItemClickLitener;
import www.chinaott.net.custom.ui.MyRecyclerView;
import www.chinaott.net.custom.ui.MyRecyclerView.OnItemScrollChangeListener;


public class HistoryActivity extends Activity {

	private MyRecyclerView mRecyclerView;
	private GalleryAdapter mAdapter;
	private List<Integer> mDatas;
	private TextView history_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_history);

		mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.img001, R.drawable.img002, R.drawable.img003,
				R.drawable.img004, R.drawable.img005, R.drawable.img006, R.drawable.img007, R.drawable.img008));

		mRecyclerView = (MyRecyclerView) findViewById(R.id.id_recyclerview_horizontal);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

		mRecyclerView.setLayoutManager(linearLayoutManager);
		mAdapter = new GalleryAdapter(this, mDatas);
		mRecyclerView.setAdapter(mAdapter);
		
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());

		mRecyclerView.setOnItemScrollChangeListener(new OnItemScrollChangeListener() {
			@Override
			public void onChange(View view, int position) {
			};
		});

		mAdapter.setOnItemClickLitener(new OnItemClickLitener() {
			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(HistoryActivity.this, "++" + position, 1).show();
			}
		});
		
		history_time=(TextView)findViewById(R.id.history_time);
		new Thread() {
			public void run() {
				try {
					while (true) {
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
						String str = sdf.format(new Date());
						handler.sendMessage(handler.obtainMessage(1000, str));
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			history_time.setText((String)msg.obj);
		};
	};
}
