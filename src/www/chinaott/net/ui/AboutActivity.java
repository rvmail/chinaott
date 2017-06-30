package www.chinaott.net.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import www.chinaott.net.R;
import www.chinaott.net.R.id;
import www.chinaott.net.R.layout;
import www.chinaott.net.R.menu;

public class AboutActivity extends Activity implements OnClickListener{

	private ImageView first_iv_smallicon,second_iv_smallicon;
	private RelativeLayout rl_first,rl_second;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tab2);
//		setContentView(R.layout.activity_about);
		first_iv_smallicon=(ImageView)findViewById(R.id.first_iv_smallicon);
		second_iv_smallicon=(ImageView)findViewById(R.id.second_iv_smallicon);
		
		rl_first=(RelativeLayout)findViewById(R.id.rl_first);
		rl_second=(RelativeLayout)findViewById(R.id.rl_second);
		rl_first.setOnClickListener(this);
		rl_second.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_first:
			second_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);
			first_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;
			
		case R.id.rl_second:
			first_iv_smallicon.setImageResource(R.drawable.setting_lg_no_selected_icon);;
			second_iv_smallicon.setImageResource(R.drawable.setting_lg_selected_icon);
			break;

		default:
			break;
		}
	}

}
