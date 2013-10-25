package myreality.development.magicwizard.components;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

/**
 * Handles the rating of this app
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 */
public class RateComponent implements Component {

	@Override
	public void handle(Activity context, View sender) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri
				.parse("market://details?id=myreality.development.magicwizard"));
		context.startActivity(intent);
	}

	@Override
	public void onCreate(Activity context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy(Activity context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPause(Activity context) {
		// TODO Auto-generated method stub
		
	}

}
