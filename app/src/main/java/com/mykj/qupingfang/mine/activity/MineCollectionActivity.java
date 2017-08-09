package com.mykj.qupingfang.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.mine.MineCollectionAdapter;
import com.mykj.qupingfang.domain.mine.CollectionLog;
import com.mykj.qupingfang.domain.mine.DeleteMyCollection;
import com.mykj.qupingfang.net.retrofit.HttpMethod;
import com.mykj.qupingfang.utils.SharedPreferencesUtils;
import com.mykj.qupingfang.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * 我的收藏界面
 * Created by jia on 2017/8/5.
 */

public class MineCollectionActivity extends Activity implements View.OnClickListener {

    // 返回键
    private ImageView iv_collection_back;
    // 清除
    private TextView tv_collection_clear;
    // 内容列表
    private RecyclerView rv_collection_content;
    // 无数据界面
    private RelativeLayout rl_collection_no_data;
    // 提示
    private TextView tv_collection_no_data;

    // 上下文
    private Context mContext;

    // 数据列表
    private List<CollectionLog.Data> list = new ArrayList<>();

    private MineCollectionAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_collection);

        mContext = MineCollectionActivity.this;

        // 返回键
        iv_collection_back = (ImageView) findViewById(R.id.iv_collection_back);
        // 清除
        tv_collection_clear = (TextView) findViewById(R.id.tv_collection_clear);
        // 内容列表
        rv_collection_content = (RecyclerView) findViewById(R.id.rv_collection_content);
        // 无数据界面
        rl_collection_no_data = (RelativeLayout) findViewById(R.id.rl_collection_no_data);
        // 提示
        tv_collection_no_data = (TextView) findViewById(R.id.tv_collection_no_data);

        iv_collection_back.setOnClickListener(this);
        tv_collection_clear.setOnClickListener(this);

        // 初始化界面
        tv_collection_clear.setVisibility(View.GONE);
        rl_collection_no_data.setVisibility(View.VISIBLE);
        tv_collection_no_data.setText("加载中...");

        adapter = new MineCollectionAdapter(mContext);

        initData();

        adapter.setOnDeleteListener(new MineCollectionAdapter.OnDeleteListener() {
            @Override
            public void delete(int position) {

                // 请求服务器删除一条记录，成功后执行以下代码
                deleteOne(position, list.get(position).getResource().getResource_id() + "");
            }
        });

        adapter.setOnItemClickListener(new MineCollectionAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                // 点击条目 跳转详情界面
//                ToastUtils.showToastSafe(mContext, list.get(position).getResource().getResource_id() + "");
                ToastUtils.showToastSafe(mContext, adapter.getItem(position).getResource().getTitle() + "");
            }
        });
    }

    /**
     * 获取数据
     */
    private void initData() {

        HttpMethod.getInstance().getCollectionLogs(SharedPreferencesUtils.getData(mContext, "userId", "0"), new Subscriber<CollectionLog>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CollectionLog collectionLog) {
                list = collectionLog.getData();
                if (list != null && list.size() > 0) {
                    rv_collection_content.setLayoutManager(new LinearLayoutManager(mContext));
                    rv_collection_content.setAdapter(adapter);
                    adapter.addData(list);

                    tv_collection_clear.setVisibility(View.VISIBLE);
                    rl_collection_no_data.setVisibility(View.GONE);
                } else {
                    // 无数据
                    tv_collection_clear.setVisibility(View.GONE);
                    rl_collection_no_data.setVisibility(View.VISIBLE);
                    tv_collection_no_data.setText("您还没有收藏过任何视频");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_collection_back:

                finish();

                break;
            case R.id.tv_collection_clear:

                deleteAll();

                break;
        }
    }

    /**
     * 删除单个记录
     *
     * @param resourseId
     */
    public void deleteOne(final int position, String resourseId) {
        HttpMethod.getInstance().deleteOneCollection(SharedPreferencesUtils.getData(mContext, "userId", "0"), resourseId, new Subscriber<DeleteMyCollection>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DeleteMyCollection deleteMyCollection) {
                adapter.deleteData(position);
                if (adapter.getItemCount() == 0) {
                    tv_collection_clear.setVisibility(View.GONE);
                    rl_collection_no_data.setVisibility(View.VISIBLE);
                    tv_collection_no_data.setText("您还没有收藏过任何视频");
                }
            }
        });
    }

    /**
     * 删除全部记录
     */
    public void deleteAll(){
        HttpMethod.getInstance().deleteAllCollection(SharedPreferencesUtils.getData(mContext, "userId", "0"), "1", new Subscriber<DeleteMyCollection>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DeleteMyCollection deleteMyCollection) {
                tv_collection_clear.setVisibility(View.GONE);
                rl_collection_no_data.setVisibility(View.VISIBLE);
                tv_collection_no_data.setText("您还没有收藏过任何视频");
            }
        });
    }
}
