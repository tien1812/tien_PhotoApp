package vn.tien.nvtimage.ui.search.users;

import android.content.Context;
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
import vn.tien.nvtimage.data.model.User;
import vn.tien.nvtimage.databinding.FragmentRecyclerBinding;
import vn.tien.nvtimage.ui.adapter.UserAdapter;
import vn.tien.nvtimage.ui.infor_user.InforUserActivity;

public class ResultUsersFragment extends Fragment implements UserAdapter.ListenerClickUser {
    private FragmentRecyclerBinding mBinding;
    private RecyclerView mRecyclerView;
    private UserAdapter mUserAdapter;
    private ResultUserViewModel mUserViewModel;
    private ImageView mImageView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mUserViewModel = ViewModelProviders.of(this).get(ResultUserViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_recycler, container, false);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mUserAdapter = new UserAdapter();
        mRecyclerView.setAdapter(mUserAdapter);
    }


    public void resultUsers(String query) {
        mUserViewModel.initViewModel(getContext());
        mUserViewModel.
                getUsers(query).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mUserAdapter.setUsers(user.getUsers());
                if (user.getUsers().size() == 0){
                    mImageView.setVisibility(View.VISIBLE);
                }
                else mImageView.setVisibility(View.GONE);
            }
        });
        mUserAdapter.setClickUser(this);
    }

    @Override
    public void onClick(User user) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.KEY_BUNDLE_USER, user);
        Intent intent = InforUserActivity.getIntent(getContext());
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
