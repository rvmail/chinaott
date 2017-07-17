package www.chinaott.net.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import www.chinaott.net.R;
import www.chinaott.net.R.id;
import www.chinaott.net.R.layout;
import www.chinaott.net.R.menu;

public class FeedbackActivity extends Activity {

	private TextView feed_ts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_feedback);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		feed_ts=(TextView)findViewById(R.id.feed_ts);
		feed_ts.setText(
				Html.fromHtml("更多问题请到全球播官方网站倾诉：<font color='#00E1FF'>http://www.golive-tv.com</font>或致电：<font color='#00E1FF'>4001876867(9-21点)QQ：4066337</font>"));

	}
}
