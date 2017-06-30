package www.chinaott.net;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import tv.icntv.epgsdk.epgSDK;
import www.chinaott.net.R;
import www.chinaott.net.bean.LoginInfo;
import www.chinaott.net.gallery.GalleryFlow;
import www.chinaott.net.gallery.ImageAdapter;
import www.chinaott.net.test.MovieLisBean;
import www.chinaott.net.test.MovieListAdapterIm;
import www.chinaott.net.ui.AboutActivity;
import www.chinaott.net.ui.DetailDramaActivity;
import www.chinaott.net.ui.FeedbackActivity;
import www.chinaott.net.ui.HelpActivity;
import www.chinaott.net.ui.HistoryActivity;

public class MainActivity extends Activity implements OnClickListener,OnFocusChangeListener {

	private Button sj_btn,sf, yh, jc, yh_cz, yh_hy, yh_xx, yh_qb, yh_gd,gd_jl,gd_gy,gd_fk,gd_bz;
	private TranslateAnimation mShowAction, mHiddenAction, mShowAction1, mHiddenAction1;
	private LinearLayout ll_sf_test,llsf, llyh, lljc, ll_yh_cz, ll_yh_hy, ll_yh_xx, ll_yh_qb, ll_yh_gd;
	private int i = -1, k = -1;
	
	private ListView listView;
	private LoginInfo loginInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		ImageLoaderConfiguration aDefault = ImageLoaderConfiguration.createDefault(this);
		ImageLoader.getInstance().init(aDefault);
		setContentView(R.layout.activity_main);
		
		Bundle extras = getIntent().getExtras();  
		if (extras != null){  
			loginInfo = (LoginInfo) extras.get("loginInfo_ser"); 
		}
		
		llsf = (LinearLayout) findViewById(R.id.ll_sf);
		ll_sf_test=(LinearLayout)findViewById(R.id.ll_sf_test);
		listView = ((ListView) findViewById(R.id.listViewId));
//		llsf.setVisibility(View.GONE);
//		ll_sf_test.setVisibility(View.VISIBLE);
//		adapter = new MovieListAdapterIm(new ArrayList<MovieLisBean.ProgramSeriesBean>(), this);
//		listView.setAdapter(adapter);
		
		initEpg();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				// TODO Auto-generated method stub
				
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						StringBuffer sb = new StringBuffer();
						int ret = epgSDK.getInstance().getMovieDetail(""+adapter.getItem(position).getId(), "", "217466", "", "", sb);
						Message msg = Message.obtain();
//						msg.what = GET_MOVIE_DETAIL;
						msg.obj = sb;
						Log.i("info", "--------getMovieDetail-" + sb);
//						mHandler.sendMessage(msg);
						Intent intent=new Intent(MainActivity.this,DetailDramaActivity.class);
						intent.putExtra("data", sb.toString());
						Bundle extras = new Bundle();  
						extras.putSerializable("loginInfo_ser", loginInfo);  
						  
