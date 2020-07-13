package vn.tien.nvtimage.ui.infor_user.fragment_collections;

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

import java.util.List;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.constant.Constant;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.data.model.User;
import vn.tien.nvtimage.databinding.FragmentRecyclerBinding;
import vn.tien.nvtimage.ui.adapter.CollectionAdapter;
import vn.tien.nvtimage.ui.detailcollection.DetailCollectionActivity;

public class CollectionOfUserFragment extends Fragment {
    private FragmentRecyclerBinding mBinding;
    private RecyclerView mRecyclerView;
    private CollectionAdapter mAdapter;
    private CollectionsUserViewModel mViewModel;
    private User mUser;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

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
        mRecyclerView = mBinding.recycleItems;
        mImageView = mBinding.imageNull;
        mProgressBar = mBinding.progressBar;
        mAdapter = new CollectionAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewModel = ViewModelProviders.of(this).get(CollectionsUserViewModel.class);
        mViewModel.initViewModel(getContext());
        mViewModel.getCollections(mUser.getUsername()).observe(this, new Observer<List<Collection>>() {
            @Override
            public void onChanged(List<Collection> collections) {
                mProgressBar.setVisibility(View.GONE);
                mAdapter.setCollections(collections);
                if (collections.size() == 0){
                    mImageView.setVisibility(View.VISIBLE);
                }
            }
        });
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
        mRecyclerView.setAdapter(mAdapter);
    }
}
