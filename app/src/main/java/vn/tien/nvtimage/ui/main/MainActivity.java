package vn.tien.nvtimage.ui.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.databinding.ActivityMainBinding;
import vn.tien.nvtimage.ui.collection.CollectionFragment;
import vn.tien.nvtimage.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private TabLayout mTabLayout;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
        setUpToolbar();
        setUpViewPager();
    }

    private void setUpViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(HomeFragment.getInstance(), getString(R.string.home_title));
        mViewPagerAdapter.addFragment(CollectionFragment.getInstance(), getString(R.string.collection_title));
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
    }

    private void initView() {
        mToolbar = mBinding.toolBarMain;
        mViewPager = mBinding.viewPager;
        mTabLayout = mBinding.tabLayout;
        mDrawerLayout = mBinding.drawerLayout;
        mNavigationView = mBinding.navView;
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_home:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.item_collection:
                        mDrawerLayout.closeDrawers();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}
