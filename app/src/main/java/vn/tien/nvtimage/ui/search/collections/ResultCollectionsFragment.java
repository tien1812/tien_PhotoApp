package vn.tien.nvtimage.ui.search.collections;

import android.content.Intent;
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

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.constant.Constant;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.databinding.FragmentRecyclerBinding;
import vn.tien.nvtimage.ui.adapter.CollectionAdapter;
import vn.tien.nvtimage.ui.detailcollection.DetailCollectionActivity;

public class ResultCollectionsFragment extends Fragment implements CollectionAdapter.ListenerClickItem {
    private FragmentRecyclerBinding mBinding;
    private RecyclerView mRecyclerView;
    private CollectionAdapter mCollectionAdapter;
    private ResultCollectionViewModel mCollectionViewModel;
    private ImageView mImageView;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mImageView = mBinding.imageNull;
        mRecyclerView = mBinding.recycleItems;
        mCollectionAdapter = new CollectionAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCollectionAdapter);
    }

    public void resultCollections(String query) {
        mCollectionViewModel = ViewModelProviders.of(this)
                .get(ResultCollectionViewModel.class);
        mCollectionViewModel.initViewModel(getContext());
        mCollectionViewModel.getCollections(query).observe(this, new Observer<Collection>() {
            @Override
            public void onChanged(Collection collection) {
                mCollectionAdapter.setCollections(collection.getCollections());
                if (collection.getCollections().size() == 0){
                    mImageView.setVisibility(View.VISIBLE);
                }else mImageView.setVisibility(View.GONE);
            }
        });
        mCollectionAdapter.setListenerClickItem(this);
    }

    @Override
    public void onClick(Collection collection) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.KEY_BUNDLE_COLLEC, collection);
        Intent intent = DetailCollectionActivity.getIntent(getContext());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
