package com.rui.androidmvvmdemo.di.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.livedata.SingleLiveEvent;
import com.rui.mvvm.viewmodel.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.subjects.Subject;

/**
 * Created by rui on 2019/2/12
 */
public class MainViewModel extends BaseViewModel {
    /**
     * 给ViewPager添加ObservableList数据
     */
    @Inject
    public ObservableList<Fragment> items;
    /**
     * 关闭页面的事件
     */
    @Inject
    public SingleLiveEvent<Void> finishAct;
    /**
     * 搜索关键词
     */
    @Inject
    public ObservableField<String> keyWord;
    /**
     * 键盘收起的事件
     */
    @Inject
    public SingleLiveEvent<Void> closeKeyBoard;
    /**
     * 发出搜索事件
     */
    @Inject
    public Subject<String> subject;

    /**
     * @param application ，getApplication()方法可以得到application
     */
    @Inject
    public MainViewModel(@NonNull BaseApplication application) {
        super(application);
    }

    public void addPage() {
//        items.add(ProductImgFragment.newInstance(getApplication(), "F", "无图商品"));
//        items.add(ProductImgFragment.newInstance(getApplication(), "T", "有图商品"));
    }
    /**
     * 点击事件退出示例2
     */
    public void closeAct(View view) {
        finishAct.call();
        Toast.makeText(getApplication(), view.getContext().getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击事件退出示例1
     */
    public View.OnClickListener listener = v -> {
        finishAct.call();
        Toast.makeText(getApplication(), "onClick!", Toast.LENGTH_SHORT).show();
    };

    public boolean onSearch(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
//                Timber.d("---------->去搜索=>" + etSearchInput.getText().toString());
            subject.onNext(keyWord.get());
            closeKeyBoard.call();
            return true;
        }
        return false;
    }
    /**
     * 清楚输入的搜索关键词
     */
    public void clearkeyWord(View view) {
        keyWord.set("");
        subject.onNext(keyWord.get());
    }


}
