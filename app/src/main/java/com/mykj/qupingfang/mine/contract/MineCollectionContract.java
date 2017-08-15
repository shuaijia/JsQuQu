package com.mykj.qupingfang.mine.contract;

import com.mykj.qupingfang.domain.mine.CollectionLog;
import com.mykj.qupingfang.domain.mine.DeleteMyCollection;

/**
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class MineCollectionContract {

    public interface MineCollectionView {

        void getCollectionLogsSuccess(CollectionLog collectionLog);

        void getCollectionLogsError(String errorMsg);

        void deleteOneCollectionSuccess(int position, DeleteMyCollection deleteMyCollection);

        void deleteOneCollectionError(String errorMsg);
    }

    public interface MineCollectionModel {

        void getCollectionLogs(String userId, MineCollectionCallBack mineCollectionCallBack);

        void deleteOneCollection(int position, String userId, String resourseId, DeleteOneCollectionCallBack deleteOneCollectionCallBack);
    }

    public interface MineCollectionCallBack {

        void onSuccess(CollectionLog collectionLog);

        void onError(String errorMsg);
    }

    public interface DeleteOneCollectionCallBack {

        void onSuccess(DeleteMyCollection deleteMyCollection);

        void onError(String errorMsg);
    }
}
