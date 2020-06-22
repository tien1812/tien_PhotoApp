package vn.tien.nvtimage.ui.search.users;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import vn.tien.nvtimage.data.model.User;
import vn.tien.nvtimage.data.repository.PhotoRepository;

public class ResultUserViewModel extends ViewModel {
    private MutableLiveData<User> mUsers;
    private PhotoRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public void initViewModel(Context context) {
        mCompositeDisposable = new CompositeDisposable();
        mRepository = PhotoRepository.getInstance(context);
    }

    public MutableLiveData<User> getUsers(String query) {
        mUsers = new MutableLiveData<>();
        loadData(query);
        return mUsers;
    }

    private void loadData(String query) {
        Disposable disposable = mRepository.searchUser(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(reponse -> mUsers.setValue(reponse),
                        error -> Log.d("tag", error.getMessage()));
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
