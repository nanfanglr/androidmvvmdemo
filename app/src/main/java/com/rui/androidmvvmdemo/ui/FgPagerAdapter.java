package com.rui.androidmvvmdemo.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import static com.rui.mvvm.dagger.modules.BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER;

/**
 * Created by rui on 2018/9/1
 * fragment使用这个适配器需要对源码进行了解，否则在出现一些bug的时候，很难处理。
 * 以下的博客链接是Fragment状态保存、FragmentStatePagerAdapter以及FragmentPagerAdapter运行机制的知识
 * https://blog.csdn.net/happylishang/article/details/78961984
 * https://blog.csdn.net/xiaofei_it/article/details/45675497
 * https://blog.csdn.net/u013651026/article/details/75322221
 * https://www.jianshu.com/p/79018b848b92
 * https://segmentfault.com/a/1190000003965285
 */
public class FgPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public FgPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Inject
    public FgPagerAdapter(@Named(ACTIVITY_FRAGMENT_MANAGER) FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList<>();
    }

    public List<Fragment> getFragmentList() {
        if (fragmentList == null) {
            return new ArrayList<>();
        }
        return fragmentList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        super.getItemPosition(object);
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        Timber.d("---------->setVpAdapter.getCount=>" + getCount());
        String title = getItem(position).getArguments().getString("title");
        return title;
    }

    @Override
    public Fragment getItem(int position) {
        return (fragmentList == null || fragmentList.size() == 0) ? null
                : fragmentList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}



