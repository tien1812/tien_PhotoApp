package vn.tien.nvtimage.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import vn.tien.nvtimage.databinding.FragmentRecyclerBinding;
import vn.tien.nvtimage.ui.adapter.PhotoAdapter;

public class HomeFragment extends Fragment {
    private FragmentRecyclerBinding mBinding;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
    private HomeViewModel mHomeViewModel;
    private int mPageCurrent = 1;
    private LinearLayoutManager mLayoutManager;
    private ListenerEvents mListenerEvents;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListenerEvents = (ListenerEvents) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler, container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initViewModel(mPageCurrent);
    }

    private void initViewModel(int page) {
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mHomeViewModel.initViewModel(getContext());
        mHomeViewModel.getPhotos().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                mPhotoAdapter.setPhotos(photos);
            }
        });
    }

    private void initView() {
        mPhotoAdapter = new PhotoAdapter();
        mRecyclerView = mBinding.recycleItems;
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mPhotoAdapter);
        mPhotoAdapter.setOnClickItem(mListenerEvents);
    }

    public static Fragment getInstance() {
        return new HomeFragment();
    }
}
