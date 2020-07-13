package vn.tien.nvtimage.ui.infor_user.fragment_collections;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.data.repository.PhotoRepository;

public class CollectionsUserViewModel extends ViewModel {
    private MutableLiveData<List<Collection>> mCollections;
    private PhotoRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public void initViewModel(Context context) {
        mRepository = PhotoRepository.getInstance(context);
        mCollections = new MutableLiveData<>();
        mCompositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<List<Collection>> getCollections(String username) {
        loadData(username);
        return mCollections;
    }

    private void loadData(String username) {
        Disposable disposable = mRepository.getCollectionsOfUser(username, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(reponse -> mCollections.setValue(reponse));
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
