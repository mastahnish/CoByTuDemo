package com.example.jacek.cobytudemo;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsefulFunctions {

    private Context ctx;

    public UsefulFunctions(Context context) {
        ctx = context;
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("cobytu", "Failure to get drawable id.", e);
            return -1;
        }
    }

    public static boolean alignsPatternCharacters(String s) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        return p.matcher(s).matches();
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    public static void largeLog(String tag, String content) {
	/*	if (content.length() > 4000) {
			Log.d(tag, content.substring(0, 4000));
			largeLog(tag, content.substring(4000));
		} else {
			Log.d(tag, content);
		}*/

        int maxLogSize = 4000;
        for(int i = 0; i <= content.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i+1) * maxLogSize;
            end = end > content.length() ? content.length() : end;
            Log.v("cobytu", content.substring(start, end));
        }

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}