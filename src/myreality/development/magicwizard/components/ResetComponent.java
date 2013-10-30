package myreality.development.magicwizard.components;

import myreality.development.magicwizard.components.core.AbstractComponent;
import myreality.development.magicwizard.components.core.Component;
import myreality.development.magicwizard.util.MagicToast;
import myreality.development.magicwizard.util.MagicToast.ToastType;
import myreality.development.magicwizard.util.Resetable;
import android.app.Activity;
import android.view.View;

/**
 * Handles the resetting behavior for context
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.2
 * @version 1.2
 * 
 */
public class ResetComponent extends AbstractComponent implements Component {

	@Override
	public void handle(Activity context, View sender) {
		if (context instanceof Resetable) {
			Resetable resetable = (Resetable) context;
			resetable.reset();

			MagicToast
					.show(context, "Successfully resetted", ToastType.SUCCESS);
		}
	}
}