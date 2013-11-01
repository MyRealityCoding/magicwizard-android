package myreality.development.magicwizard.activities;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.components.core.ComponentHandler;
import myreality.development.magicwizard.components.core.ComponentHandlerFactory;
import myreality.development.magicwizard.components.core.SimpleComponentHandlerFactory;
import android.os.Bundle;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Main activity of the application
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 1.1
 * @since 1.0
 */
public class MainActivity extends MagicActivity {

	// Target bundle to save data with
	private Bundle bundle;

	private SlidingMenu menu;
	
	private ComponentHandler handler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		setContentView(R.layout.main);

		ComponentHandlerFactory handlerFactory = new SimpleComponentHandlerFactory(this);
		handler = handlerFactory.create();
		this.bundle = bundle;
		
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.LEFT);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);		
		menu.setBehindWidthRes(R.dimen.menu_width);
		menu.setFadeDegree(1.0f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.menu);
		
		handler.onCreate(this);
	}
	
	public void onButtonClick(View view) {
		handler.handle(view.getId(), this, view);
		menu.showContent(true);
	}

	@Override
	public void setContentView(int layoutResID) {
		
		clear();
		
		if (menu != null) {
			menu.setContent(layoutResID);
		} else {
			super.setContentView(layoutResID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		reset();
		load(bundle);
		handler.onResume(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see myreality.development.magicwizard.activities.MagicActivity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		save(bundle);
		handler.onPause(this);
	}
	
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.onDestroy(this);
	}

	@Override
	protected void onRestoreInstanceState(Bundle bundle) {
		super.onRestoreInstanceState(bundle);
		this.bundle = bundle;
		load(bundle);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		this.bundle = outState;
		save(bundle);
	}
}
