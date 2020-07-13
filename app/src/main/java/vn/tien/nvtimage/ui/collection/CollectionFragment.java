package vn.tien.nvtimage.ui.collection;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
import vn.tien.nvtimage.constant.Constant;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.databinding.FragmentRecyclerBinding;
import vn.tien.nvtimage.ui.adapter.CollectionAdapter;
import vn.tien.nvtimage.ui.detailcollection.DetailCollectionActivity;

public class CollectionFragment extends Fragment {
    private FragmentRecyclerBinding mBinding;
    private RecyclerView mRecyclerView;
    private CollectionAdapter mAdapter;
    private CollectionViewModel mViewModel;
    private ProgressBar mProgressBar;

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
        initViewModel();
        initView();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(CollectionViewModel.class);
        mViewModel.initViewModel(getContext());
        mViewModel.getCollections(1).observe(this, new Observer<List<Collection>>() {
            @Override
            public void onChanged(List<Collection> collections) {
                mAdapter.setCollections(collections);
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        mRecyclerView = mBinding.recycleItems;
        mAdapter = new CollectionAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setListenerClickItem(new CollectionAdapter.ListenerClickItem() {
            @Override
            public void onClick(Collection collection) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.KEY_BUNDLE_COLLEC, collection);
                Intent intent = DetailCollectionActivity.getIntent(getContext());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mProgressBar = mBinding.progressBar;
    }

    public static CollectionFragment getInstance() {
        return new CollectionFragment();
    }
}
