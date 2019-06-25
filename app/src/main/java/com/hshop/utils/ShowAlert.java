package com.hshop.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class ShowAlert {
	
	public static String failmsgtitle ="Fail";
	public static String failmsgtitle1 ="Server Error";
	public static String failmsgtitle2 ="You are Inactive";
	public static String failmsg ="Internet Connection is NOT Available";
	public static String failmsg1 ="Temporary Server Down...!!!";
	public static String failmsg2 ="Please call to Admin...!!!";

	public void showAlertDialog(Context context, String title, String message, Boolean status) {
		// AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// Setting Dialog Title
		builder.setTitle(title);

		// Setting Dialog Message
		builder.setMessage(message);

		if (status != null)
			// Setting alert dialog icon
			// builder.setIcon((status) ? R.drawable.success : R.drawable.fail);

			// Setting OK Button
			builder.setNegativeButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {


						}
					});

		// Showing Alert Message
		builder.show();
	}

	public void showAlertDialog(Context context, String title, String message) {
		// AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// Setting Dialog Title
		builder.setTitle(title);

		// Setting Dialog Message
		builder.setMessage(message);
					// Setting alert dialog icon
			// builder.setIcon((status) ? R.drawable.success : R.drawable.fail);

			// Setting OK Button
			builder.setNegativeButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {


						}
					});

		// Showing Alert Message
		builder.show();
	}

}

/*
 
        ShowAlert alert = new ShowAlert();
		
		 alert.showAlertDialog(getActivity(), "Fail",
					"Internet Connection is NOT Available", false);
		
 
 * */
