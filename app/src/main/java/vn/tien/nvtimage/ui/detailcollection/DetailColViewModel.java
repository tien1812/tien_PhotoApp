package vn.tien.nvtimage.ui.detailcollection;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.data.repository.PhotoRepository;

public class DetailColViewModel extends ViewModel {
    private MutableLiveData<List<Photo>> mPhotos;
    private PhotoRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public void initViewModel(Context context) {
        mPhotos = new MutableLiveData<>();
        mRepository = PhotoRepository.getInstance(context);
        mCompositeDisposable = new CompositeDisposable();
    }

    public LiveData<List<Photo>> getData(int id, int page) {
        loadData(id, page);
        return mPhotos;
    }

    private void loadData(int id, int page) {
        Disposable disposable = mRepository.getPhotoInCollection(id, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(reponse -> mPhotos.setValue(reponse),
                        error -> Log.d("tag", error.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
