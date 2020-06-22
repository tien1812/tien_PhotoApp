package vn.tien.nvtimage.ui.search.collections;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.data.repository.PhotoRepository;

public class ResultCollectionViewModel extends ViewModel {
    private MutableLiveData<Collection> mCollections;
    private PhotoRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public void initViewModel(Context context) {
        mCompositeDisposable = new CompositeDisposable();
        mRepository = PhotoRepository.getInstance(context);
    }

    public MutableLiveData<Collection> getCollections(String query) {
        mCollections = new MutableLiveData<>();
        loadData(query);
        return mCollections;
    }

    private void loadData(String query) {
        Disposable disposable = mRepository.searchCollection(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(reponse -> mCollections.setValue(reponse),
                        error -> Log.d("tag", error.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
