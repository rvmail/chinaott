package www.chinaott.net;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

public class MainActivity extends Activity implements OnClickListener, OnFocusChangeListener {

	private Button sj_btn, sf, yh, jc, yh_cz, yh_hy, yh_xx, yh_qb, yh_gd;
	private TranslateAnimation mShowAction, mHiddenAction, mShowAction1, mHiddenAction1;
	private LinearLayout ll_sf_test, llsf, llyh, lljc, ll_yh_cz, ll_yh_qb, ll_yh_gd;
	private int i = -1, k = -1;
	private TextView main_time;

	private ListView listView;
	private LoginInfo loginInfo;
	private RelativeLayout ll_yh_hy, ll_yh_xx;

	private RelativeLayout gd_rl_jl, gd_rl_gy, gd_rl_fk, gd_rl_bz;

	private TextView cz_tv_ts_r;
	private ImageView first_iv_smallicon, second_iv_smallicon, third_iv_smallicon, fourth_iv_smallicon,
			fif_iv_smallicon, six_iv_smallicon;
	private RelativeLayout rl_first, rl_second, rl_third, rl_fourth, rl_fif, rl_six;
	private TextView first_tv, second_tv, third_tv, fourth_tv, fif_tv, six_tv;
	private Button cz_tv_qh;
	private boolean czIsWeiXinPay = true;
	private int czMoney = -1;
	private ProgressBar cz_probar;
	private ImageView cz_erwm_Img;

	// 会员
	private RelativeLayout rl_onem, rl_threem, rl_oney, rl_threey, rl_sixm, rl_fivey;
	private ImageView onem_iv_smallicon, threem_iv_smallicon, oney_iv_smallicon, threey_iv_smallicon, sixm_iv_smallicon,
			fivey_iv_smallicon;
	private Button hy_tv_qh;
	private TextView hy_tv_ts_r;
	private ImageView hy_default_img, hy_2weim;
	private boolean HyIsFirst = true;
	private int hyTimes = -1;
	private boolean hyIsWeiXinPay = true;
	private ProgressBar hy_probar;

