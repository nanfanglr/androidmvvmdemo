package com.rui.toolkit;

import android.content.Context;
import android.widget.Toast;


/**
 * 土司工具
 *
 * @author JetteZh
 */
public class ToastUtils {

//    public static Application application;
//    private static boolean debug = false;
//
//    /**
//     * 开启测试toast
//     */
//    public static void openTestToast() {
//        debug = true;
//    }
//
//    /**
//     * 关闭测试toast
//     */
//    public static void closeTestToast() {
//        debug = false;
//    }
//
//    /**
//     * 显示测试toast
//     *
//     * @param context
//     * @param text
//     */
//    public static void showTestToast(Context context, String text) {
//        if (debug)
//            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
//    }

//    /**
//     * 显示toast
//     *
//     * @param text
//     */
//    public static void showToast(String text) {
//        showToast(application, text);
//    }


    public static void showToast(Context context, final String text) {
        if (context == null) return;
        Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

//    public static void showNetWorkErro() {
//        showNetWorkErro(application);
//    }

    public static void showNetWorkErro(Context context) {
        if (context == null) return;
        Toast.makeText(context.getApplicationContext(), "网络异常", Toast.LENGTH_SHORT).show();

    }

//    //自定义带图片的toast
//    public static void showTOASTBTN(Activity context, int drawable, int title) {
//        CostomToast.create(context)
//                .setIcon(drawable)
//                .setMessage(title)
//                .show();
//    }
//
//    //自定义带图片的toast
//    public static void showTOASTBTN(Activity context, int drawable, String title) {
//        CostomToast.create(context)
//                .setIcon(drawable)
//                .setMessage(title)
//                .show();
//    }
//
//
//    //自定义带图片的toast
//    public static Toast showTOASTBTN(String title, int drawable, Activity context) {
//        return showTOASTBTN(context, title, drawable);
//    }
//
//    //自定义带图片的toast
//    public static Toast showTOASTBTN(Activity context, String title, int drawable) {
//
//        LayoutInflater inflater = context.getLayoutInflater();
//        View view = inflater.inflate(R.layout.userdefinedtoast, (ViewGroup) context.findViewById(R.id.toast_layout));
//        TextView txtView_Title = (TextView) view.findViewById(R.id.txt_context);
//        ImageView imageView = (ImageView) view.findViewById(R.id.image_toast);
//        txtView_Title.setText(title);
//        if (drawable == -1) imageView.setVisibility(View.GONE);
//        else imageView.setImageResource(drawable);
//
//        Toast toast = new Toast(context);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(view);
//        toast.show();
//        return toast;
//    }
//
//
//    public static Toast showTOASTBTN(String title, Activity context) {
//        return showTOASTBTN(context, title);
//    }
//
//    public static Toast showTOASTBTN(Activity context, String title) {
//
//        WindowManager wm = (WindowManager) context.getSystemService(Activity.WINDOW_SERVICE);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(displayMetrics);
//        int screenWidth = displayMetrics.widthPixels;
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        LayoutInflater inflater = context.getLayoutInflater();
//        View view = inflater.inflate(R.layout.toast_top_title, (ViewGroup) context.findViewById(R.id.toast_layout));
//        TextView txtView_Title = (TextView) view.findViewById(R.id.txt_context);
//        txtView_Title.setText(title);
//        txtView_Title.setLayoutParams(params);
//
//        Toast toast = new Toast(context);
//        toast.setGravity(Gravity.TOP, 0, dip2px(context, 46));
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(view);
//        toast.show();
//        return toast;
//    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
