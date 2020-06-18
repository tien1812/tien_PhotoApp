package vn.tien.nvtimage.ui.home;

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

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Photo>> mPhotos;
    private CompositeDisposable mCompositeDisposable;
    private PhotoRepository mRepository;

    public void initViewModel(Context context) {
        mCompositeDisposable = new CompositeDisposable();
        mRepository = PhotoRepository.getInstance(context);
    }

    public LiveData<List<Photo>> getPhotos() {
        mPhotos = new MutableLiveData<>();
        for (int i = 0; i <=10;i++){
            loadPhotos(i);
        }
        return mPhotos;
    }

    private void loadPhotos(int page) {
        Disposable disposable = mRepository.getPhoto(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(reponse -> mPhotos.setValue(reponse),
                        error -> Log.d("taggg", error.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
