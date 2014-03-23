package com.pkg.ownplugin;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.CordovaActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NotifyMe extends CordovaPlugin {
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("notifyme")) {
            String title = args.getString(0); 
            String theme = args.getString(1);
            String ticker = args.getString(2);
            this.notifyme(title, callbackContext);
            this.createNotification(title, theme, ticker);
            return true;
        }
        return false;
    }

    private void notifyme(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) { 
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    
	private void createNotification(String title, String theme, String ticker) {

        Intent intent = ((CordovaActivity) this.cordova.getActivity()).getIntent();
        PendingIntent pIntent = PendingIntent.getActivity(((CordovaActivity) cordova).getContext(), 0, intent, 0);

        Bitmap icon = BitmapFactory.decodeResource(((CordovaActivity) this.cordova.getActivity()).getResources(), R.drawable.ic_launcher);

        Notification noti = new Notification.Builder(((CordovaActivity) cordova).getContext())
        .setContentTitle(title)
        .setContentText(theme)
        .setTicker(ticker)
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setLargeIcon(icon)
        .setAutoCancel(true)
        .setContentIntent(pIntent)
        .build();

        NotificationManager notificationManager =
                (NotificationManager) ((CordovaActivity) cordova).getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, noti);
    }
}
