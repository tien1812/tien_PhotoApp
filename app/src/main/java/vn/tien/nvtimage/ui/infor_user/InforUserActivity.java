package vn.tien.nvtimage.ui.infor_user;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.constant.Constant;
import vn.tien.nvtimage.constant.listeners.ListenerEvents;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.data.model.User;
import vn.tien.nvtimage.databinding.ActivityInforUserBinding;
import vn.tien.nvtimage.ui.detail.DetailPhotoActivity;
import vn.tien.nvtimage.ui.infor_user.fragment_likes.LikesOfUserFragment;
import vn.tien.nvtimage.ui.infor_user.fragment_photos.PhotoOfUserFragment;
import vn.tien.nvtimage.ui.main.ViewPagerAdapter;

public class InforUserActivity extends AppCompatActivity implements ListenerEvents {
    private ActivityInforUserBinding mBinding;
    private Photo mPhoto;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private PhotoOfUserFragment mPhotoOfUserFragment;
    private LikesOfUserFragment mLikesOfUserFragment;
    private ImageView mImageWeb, mImageLocat;
    private TextView mTextWeb, mTextLocation;
    private User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_infor_user);
        getData();
        initView();
        setUpViewPager();
        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setUpViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(mPhotoOfUserFragment, getString(R.string.title_photos));
        mViewPagerAdapter.addFragment(mLikesOfUserFragment, getString(R.string.likes));
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mToolbar = mBinding.toolBar;
        mTabLayout = mBinding.tabLayout;
        mViewPager = mBinding.viewPager;

        mPhotoOfUserFragment = new PhotoOfUserFragment();
        mPhotoOfUserFragment.setUser(mUser);
        mLikesOfUserFragment = new LikesOfUserFragment();
        mLikesOfUserFragment.setUser(mUser);

        mImageWeb = mBinding.imageWeb;
        mImageLocat = mBinding.imgLoca;
        if (mUser.getPortfolio() == null) {
            mImageWeb.setVisibility(View.GONE);
        }
        if (mUser.getLocation() == null) {
            mImageLocat.setVisibility(View.GONE);
        }
        mTextWeb = mBinding.textWeb;
        mTextWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(String.valueOf(mTextWeb.getText())));
                startActivity(intent);
            }
        });
        mTextLocation = mBinding.textLocal;
        mTextLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri location = Uri.parse("geo:0,0?q="
                        + Uri.encode(mUser.getLocation()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                startActivity(mapIntent);
            }
        });
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        mPhoto = bundle.getParcelable(Constant.KEY_BUNDLE_PHOTO);
        if (mPhoto == null) {
            mUser = bundle.getParcelable(Constant.KEY_BUNDLE_USER);
        } else {
            mUser = mPhoto.getUser();
        }
        mBinding.setUser(mUser);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, InforUserActivity.class);
        return intent;
    }

    @Override
    public void onClick(Photo photo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.KEY_BUNDLE_PHOTO, photo);
        Intent intent = DetailPhotoActivity.getIntent(this);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
