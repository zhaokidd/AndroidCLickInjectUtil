package com.yoongoo.zy.inject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.util.Log;
import android.view.View.OnClickListener;

/**
 * @author zy
 * */
public class ClickUtil {
	private final static String TAG = "ClickUtil";
	private final static String METHOD_SET_LISTENER = "setOnClickListener";
	private final static String METHOD_FIND_VIEW_BY_ID = "findViewById";

	/**
	 * @param view
	 *            The view whose child view need to be set onclicklistener
	 * @param listener
	 *            The onclickListener
	 * */
	public static void inject(Object holderObject, OnClickListener listener) {
		inject(holderObject, holderObject, listener);
	}

	public static void inject(Object holder, Object injectObject,
			OnClickListener listener) {
		Class<?> clazzHoler = holder.getClass();
		Class<?> clazzView = injectObject.getClass();
		Field[] fields = clazzHoler.getDeclaredFields();

		for (Field field : fields) {
			ClickInject clickInjectAnnotation = field
					.getAnnotation(ClickInject.class);
			if (clickInjectAnnotation != null) {
				int viewId = clickInjectAnnotation.value();
				if (viewId > 0) {
					try {
						Method method = clazzView.getMethod(
								METHOD_FIND_VIEW_BY_ID, int.class);
						Object resObject = method.invoke(injectObject, viewId);
						field.setAccessible(true);
						field.set(holder, resObject);
						if (resObject != null) {
							Class<?> clazzInject = resObject.getClass();
							Method method2 = clazzInject.getMethod(
									METHOD_SET_LISTENER, OnClickListener.class);
							method2.invoke(resObject, listener);
						} else {
							Log.e(TAG, "view has not been found");
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					Log.e(TAG, "res id is not illegal");
				}
			}
		}

	}
}
