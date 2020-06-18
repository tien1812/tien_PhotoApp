package vn.tien.nvtimage.ui.home;

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
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.databinding.FragmentHomeBinding;
import vn.tien.nvtimage.ui.adapter.PhotoAdapter;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding mBinding;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
    private HomeViewModel mHomeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mPhotoAdapter = new PhotoAdapter();
        mRecyclerView = mBinding.recycleImage;
        mHomeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        mHomeViewModel.initViewModel(getContext());
        mHomeViewModel.getPhotos().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                mPhotoAdapter.setPhotos(photos);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPhotoAdapter);
    }

    public static Fragment getInstance() {
        return new HomeFragment();
    }
}
