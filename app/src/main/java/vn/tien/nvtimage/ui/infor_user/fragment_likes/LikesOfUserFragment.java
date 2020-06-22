package vn.tien.nvtimage.ui.infor_user.fragment_likes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.constant.listeners.ListenerEvents;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.data.model.User;
import vn.tien.nvtimage.databinding.FragmentRecyclerBinding;
import vn.tien.nvtimage.ui.adapter.PhotoAdapter;

public class LikesOfUserFragment extends Fragment {
    private FragmentRecyclerBinding mBinding;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
    private LikesViewModel mViewModel;
    private User mUser;
    private ListenerEvents mListenerEvents;
    private ImageView mImageView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListenerEvents = (ListenerEvents) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_recycler,container,false);
        return mBinding.getRoot();
    }

    public void setUser(User user) {
        mUser = user;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
    }

    private void initview() {
        mImageView = mBinding.imageNull;
        mRecyclerView = mBinding.recycleItems;
        mPhotoAdapter = new PhotoAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel = ViewModelProviders.of(this).get(LikesViewModel.class);
        mViewModel.initViewModel(getContext());
        mViewModel.getPhotos(mUser.getUsername()).observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                mPhotoAdapter.setPhotos(photos);
                if (photos.size() == 0){
                    mImageView.setVisibility(View.VISIBLE);
                }
            }
        });
        mPhotoAdapter.setOnClickItem(mListenerEvents);
        mRecyclerView.setAdapter(mPhotoAdapter);
    }
}
