package com.mykj.qupingfang.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 这里是关于Fragment的基础封装类
 *
 * @author hm
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    private boolean openLog = true;
    private String className = getClass().getSimpleName() + "";
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    protected T mPresenter;

    //定义一个View用来保存Fragment创建的时候使用打气筒工具进行的布局获取对象的存储
    protected View view;

    /**
     * 当Fragment进行创建的时候执行的方法
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();//创建presenter

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    /**
     * 这个方法是关于Fragment完成创建的过程中，进行界面填充的方法,该方法返回的是一个view对象
     * 在这个对象中封装的就是Fragment对应的布局
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initFragmentView(inflater);
        //System.out.println("BaseFragment---------onCreateView");
        return view;
    }

    /**
     * 这个方法当onCreateView方法中的view创建完成之后，执行
     * 在inflate完成view的创建之后，可以将对应view中的各个控件进行查找findViewById
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initFragmentChildView(view);
        //System.out.println("BaseFragment---------onViewCreated");
    }

    /**
     * 这个方法是在Fragment完成创建操作之后，进行数据填充操作的时候执行的方法
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragmentData(savedInstanceState);
        //System.out.println("BaseFragment---------onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("BaseFragment---------onStart");
    }


    /**
     * 完成打气筒操作
     */
    protected abstract View initFragmentView(LayoutInflater inflater);

    /**
     * 进行findViewById的操作
     *
     * @param view 打气筒生成的View对象
     */
    protected abstract void initFragmentChildView(View view);

    /**
     * 网络数据填充的操作
     *
     * @param savedInstanceState
     */
    protected abstract void initFragmentData(Bundle savedInstanceState);

    /**
     * 创建Presenter对象
     */
    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(null!=mPresenter){
            mPresenter.detachView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(null!=mPresenter) {
            mPresenter.attachView((V) this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
