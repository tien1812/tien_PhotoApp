package vn.tien.nvtimage.ui.detailcollection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.constant.Constant;
import vn.tien.nvtimage.constant.listeners.ListenerEvents;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.databinding.ActivityDetailCollectionBinding;
import vn.tien.nvtimage.ui.adapter.PhotoAdapter;
import vn.tien.nvtimage.ui.detailphoto.DetailPhotoActivity;
import vn.tien.nvtimage.ui.infor_user.InforUserActivity;

public class DetailCollectionActivity extends AppCompatActivity implements ListenerEvents, View.OnClickListener {
    private ActivityDetailCollectionBinding mBinding;
    private RecyclerView mRecyclerView;
    private DetailColViewModel mViewModel;
    private PhotoAdapter mPhotoAdapter;
    private Collection mCollection;
    private Toolbar mToolbar;
    private ImageView mImageAva;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_collection);
        getBundle();
        initView();
        initViewModel();
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        mCollection = bundle.getParcelable(Constant.KEY_BUNDLE_COLLEC);
        mBinding.setCollection(mCollection);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, DetailCollectionActivity.class);
        return intent;
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(DetailColViewModel.class);
        mViewModel.initViewModel(this);
        mViewModel.getData(mCollection.getId(), 1).observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                mPhotoAdapter.setPhotos(photos);
            }
        });
    }

    private void initView() {
        mToolbar = mBinding.toolBar;
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mRecyclerView = mBinding.recycleItems;
        mPhotoAdapter = new PhotoAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mPhotoAdapter);
        mPhotoAdapter.setOnClickItem(this);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mImageAva = mBinding.imgAvatar;
        mImageAva.setOnClickListener(this);
    }

    @Override
    public void onClick(Photo photo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.KEY_BUNDLE_PHOTO, photo);
        Intent intent = DetailPhotoActivity.getIntent(this);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_avatar:
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.KEY_BUNDLE_USER,mCollection.getUser());
                Intent intent = InforUserActivity.getIntent(this);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
