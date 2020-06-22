package vn.tien.nvtimage.ui.search.photos;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.data.repository.PhotoRepository;

public class ResultPhotosModel extends ViewModel {
    private MutableLiveData<Photo> mPhotos;
    private PhotoRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public void initViewModel(Context context) {
        mCompositeDisposable = new CompositeDisposable();
        mRepository = PhotoRepository.getInstance(context);
    }

    public MutableLiveData<Photo> getPhotos(String query) {
        mPhotos = new MutableLiveData<>();
        loadData(query);
        return mPhotos;
    }

    private void loadData(String query) {
        Disposable disposable = mRepository.searchPhoto(query)
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
