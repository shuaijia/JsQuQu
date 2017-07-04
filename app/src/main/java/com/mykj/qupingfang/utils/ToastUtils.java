package com.mykj.qupingfang.utils;


import com.mykj.qupingfang.base.BaseApplication;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * 这里定义一个关于Toast的单例类，并且添加上关于线程的操作，保证在任何线程中都可以进行Toast的输出操作
 * 
 * @author hm
 *
 */
public class ToastUtils {

	private static Toast toast;
	
	/**
	 * 对Toast的简易封装。线程安全，可以在非UI线程调用。
	 * @param context  上下文
	 * @param str      显示内容
	 */
	public static void showToastSafe(final Context context,final String str) {
		if (isRunInMainThread()) {
			showToast(context,str);
		} else {
			post(new Runnable() {
				@Override
				public void run() {
					showToast(context,str);
				}
			});
		}
	}
	/**
	 * 完成的是关于Toast的单例输出
	 * @param context
	 * @param msg
	 */
	private static void showToast(Context context, String msg){
		if(toast == null){
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		}
		
		toast.setText(msg);
		toast.show();
	}
	/**
	 * 判断当前的线程是不是在主线程
	 */ 
	private static boolean isRunInMainThread() {
		return android.os.Process.myTid() == getMainThreadId();
	}
	/**
	 *  在主线程执行runnable 
	 */
	private static boolean post(Runnable runnable) {
		return getMainHandler().post(runnable);
	}
	/**
	 * 获取当前应用的主线程的Handler
	 * @return
	 */
	private static Handler getMainHandler(){
		return BaseApplication.getMainHandler();
	} 
	/**
	 * 获取主线程的ID
	 * @return
	 */
	private static int getMainThreadId(){
		return BaseApplication.getMaiThredId();
	}
	
	
}
