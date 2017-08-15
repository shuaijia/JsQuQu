package com.mykj.qupingfang.mine.model;

import com.mykj.qupingfang.domain.mine.CollectionLog;
import com.mykj.qupingfang.mine.contract.MineCollectionContract;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import rx.Subscriber;

/**
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class MineCollectionModelImpl implements MineCollectionContract.MineCollectionModel {
    @Override
    public void getCollectionLogs(String userId, final MineCollectionContract.MineCollectionCallBack mineCollectionCallBack) {
        HttpMethod.getInstance().getCollectionLogs(userId, new Subscriber<CollectionLog>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mineCollectionCallBack.onError(e.toString());
            }

            @Override
            public void onNext(CollectionLog collectionLog) {
                mineCollectionCallBack.onSuccess(collectionLog);
            }
        });
    }
}
