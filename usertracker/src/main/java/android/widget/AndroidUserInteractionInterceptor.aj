package android.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.test.IAndroidAnalyzable;
import com.test.IAndroidNotAnalyzable;

/**
 * The Trace aspect injects tracing messages before and after method main of
 * class HelloWorld.
 */

aspect AndroidUserInteractionInterceptor {

	pointcut OnClickListener_onClick(View v)         :execution(void    OnClickListener+.onClick(View))         && args(v) && !this(IAndroidNotAnalyzable);

	pointcut OnLongClickListener_onLongClick(View v) :execution(boolean OnLongClickListener+.onLongClick(View)) && args(v) && !this(IAndroidNotAnalyzable);


	pointcut OnItemClickListener_onItemClick(View v) :execution(boolean OnItemClickListener+.onItemClick(View)) && args(v) && !this(IAndroidNotAnalyzable);

	pointcut OnItemLongClickListener_onItemLongClick(View v) :execution(boolean OnItemLongClickListener+.onItemLongClick(View)) && args(v) && !this(IAndroidNotAnalyzable);

    
	protected IAndroidAnalyzable getAnalyzable(View v) {
		if (v == null) {
			return null;
		}

		Context c = v.getContext();

		IAndroidAnalyzable analyzable = null;

		if (c instanceof IAndroidAnalyzable) {
			analyzable = (IAndroidAnalyzable) c;
		} else if (c.getApplicationContext() instanceof IAndroidAnalyzable) {
			analyzable = (IAndroidAnalyzable) c.getApplicationContext();
		}

		return analyzable;
	}

	/**
	 * After as we want to leg if events returned true or false
	 * 
	 * @param v
	 */
	before(View v) : OnClickListener_onClick(v) {
		IAndroidAnalyzable analyzable = getAnalyzable(v);

		if (analyzable != null) {
			if (thisJoinPoint.getTarget() instanceof OnClickListener) {
				analyzable.doAnalytics(v, (OnClickListener) thisJoinPoint.getTarget());
			} else {
				// TODO: Log unexpected call
			}
		}
	}

	before(View v) : OnLongClickListener_onLongClick(v) {
		IAndroidAnalyzable analyzable = getAnalyzable(v);

		if (analyzable != null) {
			if (thisJoinPoint.getTarget() instanceof OnLongClickListener) {
				analyzable.doAnalytics(v, (OnLongClickListener) thisJoinPoint.getTarget());
			}
		}
	}
}