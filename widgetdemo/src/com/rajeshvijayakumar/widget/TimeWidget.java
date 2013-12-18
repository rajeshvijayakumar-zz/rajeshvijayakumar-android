package com.rajeshvijayakumar.widget;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RemoteViews;
import android.widget.Toast;

public class TimeWidget extends AppWidgetProvider implements OnClickListener {

	private Date mCurrentTime;
	private int mYear;
	private PendingIntent pendingIntent;
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Toast.makeText(context, "onUpdate", Toast.LENGTH_SHORT).show();

		RemoteViews remoteViews1 = new RemoteViews(context.getPackageName (), R.layout.wc_home_widget); 
//		Intent intent = new Intent(context, AddLocationActivity.class);
//		pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new MyTime(context, appWidgetManager), 1, 1000);
	
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	private class MyTime extends TimerTask {
		
		RemoteViews remoteViews;
		AppWidgetManager appWidgetManager;
		ComponentName thisWidget;
		DateFormat format = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());
				
		public MyTime(Context context, AppWidgetManager appWidgetManager) {
			this.appWidgetManager = appWidgetManager;
			
			remoteViews = new RemoteViews(context.getPackageName(), R.layout.wc_home_widget);
			remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
			thisWidget = new ComponentName(context, TimeWidget.class);
			
		}
		
		@Override
		public void run() {
			mCurrentTime = new Date();
			remoteViews.setTextViewText(R.id.widget_textView, "" + format.format(mCurrentTime));
			remoteViews.setTextViewText(R.id.day_textView, ""+DateTimeUtils.getDayName(mCurrentTime.getDay()));
			remoteViews.setTextViewText(R.id.date_textView, ""+mCurrentTime.getDate()+" "+DateTimeUtils.getMonthName(mCurrentTime.getMonth()));
			
			//  MMM
				
			appWidgetManager.updateAppWidget(thisWidget, remoteViews);
		}
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		final String action = intent.getAction();
		if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)) {
			final int appWidgetId = intent.getExtras().getInt(
					AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
			if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
				this.onDeleted(context, new int[] { appWidgetId });
			}
		} else {
			super.onReceive(context, intent);
		}
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Toast.makeText(context, "onDelete", Toast.LENGTH_SHORT).show();
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
}
