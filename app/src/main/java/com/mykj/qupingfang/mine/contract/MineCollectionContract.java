package com.mykj.qupingfang.mine.contract;

import com.mykj.qupingfang.domain.mine.CollectionLog;

/**
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class MineCollectionContract {

    public interface MineCollectionView {

        void getCollectionLogsSuccess(CollectionLog collectionLog);

        void getCollectionLogsError(String errorMsg);

        //void deleteOneCollection();
    }

    public interface MineCollectionModel{

        void getCollectionLogs(String userId,MineCollectionCallBack mineCollectionCallBack);
    }

    public interface MineCollectionCallBack{

        void onSuccess(CollectionLog collectionLog);

        void onError(String errorMsg);
    }
}
