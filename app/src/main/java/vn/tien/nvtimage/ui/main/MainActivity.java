package vn.tien.nvtimage.ui.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.constant.Constant;
import vn.tien.nvtimage.constant.listeners.ListenerEvents;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.databinding.ActivityMainBinding;
import vn.tien.nvtimage.ui.collection.CollectionFragment;
import vn.tien.nvtimage.ui.detailphoto.DetailPhotoActivity;
import vn.tien.nvtimage.ui.home.HomeFragment;
import vn.tien.nvtimage.ui.search.SearchActivity;

public class MainActivity extends AppCompatActivity implements ListenerEvents {
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
        checkPermission();
    }

    private void setUpViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(HomeFragment.getInstance(),
                getString(R.string.home_title));
        mViewPagerAdapter.addFragment(CollectionFragment.getInstance(),
                getString(R.string.collection_title));
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
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
        switch (item.getItemId()) {
            case R.id.item_search:
                Intent intent = SearchActivity.getIntent(this);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn muốn thoát ứng dụng không?");
        builder.setPositiveButton(Html.fromHtml("<font color='#050407'>Có</font>"),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        builder.setNegativeButton(Html.fromHtml("<font color='#050407'>Không</font>"),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        Constant.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }
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
