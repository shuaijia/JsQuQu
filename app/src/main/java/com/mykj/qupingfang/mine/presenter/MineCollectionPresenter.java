package com.mykj.qupingfang.mine.presenter;

import com.mykj.qupingfang.base.BasePresenter;
import com.mykj.qupingfang.domain.mine.CollectionLog;
import com.mykj.qupingfang.domain.mine.DeleteMyCollection;
import com.mykj.qupingfang.mine.contract.MineCollectionContract;
import com.mykj.qupingfang.mine.model.MineCollectionModelImpl;

/**
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class MineCollectionPresenter extends BasePresenter<MineCollectionContract.MineCollectionView> {

    private MineCollectionModelImpl model;

    private MineCollectionContract.MineCollectionView view;

    public MineCollectionPresenter(MineCollectionContract.MineCollectionView view) {
        model = new MineCollectionModelImpl();
        this.view = view;
    }

    public void getCollectionLogs(String userId) {
        model.getCollectionLogs(userId, new MineCollectionContract.MineCollectionCallBack() {
            @Override
            public void onSuccess(CollectionLog collectionLog) {
                view.getCollectionLogsSuccess(collectionLog);
            }

            @Override
            public void onError(String errorMsg) {
                view.getCollectionLogsError(errorMsg);
            }
        });
    }

    public void deleteOneCollection(final int position, String userId, String resourseId) {
        model.deleteOneCollection(position, userId, resourseId, new MineCollectionContract.DeleteOneCollectionCallBack() {
            @Override
            public void onSuccess(DeleteMyCollection deleteMyCollection) {
                view.deleteOneCollectionSuccess(position, deleteMyCollection);
            }

            @Override
            public void onError(String errorMsg) {
                view.deleteOneCollectionError(errorMsg);
            }
        });
    }
}
