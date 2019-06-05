package com.souyute.toolkit;

import android.content.ClipboardManager;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/3/22.
 */
public class StringUtils {

    private static DecimalFormat twolastDF = new DecimalFormat("#0.00");
    private static DecimalFormat onelastDF = new DecimalFormat("#0.0");
    private static DecimalFormat IntlastDF = new DecimalFormat("#0");

    public static SpannableStringBuilder setTextColor(String Texts, String kw, int color) {
        Texts = Texts + " ";
        if (TextUtils.isEmpty(kw) || !Texts.contains(kw)) {
            return new SpannableStringBuilder(Texts);
        }

        SpannableStringBuilder builder = new SpannableStringBuilder(Texts);

        String[] str = Texts.split(kw);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            if (i == 0) {
                sb.append(str[i]);
            } else {
                sb.append(kw);
                sb.append(str[i]);
            }
            str[i] = sb.length() + "," + (sb.length() + kw.length());
        }
        for (int i = 0; i < str.length - 1; i++) {
            ForegroundColorSpan redSpan = new ForegroundColorSpan(color);
            builder.setSpan(redSpan, Integer.parseInt(str[i].split(",")[0]), Integer.parseInt(str[i].split(",")[1]), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }

    public static SpannableStringBuilder setTextColor(String content, String key, int colorResouceId, Context context) {
        ForegroundColorSpan redSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary));
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        int chageTextColor = content.indexOf(key);
        if (chageTextColor != -1)
            builder.setSpan(redSpan, chageTextColor, chageTextColor + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    public static SpannableStringBuilder setTextColor(String Texts, String[] kw, int color) {
        Texts = Texts + " ";
        if (kw == null || kw.length == 0) {
            return new SpannableStringBuilder(Texts);
        }

        SpannableStringBuilder builder = new SpannableStringBuilder(Texts);
        for (int j = 0; j < kw.length; j++) {
            String numStr = kw[j];

            String[] str = Texts.split(numStr);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length; i++) {
                if (i == 0) {
                    sb.append(str[i]);
                } else {
                    sb.append(numStr);
                    sb.append(str[i]);
                }
                str[i] = sb.length() + "," + (sb.length() + numStr.length());
            }
            for (int i = 0; i < str.length - 1; i++) {
                ForegroundColorSpan redSpan = new ForegroundColorSpan(color);
                builder.setSpan(redSpan, Integer.parseInt(str[i].split(",")[0]), Integer.parseInt(str[i].split(",")[1]), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return builder;
    }

    public static String twolastDF(double d) {
//        twolastDF.setRoundingMode(RoundingMode.DOWN);//不要四舍五入
        twolastDF.setRoundingMode(RoundingMode.HALF_UP);//要四舍五入
        return twolastDF.format(d);
    }

    public static double twolastDouble(double d) {
//        twolastDF.setRoundingMode(RoundingMode.DOWN);//不要四舍五入
        twolastDF.setRoundingMode(RoundingMode.HALF_UP);//要四舍五入
        return Double.parseDouble(twolastDF.format(d));
    }

    public static String onelastDF(double d) {
//        twolastDF.setRoundingMode(RoundingMode.DOWN);//不要四舍五入
        onelastDF.setRoundingMode(RoundingMode.HALF_UP);//要四舍五入
        return onelastDF.format(d);
    }

    public static double onelastDouble(double d) {
//        twolastDF.setRoundingMode(RoundingMode.DOWN);//不要四舍五入
        onelastDF.setRoundingMode(RoundingMode.HALF_UP);//要四舍五入
        return Double.parseDouble(onelastDF.format(d));
    }

    public static int Intlast(double d) {
//        onelastDF.setRoundingMode(RoundingMode.HALF_UP);//要四舍五入
        return Integer.parseInt(IntlastDF.format(d));
    }

    public static void onClickCopy(Context context, String url) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(url);
        Toast.makeText(context, "复制成功，可以发给朋友们了。", Toast.LENGTH_LONG).show();
    }

    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile("^(13[0-9]|15[0-9]|18[0-9]|17[0-9])\\d{8}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 去掉小数点后的0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }


    public static int getInt(String s) {
        int value = 0;
        if (!TextUtils.isEmpty(s)) {
            try {
                value = Integer.valueOf(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;

    }

    public static float getFloat(String s) {
        float value = 0;
        if (!TextUtils.isEmpty(s)) {
            try {
                value = Float.valueOf(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static float get2Float(String s) {
        float value = getFloat(s);
        if (value == 0) return 0;
        if (value < 0.01f) {
            value = 0.01f;
        }
        value = StringUtils.getFloat(StringUtils.twolastDF(value));
        return value;
    }

}