						intent.putExtras(extras); 
						startActivity(intent);
					}
				});
				thread.start();
			}
		});
		
		initView();
		gallary();
	}

	

	private void initView() {
		// TODO Auto-generated method stub
		
		sj_btn = (Button) findViewById(R.id.sj_btn);
		sj_btn.setOnClickListener(this);
		
		sf = (Button) findViewById(R.id.sf);
		yh = (Button) findViewById(R.id.yh);
		jc = (Button) findViewById(R.id.jc);
		sf.setOnClickListener(this);
		yh.setOnClickListener(this);
		jc.setOnClickListener(this);

		llsf = (LinearLayout) findViewById(R.id.ll_sf);
		llyh = (LinearLayout) findViewById(R.id.ll_yh);
		lljc = (LinearLayout) findViewById(R.id.ll_jc);

		mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		mShowAction.setDuration(500);

		mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		mHiddenAction.setDuration(500);

		mShowAction1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		mShowAction1.setDuration(500);

		mHiddenAction1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		mHiddenAction1.setDuration(500);
		i = 1;
		k=1;
		
		yh_cz = (Button) findViewById(R.id.yh_cz);
		yh_cz.setOnClickListener(this);
		yh_hy = (Button) findViewById(R.id.yh_hy);
		yh_hy.setOnClickListener(this);
		yh_xx = (Button) findViewById(R.id.yh_xx);
		yh_xx.setOnClickListener(this);
		yh_qb = (Button) findViewById(R.id.yh_qb);
		yh_qb.setOnClickListener(this);
		yh_gd = (Button) findViewById(R.id.yh_gd);
		yh_gd.setOnClickListener(this);

		ll_yh_cz = (LinearLayout) findViewById(R.id.ll_yh_cz);
		ll_yh_hy = (LinearLayout) findViewById(R.id.ll_yh_hy);
		ll_yh_xx = (LinearLayout) findViewById(R.id.ll_yh_xx);
		ll_yh_qb = (LinearLayout) findViewById(R.id.ll_yh_qb);
		ll_yh_gd = (LinearLayout) findViewById(R.id.ll_yh_gd);
		
		//gd_jl,gd_gy,gd_fk,gd_bz
		gd_jl = (Button) findViewById(R.id.gd_jl);
		gd_jl.setOnClickListener(this);
		gd_gy = (Button) findViewById(R.id.gd_gy);
		gd_gy.setOnClickListener(this);
		gd_fk = (Button) findViewById(R.id.gd_fk);
		gd_fk.setOnClickListener(this);
		gd_bz = (Button) findViewById(R.id.gd_bz);
		gd_bz.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sf:

			// llsf.startAnimation(mHiddenAction);
			// llsf.setVisibility(View.GONE);
			//
			// llyh.startAnimation(mShowAction);
			// llyh.setVisibility(View.VISIBLE);
			if (i != -1 && i != 1) {
				hiddll1(i);
				showll1(1);
			}

			i = 1;
			break;

		case R.id.yh:

			if (i != 2) {
				if (i == 3) {
					hiddll1(i);
					showll1(2);
				} else if (i == 1) {
					hiddll(i);
					showll(2);
				}
			}
			i = 2;
			k = 4;
			break;
		case R.id.yh_cz:
			if (k != 4) {
				hiddll1(k);
				showll1(4);
			}
			k = 4;
			break;

		case R.id.yh_hy:
			if (k > 5) {
				hiddll1(k);
				showll1(5);
			} else if (k < 5) {
				hiddll(k);
				showll(5);
			}
			k = 5;
			break;

		case R.id.yh_xx:
			if (k > 6) {
				hiddll1(k);
				showll1(6);
			} else if (k < 6) {
				hiddll(k);
				showll(6);
			}
			k = 6;
			break;

		case R.id.yh_qb:
			if (k > 7) {
				hiddll1(k);
				showll1(7);
			} else if (k < 7) {
				hiddll(k);
				showll(7);
			}
			k = 7;
			break;
			//gd_jl,gd_gy,gd_fk,gd_bz
		case R.id.gd_jl:
			startActivity(new Intent(MainActivity.this,HistoryActivity.class));
			break;
			
		case R.id.gd_gy:
			startActivity(new Intent(MainActivity.this,AboutActivity.class));
			break;
			
		case R.id.gd_fk:
			startActivity(new Intent(MainActivity.this,FeedbackActivity.class));
			break;
			
		case R.id.gd_bz:
			startActivity(new Intent(MainActivity.this,HelpActivity.class));
			break;
			
		case R.id.yh_gd:
			if (k != 8) {
				hiddll(k);
				showll(8);
			}
			k = 8;
			break;

		case R.id.jc:
			if (i != 3) {
				hiddll(i);
				showll(3);
			}
			i = 3;
			
//			llsf.setVisibility(View.GONE);
//			ll_sf_test.setVisibility(View.VISIBLE);
//			showTestData();
			break;
			
		case R.id.sj_btn:
			if (k > 5) {
				hiddll1(k);
				showll1(2);
				showll1(5);
			} else if (k < 5) {
				if(k==1){
					hiddll(i);
					showll(2);
					showll(5);
				}else {
					hiddll(k);
					showll(2);
					showll(5);
				}
				
			}
			k = 5;
			break;

		default:
			break;
		}
	}

	private void hiddll(int j) {
		// TODO Auto-generated method stub
		switch (j) {

		case 1:
			llsf.startAnimation(mHiddenAction);
			llsf.setVisibility(View.GONE);
			break;
		case 2:
			llyh.startAnimation(mHiddenAction);
			llyh.setVisibility(View.GONE);
			break;
		case 3:
			lljc.startAnimation(mHiddenAction);
			lljc.setVisibility(View.GONE);
			break;
			// ll_yh_cz, ll_yh_hy, ll_yh_xx, ll_yh_qb, ll_yh_gd
		case 4:
			ll_yh_cz.startAnimation(mHiddenAction);
			ll_yh_cz.setVisibility(View.GONE);
			break;
		case 5:
			ll_yh_hy.startAnimation(mHiddenAction);
			ll_yh_hy.setVisibility(View.GONE);
			break;
		case 6:
			ll_yh_xx.startAnimation(mHiddenAction);
			ll_yh_xx.setVisibility(View.GONE);
			break;
		case 7:
			ll_yh_qb.startAnimation(mHiddenAction);
			ll_yh_qb.setVisibility(View.GONE);
			break;
		case 8:
			ll_yh_gd.startAnimation(mHiddenAction);
			ll_yh_gd.setVisibility(View.GONE);
			break;

		default:
			break;
		}
	}

	private void showll(int j) {
		// TODO Auto-generated method stub
		switch (j) {

		case 1:
			llsf.startAnimation(mShowAction);
			llsf.setVisibility(View.VISIBLE);
			break;
		case 2:
			llyh.startAnimation(mShowAction);
			llyh.setVisibility(View.VISIBLE);
			break;
		case 3:
			lljc.startAnimation(mShowAction);
			lljc.setVisibility(View.VISIBLE);
			break;

		// ll_yh_cz, ll_yh_hy, ll_yh_xx, ll_yh_qb, ll_yh_gd
		case 4:
			ll_yh_cz.startAnimation(mShowAction);
			ll_yh_cz.setVisibility(View.VISIBLE);
			break;
		case 5:
			ll_yh_hy.startAnimation(mShowAction);
			ll_yh_hy.setVisibility(View.VISIBLE);
			break;
		case 6:
			ll_yh_xx.startAnimation(mShowAction);
			ll_yh_xx.setVisibility(View.VISIBLE);
			break;
		case 7:
			ll_yh_qb.startAnimation(mShowAction);
			ll_yh_qb.setVisibility(View.VISIBLE);
			break;
		case 8:
			ll_yh_gd.startAnimation(mShowAction);
			ll_yh_gd.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	private void hiddll1(int j) {
		// TODO Auto-generated method stub
		switch (j) {

		case 1:
			llsf.startAnimation(mHiddenAction1);
			llsf.setVisibility(View.GONE);
			break;
		case 2:
			llyh.startAnimation(mHiddenAction1);
			llyh.setVisibility(View.GONE);
			break;
		case 3:
			lljc.startAnimation(mHiddenAction1);
			lljc.setVisibility(View.GONE);
			break;

		// ll_yh_cz, ll_yh_hy, ll_yh_xx, ll_yh_qb, ll_yh_gd
		case 4:
			ll_yh_cz.startAnimation(mHiddenAction1);
			ll_yh_cz.setVisibility(View.GONE);
			break;
		case 5:
			ll_yh_hy.startAnimation(mHiddenAction1);
			ll_yh_hy.setVisibility(View.GONE);
			break;
		case 6:
			ll_yh_xx.startAnimation(mHiddenAction1);
			ll_yh_xx.setVisibility(View.GONE);
			break;
		case 7:
			ll_yh_qb.startAnimation(mHiddenAction1);
			ll_yh_qb.setVisibility(View.GONE);
			break;
		case 8:
			ll_yh_gd.startAnimation(mHiddenAction1);
			ll_yh_gd.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

	private void showll1(int j) {
		// TODO Auto-generated method stub
		switch (j) {

		case 1:
			llsf.startAnimation(mShowAction1);
			llsf.setVisibility(View.VISIBLE);
			break;
		case 2:
			llyh.startAnimation(mShowAction1);
			llyh.setVisibility(View.VISIBLE);
			break;
		case 3:
			lljc.startAnimation(mShowAction1);
			lljc.setVisibility(View.VISIBLE);
			break;

		// ll_yh_cz, ll_yh_hy, ll_yh_xx, ll_yh_qb, ll_yh_gd
		case 4:
			ll_yh_cz.startAnimation(mShowAction1);
			ll_yh_cz.setVisibility(View.VISIBLE);
			break;
		case 5:
			ll_yh_hy.startAnimation(mShowAction1);
			ll_yh_hy.setVisibility(View.VISIBLE);
			break;
		case 6:
			ll_yh_xx.startAnimation(mShowAction1);
			ll_yh_xx.setVisibility(View.VISIBLE);
			break;
		case 7:
			ll_yh_qb.startAnimation(mShowAction1);
			ll_yh_qb.setVisibility(View.VISIBLE);
			break;
		case 8:
			ll_yh_gd.startAnimation(mShowAction1);
			ll_yh_gd.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if(hasFocus){
			switch (v.getId()) {
			case R.id.sj_btn:
//				sj_btn.setBackgroundResource(R.color.sj_btn);
				break;

			default:
				break;
			}
		}
	}

	private void gallary() {
		Integer[] images = { R.drawable.img001, R.drawable.img002,
				R.drawable.img003, R.drawable.img004, R.drawable.img005,
				R.drawable.img006, R.drawable.img007, R.drawable.img008,
				R.drawable.img009, R.drawable.img010, R.drawable.img011,
				R.drawable.img012, };
		
		ImageAdapter adapter = new ImageAdapter(this, images);
		adapter.createReflectedImages();// 创建倒影效果
		GalleryFlow galleryFlow = (GalleryFlow) this
				.findViewById(R.id.Gallery01);
		galleryFlow.setFadingEdgeLength(0);
		galleryFlow.setSpacing(-10); // 图片之间的间距
		galleryFlow.setAdapter(adapter);

		galleryFlow.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						String.valueOf(position), Toast.LENGTH_SHORT).show();
				startActivity(new Intent(MainActivity.this,DetailDramaActivity.class));
			}

		});
		galleryFlow.setSelection(4);
	}
	
	
	private void showTestData() {
		// TODO Auto-generated method stub
		adapter = new MovieListAdapterIm(new ArrayList<MovieLisBean.ProgramSeriesBean>(), this);
		listView.setAdapter(adapter);
		initEpg();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				// TODO Auto-generated method stub
				
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						StringBuffer sb = new StringBuffer();
						int ret = epgSDK.getInstance().getMovieDetail(""+adapter.getItem(position).getId(), "", "217466", "", "", sb);
						Message msg = Message.obtain();
//						msg.what = GET_MOVIE_DETAIL;
						msg.obj = sb;
						Log.i("info", "--------getMovieDetail-" + sb);
//						mHandler.sendMessage(msg);
					}
				});
				thread.start();
			}
		});
	}
	
	private MovieListAdapterIm adapter;
	
	
	private void initEpg() {
		// TODO Auto-generated method stub
		String path = "";
		String epgServer = "http://epg.is.ysten.com";
		String searchServer = "http://search.is.ysten.com:8080/yst-search/";
		String templateID = "1400004";
		String STBext = "%7B%22abilities%22%3A%5B%22cp-TENCENT%22%5D%7D";
		boolean ret = epgSDK.getInstance().sdkInit(path, epgServer, searchServer, templateID, STBext);

		if (ret) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					StringBuffer sb = new StringBuffer();
					int ret = epgSDK.getInstance().getMovieList("217466", 1, 10, sb);
					Gson gson = new Gson();
					qsTextEntity = gson.fromJson(sb.toString(), MovieLisBean.class);
					// adapter.addAll(qsTextEntity.getProgramSeries());
					Log.i("info", "------getMovieList-" + sb.toString());
					handler.sendEmptyMessage(1);
				}
			});
			thread.start();
		}
	}

	MovieLisBean qsTextEntity;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
//			adapter.addAll(qsTextEntity.getProgramSeries());
		};
	};
}
