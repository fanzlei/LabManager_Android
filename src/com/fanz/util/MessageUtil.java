package com.fanz.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Message工具类
 * 
 * @author fanz
 *
 */
public class MessageUtil {
	public static void shortMessage(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
