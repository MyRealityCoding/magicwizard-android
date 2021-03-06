package myreality.development.magicwizard.widgets;

import myreality.development.magicwizard.R;
import myreality.development.magicwizard.activities.MagicActivity;
import myreality.development.magicwizard.layouts.FlipLayout;
import myreality.development.magicwizard.util.Reloadable;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

public class CounterBar extends FlipLayout implements Reloadable {

	protected ValueBar valueBar;
	
	protected Vibrator vibrator;
	
    private LayoutInflater inflater;

	public CounterBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initLayout(context);
	}

	public CounterBar(Context context) {
		super(context);
		initLayout(context);
	}
	
	
	protected void setRuleDefault(int value) {
		valueBar.clearRules();
		valueBar.setMaximum(value);
	}
	
	public void plus() {
		valueBar.plus();
	}
	
	public void minus() {
		valueBar.minus();
	}
	
	public void setValue(int value) {
		valueBar.setValue(value);
	}
	
	public int getValue() {
		return valueBar.getValue();
	}
	
	private void initLayout(Context context) {
		
		if (context instanceof MagicActivity) {
			((MagicActivity) context).addReloadable(this);			
		}
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		addView(inflater.inflate(R.layout.counterbar, null));
		vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
		valueBar = (ValueBar) findViewById(R.id.vb_value);
		// Listener
		ImageButton btnLeft = (ImageButton) findViewById(R.id.btn_count_left);
		btnLeft.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				minus();
			}			
		});
		ImageButton btnRight = (ImageButton) findViewById(R.id.btn_count_right);
		btnRight.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				plus();
			}			
		});
	}
	
	public void reset(Activity activity) {
		
	}

	public void onReload(Activity from, boolean reset) {
		if (reset) {
			reset(from);		
		}
	}

	public void saveToBundle(Bundle bundle) {
		bundle.putInt(String.valueOf(getId()), getValue());
	}

	public void loadFromBundle(Bundle bundle) {
		setValue(bundle.getInt(String.valueOf(getId())));
	}
}
