package vn.tien.nvtimage.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.databinding.ActivitySearchBinding;
import vn.tien.nvtimage.ui.main.ViewPagerAdapter;
import vn.tien.nvtimage.ui.search.collections.ResultCollectionsFragment;
import vn.tien.nvtimage.ui.search.photos.ResultPhotosFragment;
import vn.tien.nvtimage.ui.search.users.ResultUsersFragment;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private ActivitySearchBinding mBinding;
    private Toolbar mToolbar;
    private SearchView mSearchView;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private TabLayout mTabLayout;
    private ResultPhotosFragment mPhotosFragment;
    private ResultCollectionsFragment mCollectionsFragment;
    private ResultUsersFragment mUsersFragment ;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        initView();
        setUpViewPager();
        setUpSearchView();
        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setUpSearchView() {
        mSearchView.setOnQueryTextListener(this);
    }

    private void setUpViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(mPhotosFragment,
                getString(R.string.title_photos));
        mViewPagerAdapter.addFragment(mCollectionsFragment,
                getString(R.string.collection_title));
        mViewPagerAdapter.addFragment(mUsersFragment,
                getString(R.string.title_users));
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
    }

    private void initView() {
        mToolbar = mBinding.toolBar;
        mSearchView = mBinding.searchBar;
        mTabLayout = mBinding.tabLayout;
        mViewPager = mBinding.viewPager;
        mPhotosFragment = new ResultPhotosFragment();
        mCollectionsFragment = new ResultCollectionsFragment();
        mUsersFragment = new ResultUsersFragment();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mPhotosFragment.resultPhotos(query);
        mCollectionsFragment.resultCollections(query);
        mUsersFragment.resultUsers(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}
