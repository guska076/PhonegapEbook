package com.pkg.photocapture;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;

public class SharePhoto extends CordovaPlugin {
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("share")) {
        	String txt = args.getString(0); 
        	this.share(txt, callbackContext);
            return true;
        }
        return false;
    }

	private void share(String uriToImage, CallbackContext callbackContext) {
		if (uriToImage != null && uriToImage.length() > 0) { 
            callbackContext.success("Success");
            Intent sendIntent = new Intent();
            sendIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
            sendIntent.setType("image/*");
            ((CordovaActivity) this.cordova.getActivity()).startActivity(Intent.createChooser(sendIntent, ((CordovaActivity) this.cordova.getActivity()).getResources().getText(R.string.hello)));
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
	}
}
