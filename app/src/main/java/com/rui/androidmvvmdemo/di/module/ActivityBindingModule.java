package com.rui.androidmvvmdemo.di.module;


import com.rui.androidmvvmdemo.ui.activity.LoginActivity;
import com.rui.mvvm.dagger.scopes.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * activity注入器，提供activity所需要的注入对象
 * 可以理解为：新activity都需要到这里注册,以便获取需要的注入对象
 * Created by rui on 2019/2/13.
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivityInjector();

//    @ActivityScope
//    @ContributesAndroidInjector(modules = MainModule.class)
//    abstract MainActivity mainActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = SearchProModule.class)
//    abstract SearchProActivity searchProActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = ShiftModule.class)
//    abstract ShiftActivity ShiftActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = CasherModule.class)
//    abstract CasherActivity casherActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = ClerksModule.class)
//    abstract ClerksActivity clerksActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = SettleAccModule.class)
//    abstract SettleAccActivity settleAccActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = SuccessPayModule.class)
//    abstract SuccessPayActivity successPayActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = CouponsModule.class)
//    abstract CouponsActivity couponsActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = GiftCheckModule.class)
//    abstract GiftCheckActivity giftCheckActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = RefundsModule.class)
//    abstract RefundsActivity refundsActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = RefundDtlModule.class)
//    abstract RefundDtlActivity refundDtlActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = RefundSearchModule.class)
//    abstract RefundSearchActivity refundSearchActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = OrdersModule.class)
//    abstract OrdersActivity ordersActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = OrderSearchModule.class)
//    abstract OrderSearchActivity orderSearchActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = OrderDtlModule.class)
//    abstract OrderDtlActivity orderDtlActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = PerformanceModule.class)
//    abstract PerformanceActivity performanceActivityActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = BusinessDtlModule.class)
//    abstract BusinessDtlActivity businessDtlActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = RankingModule.class)
//    abstract RankingActivity rankingActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = RefundMemModule.class)
//    abstract RefundMemActivity refundMemActivityInjector();
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = ChargeModule.class)
//    abstract ChargeActivity chargeActivityInjector();


}
