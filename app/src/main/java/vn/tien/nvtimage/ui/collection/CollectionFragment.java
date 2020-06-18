package vn.tien.nvtimage.ui.collection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.databinding.FragmentCollectionBinding;

public class CollectionFragment extends Fragment {
    private FragmentCollectionBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_collection, container,
                false);
        return mBinding.getRoot();
    }

    public static CollectionFragment getInstance() {
        return new CollectionFragment();
    }
}