	// 信息
	private EditText xx_edit_phone, xx_edit_mail;
	private ImageView xx_edit_mail_img, xx_edit_phone_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		ImageLoaderConfiguration aDefault = ImageLoaderConfiguration.createDefault(this);
		ImageLoader.getInstance().init(aDefault);
		setContentView(R.layout.activity_main);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			loginInfo = (LoginInfo) extras.get("loginInfo_ser");
		}

		llsf = (LinearLayout) findViewById(R.id.ll_sf);
		ll_sf_test = (LinearLayout) findViewById(R.id.ll_sf_test);
		listView = ((ListView) findViewById(R.id.listViewId));
		// llsf.setVisibility(View.GONE);
		// ll_sf_test.setVisibility(View.VISIBLE);
		// adapter = new MovieListAdapterIm(new
		// ArrayList<MovieLisBean.ProgramSeriesBean>(), this);
		// listView.setAdapter(adapter);
		initEpg();
		// getData();

		initView();
		gallary();
	}

	private void initView() {
		// TODO Auto-generated method stub

		initMainView();
		initCzView();
		initHyView();
		initXxView();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (k == 4) {
			czOnClick(v);
		}

		if (k == 5) {
			hyOnClick(v);
		}

		if (k == 8) {
			gdOnClick(v);
		}

		MainOnClick(v);
	}

	private void MainOnClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sf:

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
			} else {
				if (k != 4) {
					hiddll(k);
					showll(4);
				}
			}
			i = 2;
			if(k==1){
				k = 4;
			}
			
			yh_cz.requestFocus();
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
		// gd_rl_jl,gd_rl_gy,gd_rl_fk,gd_rl_bz

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

			// llsf.setVisibility(View.GONE);
			// ll_sf_test.setVisibility(View.VISIBLE);
			// showTestData();
			break;

		case R.id.sj_btn:
			if (k > 5) {
				hiddll1(k);
				showll1(2);
				showll1(5);
			} else if (k < 5) {
				if (k == 1) {
					hiddll(i);
					showll(2);
					showll(5);
				} else {
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

	private void initXxView() {
		// TODO Auto-generated method stub
		xx_edit_mail_img = (ImageView) findViewById(R.id.xx_edit_mail_img);
		xx_edit_phone_img = (ImageView) findViewById(R.id.xx_edit_phone_img);
		xx_edit_phone = (EditText) findViewById(R.id.xx_edit_phone);
		xx_edit_mail = (EditText) findViewById(R.id.xx_edit_mail);
		xx_edit_phone.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() == 0) {
					xx_edit_phone_img.setVisibility(View.INVISIBLE);
				} else {
					xx_edit_phone_img.setVisibility(View.VISIBLE);
					Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
					Matcher m = p.matcher(xx_edit_phone.getText().toString());
					if (m.matches()) {
						xx_edit_phone_img.setImageResource(R.drawable.fs_good);
					} else {
						xx_edit_phone_img.setImageResource(R.drawable.setting_rated_input_error_icon);
					}
				}
			}
		});
		xx_edit_mail.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s.length() != 0) {
					xx_edit_mail_img.setVisibility(View.VISIBLE);
					String strEmail = xx_edit_mail.getText().toString();
					String strPattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
					Pattern p = Pattern.compile(strPattern);
					Matcher m = p.matcher(strEmail);
					if (m.matches()) {
						xx_edit_mail_img.setImageResource(R.drawable.fs_good);
					} else {
						xx_edit_mail_img.setImageResource(R.drawable.setting_rated_input_error_icon);
					}
				} else {
					xx_edit_mail_img.setVisibility(View.INVISIBLE);
				}
			}
		});
	}

	private void initHyView() {
		// TODO Auto-generated method stub
		// private RelativeLayout
		// rl_onem,rl_threem,rl_oney,rl_threey,rl_sixm,rl_fivey;
		// private ImageView
		// onem_iv_smallicon,threem_iv_smallicon,oney_iv_smallicon,threey_iv_smallicon,sixm_iv_smallicon,fivey_iv_smallicon;
		rl_onem = (RelativeLayout) findViewById(R.id.rl_onem);
		rl_onem.setOnClickListener(this);
		rl_threem = (RelativeLayout) findViewById(R.id.rl_threem);
		rl_threem.setOnClickListener(this);
		rl_oney = (RelativeLayout) findViewById(R.id.rl_oney);
		rl_oney.setOnClickListener(this);
		rl_threey = (RelativeLayout) findViewById(R.id.rl_threey);
		rl_threey.setOnClickListener(this);
		rl_sixm = (RelativeLayout) findViewById(R.id.rl_sixm);
		rl_sixm.setOnClickListener(this);
		rl_fivey = (RelativeLayout) findViewById(R.id.rl_fivey);
		rl_fivey.setOnClickListener(this);

		onem_iv_smallicon = (ImageView) findViewById(R.id.onem_iv_smallicon);
		threem_iv_smallicon = (ImageView) findViewById(R.id.threem_iv_smallicon);
		oney_iv_smallicon = (ImageView) findViewById(R.id.oney_iv_smallicon);
		threey_iv_smallicon = (ImageView) findViewById(R.id.threey_iv_smallicon);
		sixm_iv_smallicon = (ImageView) findViewById(R.id.sixm_iv_smallicon);
		fivey_iv_smallicon = (ImageView) findViewById(R.id.fivey_iv_smallicon);

		hy_tv_ts_r = (TextView) findViewById(R.id.hy_tv_ts_r);
		hy_default_img = (ImageView) findViewById(R.id.hy_default_img);
		hy_2weim = (ImageView) findViewById(R.id.hy_2weim);
		hy_tv_qh = (Button) findViewById(R.id.hy_tv_qh);
		hy_tv_qh.setOnClickListener(this);

		hy_probar = (ProgressBar) findViewById(R.id.hy_probar);
	}

	private void initCzView() {
		// TODO Auto-generated method stub
		cz_tv_ts_r = (TextView) findViewById(R.id.cz_tv_ts_r);
		cz_tv_ts_r.setText(Html.fromHtml("扫<font color='#7FD743'>微信</font>二维码支付<font color='#F32323'>￥20.00</font>"));

		// first_iv_smallicon,second_iv_smallicon,third_iv_smallicon,fourth_iv_smallicon,fif_iv_smallicon,six_iv_smallicon;
		// rl_first,rl_second,rl_fourth,rl_fourth,rl_fif,rl_six;
		first_iv_smallicon = (ImageView) findViewById(R.id.first_iv_smallicon);
		second_iv_smallicon = (ImageView) findViewById(R.id.second_iv_smallicon);
		third_iv_smallicon = (ImageView) findViewById(R.id.third_iv_smallicon);
		fourth_iv_smallicon = (ImageView) findViewById(R.id.fourth_iv_smallicon);
		fif_iv_smallicon = (ImageView) findViewById(R.id.fif_iv_smallicon);
		six_iv_smallicon = (ImageView) findViewById(R.id.six_iv_smallicon);

		rl_first = (RelativeLayout) findViewById(R.id.rl_first);
		rl_second = (RelativeLayout) findViewById(R.id.rl_second);
		rl_first.setOnClickListener(this);
		rl_second.setOnClickListener(this);
		rl_third = (RelativeLayout) findViewById(R.id.rl_third);
		rl_third.setOnClickListener(this);
		rl_fourth = (RelativeLayout) findViewById(R.id.rl_fourth);
		rl_fourth.setOnClickListener(this);
		rl_fif = (RelativeLayout) findViewById(R.id.rl_fif);
		rl_fif.setOnClickListener(this);
		rl_six = (RelativeLayout) findViewById(R.id.rl_six);
		rl_six.setOnClickListener(this);

		// first_tv,second_tv,third_tv,fourth_tv,fif_tv,six_tv
		first_tv = (TextView) findViewById(R.id.first_tv);
		second_tv = (TextView) findViewById(R.id.second_tv);
		third_tv = (TextView) findViewById(R.id.third_tv);
		fourth_tv = (TextView) findViewById(R.id.fourth_tv);
		fif_tv = (TextView) findViewById(R.id.fif_tv);
		six_tv = (TextView) findViewById(R.id.six_tv);

		cz_tv_qh = (Button) findViewById(R.id.cz_tv_qh);
		cz_tv_qh.setOnClickListener(this);

		showMoneyImg(3);
		third_tv.setTextColor(Color.WHITE);
		// czSetTextColor(czMoney);
		czSetMoneyChange(3);
		third_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
		czMoney = 3;
		cz_probar = (ProgressBar) findViewById(R.id.cz_probar);
		cz_erwm_Img = (ImageView) findViewById(R.id.cz_erwm_Img);
	}

	private void initMainView() {
		// TODO Auto-generated method stub
		sj_btn = (Button) findViewById(R.id.sj_btn);
		sj_btn.setOnClickListener(this);
		main_time = (TextView) findViewById(R.id.main_time);

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
		k = 1;

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
		ll_yh_hy = (RelativeLayout) findViewById(R.id.ll_yh_hy);
		ll_yh_xx = (RelativeLayout) findViewById(R.id.ll_yh_xx);
		ll_yh_qb = (LinearLayout) findViewById(R.id.ll_yh_qb);
		ll_yh_gd = (LinearLayout) findViewById(R.id.ll_yh_gd);

		// gd_rl_jl,gd_rl_gy,gd_rl_fk,gd_rl_bz
		gd_rl_jl = (RelativeLayout) findViewById(R.id.gd_rl_jl);
		gd_rl_jl.setOnClickListener(this);
		gd_rl_gy = (RelativeLayout) findViewById(R.id.gd_rl_gy);
		gd_rl_gy.setOnClickListener(this);
		gd_rl_fk = (RelativeLayout) findViewById(R.id.gd_rl_fk);
		gd_rl_fk.setOnClickListener(this);
		gd_rl_bz = (RelativeLayout) findViewById(R.id.gd_rl_bz);
		gd_rl_bz.setOnClickListener(this);
		setTime();
//		handler.postDelayed(runnable, 1000);
	}

	private void setTime() {
		// TODO Auto-generated method stub
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

	private void hyOnClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		// rl_onem,rl_threem,rl_oney,rl_threey,rl_sixm,rl_fivey;
		case R.id.rl_onem:
			HyIsFirst=false;
			if (!HyIsFirst){
				hy_default_img.setVisibility(View.GONE);
				hy_2weim.setVisibility(View.VISIBLE);
				hy_tv_qh.setVisibility(View.VISIBLE);
				hy_tv_ts_r.setVisibility(View.VISIBLE);
			}
			hiddMoneyImg_Hy(hyTimes);
			showMoneyImg_Hy(1);
			hySetMoneyChange(1);
			hyTimes = 1;
			break;
		case R.id.rl_threem:
			if (!HyIsFirst){
				hy_default_img.setVisibility(View.GONE);
				hy_2weim.setVisibility(View.VISIBLE);
				hy_tv_qh.setVisibility(View.VISIBLE);
				hy_tv_ts_r.setVisibility(View.VISIBLE);
			}
			HyIsFirst=false;
			hiddMoneyImg_Hy(hyTimes);
			showMoneyImg_Hy(2);
			hySetMoneyChange(2);
			hyTimes = 2;
			break;
		case R.id.rl_oney:
			if (!HyIsFirst){
				hy_default_img.setVisibility(View.GONE);
				hy_2weim.setVisibility(View.VISIBLE);
				hy_tv_qh.setVisibility(View.VISIBLE);
				hy_tv_ts_r.setVisibility(View.VISIBLE);
			}
			HyIsFirst=false;
			hiddMoneyImg_Hy(hyTimes);
			showMoneyImg_Hy(3);
			hySetMoneyChange(3);
			hyTimes = 3;
			break;
		case R.id.rl_threey:
			if (!HyIsFirst){
				hy_default_img.setVisibility(View.GONE);
				hy_2weim.setVisibility(View.VISIBLE);
				hy_tv_qh.setVisibility(View.VISIBLE);
				hy_tv_ts_r.setVisibility(View.VISIBLE);
			}
			HyIsFirst=false;
			hiddMoneyImg_Hy(hyTimes);
			showMoneyImg_Hy(4);
			hySetMoneyChange(4);
			hyTimes = 4;
			break;
		case R.id.rl_sixm:
			if (!HyIsFirst){
				hy_default_img.setVisibility(View.GONE);
				hy_2weim.setVisibility(View.VISIBLE);
				hy_tv_qh.setVisibility(View.VISIBLE);
				hy_tv_ts_r.setVisibility(View.VISIBLE);
			}
			HyIsFirst=false;
			hiddMoneyImg_Hy(hyTimes);
			showMoneyImg_Hy(5);
			hySetMoneyChange(5);
			hyTimes = 5;
			break;
		case R.id.rl_fivey:
			if (!HyIsFirst){
				hy_default_img.setVisibility(View.GONE);
				hy_2weim.setVisibility(View.VISIBLE);
				hy_tv_qh.setVisibility(View.VISIBLE);
				hy_tv_ts_r.setVisibility(View.VISIBLE);
			}
			HyIsFirst=false;
			hiddMoneyImg_Hy(hyTimes);
			showMoneyImg_Hy(6);
			hySetMoneyChange(6);
			hyTimes = 6;
			break;
		case R.id.hy_tv_qh:
			if (hyIsWeiXinPay) {
				hyIsWeiXinPay = false;
				hy_tv_qh.setText("切换微信");
				hySetWeiXinPay(hyTimes);

				hy_2weim.setVisibility(View.GONE);
				hy_probar.setVisibility(View.VISIBLE);

				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						/**
						 * 要执行的操作
						 */

						hy_2weim.setVisibility(View.VISIBLE);
						hy_2weim.setImageResource(R.drawable.zhifubao);
						hy_probar.setVisibility(View.GONE);
					}
				}, 1000);

			} else {
				hyIsWeiXinPay = true;
				hy_tv_qh.setText("切换支付宝");
				hySetWeiXinPay(czMoney);

				hy_2weim.setVisibility(View.GONE);
				hy_probar.setVisibility(View.VISIBLE);

				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						/**
						 * 要执行的操作
						 */

						hy_2weim.setVisibility(View.VISIBLE);
						hy_2weim.setImageResource(R.drawable.erweima);
						hy_probar.setVisibility(View.GONE);
					}

				}, 1000);
			}
			break;

		default:
			break;
		}
	}

	private void czOnClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_first:
			if (czMoney != 1) {

				hiddMoneyImg(czMoney);
				showMoneyImg(1);
				first_tv.setTextColor(Color.WHITE);
				czSetTextColor(czMoney);
				czSetMoneyChange(1);
				czMoney = 1;
			}

			// second_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			// first_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);

			break;
		case R.id.rl_second:
			if (czMoney != 2) {

				hiddMoneyImg(czMoney);
				showMoneyImg(2);
				second_tv.setTextColor(Color.WHITE);
				czSetTextColor(czMoney);
				czSetMoneyChange(2);
				czMoney = 2;
			}

			break;

		case R.id.rl_third:
			if (czMoney != 3) {

				hiddMoneyImg(czMoney);
				showMoneyImg(3);
				third_tv.setTextColor(Color.WHITE);
				czSetTextColor(czMoney);
				czSetMoneyChange(3);
				czMoney = 3;
			}

			break;
		case R.id.rl_fourth:
			if (czMoney != 4) {

				hiddMoneyImg(czMoney);
				showMoneyImg(4);
				fourth_tv.setTextColor(Color.WHITE);
				czSetTextColor(czMoney);
				czSetMoneyChange(4);
				czMoney = 4;
			}
			break;
		case R.id.rl_fif:
			if (czMoney != 5) {

				hiddMoneyImg(czMoney);
				showMoneyImg(5);
				fif_tv.setTextColor(Color.WHITE);
				czSetTextColor(czMoney);
				czSetMoneyChange(5);
				czMoney = 5;
			}
			break;
		case R.id.rl_six:
			if (czMoney != 6) {

				hiddMoneyImg(czMoney);
				showMoneyImg(6);
				six_tv.setTextColor(Color.WHITE);
				czSetTextColor(czMoney);
				czSetMoneyChange(6);
				czMoney = 6;
			}
			break;

		case R.id.cz_tv_qh:
			if (czMoney != -1) {
				if (czIsWeiXinPay) {
					czIsWeiXinPay = false;
					cz_tv_qh.setText("切换微信");
					czSetWeiXinPay(czMoney);
					cz_erwm_Img.setVisibility(View.GONE);
					cz_probar.setVisibility(View.VISIBLE);

					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							/**
							 * 要执行的操作
							 */

							cz_erwm_Img.setVisibility(View.VISIBLE);
							cz_erwm_Img.setImageResource(R.drawable.zhifubao);
							cz_probar.setVisibility(View.GONE);
						}
					}, 1000);

				} else {
					czIsWeiXinPay = true;
					cz_tv_qh.setText("切换支付宝");
					czSetMoneyChange(czMoney);

					cz_erwm_Img.setVisibility(View.GONE);
					cz_probar.setVisibility(View.VISIBLE);

					handler.postDelayed(new Runnable() {

						@Override
						public void run() {
							/**
							 * 要执行的操作
							 */

							cz_erwm_Img.setVisibility(View.VISIBLE);
							cz_erwm_Img.setImageResource(R.drawable.erweima);
							cz_probar.setVisibility(View.GONE);
						}

					}, 1000);
				}
			}

			break;
		default:
			break;
		}
	}

	private void showMoneyImg_Hy(int czMoney2) {
		// TODO Auto-generated method stub
		switch (czMoney2) {
		// onem_iv_smallicon,threem_iv_smallicon,oney_iv_smallicon,threey_iv_smallicon,sixm_iv_smallicon,fivey_iv_smallicon;
		case 1:
			onem_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;
		case 2:
			threem_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;

		case 3:
			oney_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;

		case 4:
			threey_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;

		case 5:
			sixm_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;

		case 6:
			fivey_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;

		default:
			break;
		}
	}

	private void hiddMoneyImg_Hy(int czMoney2) {
		// TODO Auto-generated method stub
		switch (czMoney2) {
		// onem_iv_smallicon,threem_iv_smallicon,oney_iv_smallicon,threey_iv_smallicon,sixm_iv_smallicon,fivey_iv_smallicon;
		case 1:
			onem_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			break;
		case 2:
			threem_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			break;
		case 3:
			oney_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			break;
		case 4:
			threey_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			break;
		case 5:
			sixm_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			break;
		case 6:
			fivey_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			break;

		default:
			break;
		}
	}

	private void czSetWeiXinPay(int czMoney2) {
		// TODO Auto-generated method stub
		switch (czMoney2) {
		case 1:
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#0FDBF6'>支付宝</font>二维码付<font color='#F32323'>￥200.00</font>"));

			break;

		case 2:
			cz_tv_ts_r
					.setText(Html.fromHtml("扫<font color='#0FDBF6'>支付宝</font>二维码付<font color='#F32323'>￥50.00</font>"));

			break;

		case 3:
			cz_tv_ts_r.setText(
					Html.fromHtml("扫<font color='#0FDBF6'>支付宝</font>二维码支付<font color='#F32323'>￥20.00</font>"));

			break;

		case 4:
			cz_tv_ts_r.setText(
					Html.fromHtml("扫<font color='#0FDBF6'>支付宝</font>二维码支付<font color='#F32323'>￥10.00</font>"));

			break;

		case 5:
			cz_tv_ts_r
					.setText(Html.fromHtml("扫<font color='#0FDBF6'>支付宝</font>二维码支付<font color='#F32323'>￥5.00</font>"));

			break;

		case 6:
			cz_tv_ts_r
					.setText(Html.fromHtml("扫<font color='#0FDBF6'>支付宝</font>二维码支付<font color='#F32323'>￥1.00</font>"));

			break;

		default:
			break;
		}
	}

	private void hySetWeiXinPay(int czMoney2) {
		// TODO Auto-generated method stub
		switch (czMoney2) {
		case 1:
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>30.00元</font>请扫<font color='#0FDBF6'>支付宝</font>二维码支付"));

			break;

		case 2:
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>88.00元</font>请扫<font color='#0FDBF6'>支付宝</font>二维码支付"));
			break;

		case 3:
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>288.00元</font>请扫<font color='#0FDBF6'>支付宝</font>二维码支付"));
			break;

		case 4:
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>899.00元</font>请扫<font color='#0FDBF6'>支付宝</font>二维码支付"));
			break;

		case 5:
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>158.00元</font>请扫<font color='#0FDBF6'>支付宝</font>二维码支付"));
			break;

		case 6:
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>1199.00元</font>请扫<font color='#0FDBF6'>支付宝</font>二维码支付"));
			break;

		default:
			break;
		}
	}

	private void gdOnClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.gd_rl_jl:
			startActivity(new Intent(MainActivity.this, HistoryActivity.class));
			break;

		case R.id.gd_rl_gy:
			startActivity(new Intent(MainActivity.this, AboutActivity.class));
			break;

		case R.id.gd_rl_fk:
			startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
			break;

		case R.id.gd_rl_bz:
			startActivity(new Intent(MainActivity.this, HelpActivity.class));
			break;
		default:
			break;
		}
	}

	private void hySetMoneyChange(int j) {
		// TODO Auto-generated method stub
		switch (j) {
		case 1:
			hyIsWeiXinPay = true;
			hy_tv_qh.setText("切换支付宝");
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>30.00元</font>请扫<font color='#7FD743'>微信</font>二维码支付"));
			break;

		case 2:
			hyIsWeiXinPay = true;
			hy_tv_qh.setText("切换支付宝");
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>88.00元</font>请扫<font color='#7FD743'>微信</font>二维码支付"));
			break;

		case 3:
			hyIsWeiXinPay = true;
			hy_tv_qh.setText("切换支付宝");
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>288.00元</font>请扫<font color='#7FD743'>微信</font>二维码支付"));
			break;

		case 4:
			hyIsWeiXinPay = true;
			hy_tv_qh.setText("切换支付宝");
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>899.00元</font>请扫<font color='#7FD743'>微信</font>二维码支付"));
			break;

		case 5:
			hyIsWeiXinPay = true;
			hy_tv_qh.setText("切换支付宝");
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>158.00元</font>请扫<font color='#7FD743'>微信</font>二维码支付"));
			break;

		case 6:
			hyIsWeiXinPay = true;
			hy_tv_qh.setText("切换支付宝");
			hy_tv_ts_r.setText(
					Html.fromHtml("差<font color='#F32323'>1199.00元</font>请扫<font color='#7FD743'>微信</font>二维码支付"));
			break;

		default:
			break;
		}
	}

	private void czSetMoneyChange(int j) {
		// TODO Auto-generated method stub
		switch (j) {
		case 1:
			czIsWeiXinPay = true;
			cz_tv_qh.setText("切换支付宝");
			cz_tv_ts_r.setText(
					Html.fromHtml("扫<font color='#7FD743'>微信</font>二维码支付<font color='#F32323'>￥200.00</font>"));
			break;

		case 2:
			czIsWeiXinPay = true;
			cz_tv_qh.setText("切换支付宝");
			cz_tv_ts_r
					.setText(Html.fromHtml("扫<font color='#7FD743'>微信</font>二维码支付<font color='#F32323'>￥50.00</font>"));
			break;

		case 3:
			czIsWeiXinPay = true;
			cz_tv_qh.setText("切换支付宝");
			cz_tv_ts_r
					.setText(Html.fromHtml("扫<font color='#7FD743'>微信</font>二维码支付<font color='#F32323'>￥20.00</font>"));
			break;

		case 4:
			czIsWeiXinPay = true;
			cz_tv_qh.setText("切换支付宝");
			cz_tv_ts_r
					.setText(Html.fromHtml("扫<font color='#7FD743'>微信</font>二维码支付<font color='#F32323'>￥10.00</font>"));
			break;

		case 5:
			czIsWeiXinPay = true;
			cz_tv_qh.setText("切换支付宝");
			cz_tv_ts_r
					.setText(Html.fromHtml("扫<font color='#7FD743'>微信</font>二维码支付<font color='#F32323'>￥5.00</font>"));
			break;

		case 6:
			czIsWeiXinPay = true;
			cz_tv_qh.setText("切换支付宝");
			cz_tv_ts_r
					.setText(Html.fromHtml("扫<font color='#7FD743'>微信</font>二维码支付<font color='#F32323'>￥1.00</font>"));
			break;

		default:
			break;
		}
	}

	private void czSetTextColor(int czMoney2) {
		// TODO Auto-generated method stub
		// first_tv,second_tv,third_tv,fourth_tv,fif_tv,six_tv;
		switch (czMoney2) {
		case 1:
			first_tv.setTextColor(this.getResources().getColor(R.color.tab_yellow));
			break;
		case 2:
			second_tv.setTextColor(this.getResources().getColor(R.color.tab_yellow));
			break;
		case 3:
			third_tv.setTextColor(this.getResources().getColor(R.color.tab_yellow));
			break;
		case 4:
			fourth_tv.setTextColor(this.getResources().getColor(R.color.tab_yellow));
			break;
		case 5:
			fif_tv.setTextColor(this.getResources().getColor(R.color.tab_yellow));
			break;
		case 6:
			six_tv.setTextColor(this.getResources().getColor(R.color.tab_yellow));
			break;

		default:
			break;
		}
	}

	private void showMoneyImg(int czMoney2) {
		// TODO Auto-generated method stub
		switch (czMoney2) {
		// first_iv_smallicon,second_iv_smallicon,third_iv_smallicon,fourth_iv_smallicon,fif_iv_smallicon,six_iv_smallicon
		case 1:
			first_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;
		case 2:
			second_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;

		case 3:
			third_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;

		case 4:
			fourth_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;

		case 5:
			fif_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;

		case 6:
			six_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;

		default:
			break;
		}
	}

	private void hiddMoneyImg(int czMoney2) {
		// TODO Auto-generated method stub
		switch (czMoney2) {
		// first_iv_smallicon,second_iv_smallicon,third_iv_smallicon,fourth_iv_smallicon,fif_iv_smallicon,six_iv_smallicon
		case 1:
			first_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			break;
		case 2:
			second_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			break;
		case 3:
			third_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			break;
		case 4:
			fourth_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			break;
		case 5:
			fif_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			break;
		case 6:
			six_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
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
		if (hasFocus) {
			switch (v.getId()) {
			case R.id.sj_btn:
				// sj_btn.setBackgroundResource(R.color.sj_btn);
				break;

			default:
				break;
			}
		}
	}

	private void gallary() {
		Integer[] images = { R.drawable.img001, R.drawable.img002, R.drawable.img003, R.drawable.img004,
				R.drawable.img005, R.drawable.img006, R.drawable.img007, R.drawable.img008, R.drawable.img009,
				R.drawable.img010, R.drawable.img011, R.drawable.img012, };

		ImageAdapter adapter = new ImageAdapter(this, images);
		adapter.createReflectedImages();// 创建倒影效果
		GalleryFlow galleryFlow = (GalleryFlow) this.findViewById(R.id.Gallery01);
		galleryFlow.setFadingEdgeLength(0);
		galleryFlow.setSpacing(-10); // 图片之间的间距
		galleryFlow.setAdapter(adapter);

		galleryFlow.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
				startActivity(new Intent(MainActivity.this, DetailDramaActivity.class));
			}

		});
		galleryFlow.setSelection(4);
	}

	private void getData() {
		// TODO Auto-generated method stub
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				// TODO Auto-generated method stub

				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						StringBuffer sb = new StringBuffer();
						int ret = epgSDK.getInstance().getMovieDetail("" + adapter.getItem(position).getId(), "",
								"217466", "", "", sb);
						Message msg = Message.obtain();
						// msg.what = GET_MOVIE_DETAIL;
						msg.obj = sb;
						Log.i("info", "--------getMovieDetail-" + sb);
						// mHandler.sendMessage(msg);
						Intent intent = new Intent(MainActivity.this, DetailDramaActivity.class);
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
						int ret = epgSDK.getInstance().getMovieDetail("" + adapter.getItem(position).getId(), "",
								"217466", "", "", sb);
						Message msg = Message.obtain();
						// msg.what = GET_MOVIE_DETAIL;
						msg.obj = sb;
						Log.i("info", "--------getMovieDetail-" + sb);
						// mHandler.sendMessage(msg);
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
			main_time.setText((String)msg.obj);
		};
	};

}
