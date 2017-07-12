package www.chinaott.net.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
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
				// mImg.setImageResource(mDatas.get(position));
//				 Toast.makeText(MainActivity.this, "--"+position, 1).show();
			};
		});

		mAdapter.setOnItemClickLitener(new OnItemClickLitener() {
			@Override
			public void onItemClick(View view, int position) {
				// Toast.makeText(getApplicationContext(), position + "",
				// Toast.LENGTH_SHORT)
				// .show();
				Toast.makeText(HistoryActivity.this, "++" + position, 1).show();
			}
		});

	}

}
