package vn.tien.nvtimage.ui.collection;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.data.repository.PhotoRepository;

public class CollectionViewModel extends ViewModel {
    private MutableLiveData<List<Collection>> mCollections;
    private PhotoRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public void initViewModel(Context context) {
        mRepository = PhotoRepository.getInstance(context);
        mCompositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<List<Collection>> getCollections(int page) {
        mCollections = new MutableLiveData<>();
        loadData(page);
        return mCollections;
    }

    private void loadData(int page) {
        Disposable disposable = mRepository.getCollection(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(reponse -> mCollections.setValue(reponse),
                        error -> Log.d("taggg", error.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
