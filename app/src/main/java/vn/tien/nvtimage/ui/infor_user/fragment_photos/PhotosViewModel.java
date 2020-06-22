package vn.tien.nvtimage.ui.infor_user.fragment_photos;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.data.repository.PhotoRepository;

public class PhotosViewModel extends ViewModel {
    private MutableLiveData<List<Photo>> mPhotos;
    private PhotoRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public void initViewModel(Context context){
        mRepository = PhotoRepository.getInstance(context);
        mPhotos = new MutableLiveData<>();
        mCompositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<List<Photo>> getPhotos(String username){
        loadData(username);
        return mPhotos;
    }

    private void loadData(String username) {
        Disposable disposable = mRepository.getPhotoOfUser(username,1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(reponse ->mPhotos.setValue(reponse));
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
