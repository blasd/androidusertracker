package androidusertracker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

public class TestClass {

	protected View v = Mockito.mock(View.class);
	protected AnalyzableContext mockedContext = Mockito.mock(AnalyzableContext.class);

	@Before
	public void before() {
		Mockito.reset(v, mockedContext);

		Mockito.when(v.getContext()).thenReturn(mockedContext);
	}

	@Test
	public void testOnClickListener() {
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// any application code
			}
		};

		listener.onClick(v);

		Mockito.verify(mockedContext, Mockito.times(1)).doAnalytics(listener, v);
	}

	@Test
	public void testOnLongClickListener() {
		OnLongClickListener listener = new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// any application code
				return true;
			}
		};

		listener.onLongClick(v);

		Mockito.verify(mockedContext, Mockito.times(1)).doAnalytics(listener, v, true);
	}

	@Test
	public void testOnItemClickListener() {

		OnItemClickListener listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// any application code

			}
		};

		listener.onItemClick(null, v, 0, 0);

		Mockito.verify(mockedContext, Mockito.times(1)).doAnalytics(listener, null, v, 0, 0);

	}

	@Test
	public void testOnItemLongClickListener() {
		OnItemLongClickListener listener = new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// any application code
				return true;
			}
		};

		listener.onItemLongClick(null, v, 0, 0);

		Mockito.verify(mockedContext, Mockito.times(1)).doAnalytics(listener, null, v, 0, 0, true);
	}

	@Test
	public void testOnGroupClickListener() {
		OnGroupClickListener listener = new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				// any application code
				return true;
			}
		};

		listener.onGroupClick(null, v, 0, 0);

		Mockito.verify(mockedContext, Mockito.times(1)).doAnalytics(listener, null, v, 0, 0, true);
	}

	@Test
	public void testOnChildClickListener() {
		OnChildClickListener listener = new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				// any application code
				return true;
			}
		};

		listener.onChildClick(null, v, 0, 0, 0);

		Mockito.verify(mockedContext, Mockito.times(1)).doAnalytics(listener, null, v, 0, 0, 0, true);
	}
}
