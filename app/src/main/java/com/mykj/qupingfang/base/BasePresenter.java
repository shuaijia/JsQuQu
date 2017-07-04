package com.mykj.qupingfang.base;

/**
 * Description: Presenter的根父类
 * Created by jia on 2016/10/27.
 * 人之所以能，是相信能
 */
public abstract class BasePresenter<T> {

    //View接口类型的弱引用
//    protected Reference<T> mViewRef;

    protected T mView;

    public void attachView(T view) {

        //建立关系
//        mViewRef = new WeakReference<T>(view);

        mView = view;
    }

    protected T getView() {

//        return mViewRef.get();
        return mView;
    }

    public boolean isViewAttached() {

//        return mViewRef != null && mViewRef.get() != null;
        return mView != null;
    }


    public void detachView() {

//        if (mViewRef != null) {
//
//            mViewRef.clear();
//            mViewRef = null;
//        }

        mView=null;
    }

}
