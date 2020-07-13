package vn.tien.nvtimage.ui.search.photos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.constant.Constant;
import vn.tien.nvtimage.constant.listeners.ListenerEvents;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.databinding.FragmentRecyclerBinding;
import vn.tien.nvtimage.ui.adapter.PhotoAdapter;
import vn.tien.nvtimage.ui.detailphoto.DetailPhotoActivity;

public class ResultPhotosFragment extends Fragment implements ListenerEvents {
    private FragmentRecyclerBinding mBinding;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
    private ResultPhotosModel mResultPhotosModel;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler,
                container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mResultPhotosModel = ViewModelProviders.of(this).get(ResultPhotosModel.class);
    }

    private void initView() {
        mImageView = mBinding.imageNull;
        mRecyclerView = mBinding.recycleItems;
        mProgressBar = mBinding.progressBar;
        mPhotoAdapter = new PhotoAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPhotoAdapter);
        mPhotoAdapter.setOnClickItem(this);
        mProgressBar.setVisibility(View.GONE);
    }

    public void resultPhotos(String query){
        mResultPhotosModel.initViewModel(getContext());
        mResultPhotosModel.
                getPhotos(query).observe(this, new Observer<Photo>() {
            @Override
            public void onChanged(Photo photo) {
                mPhotoAdapter.setPhotos(photo.getPhotos());
                if (photo.getPhotos().size() ==0){
                    mImageView.setVisibility(View.VISIBLE);
                }else mImageView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(Photo photo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.KEY_BUNDLE_PHOTO, photo);
        Intent intent = DetailPhotoActivity.getIntent(getContext());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
