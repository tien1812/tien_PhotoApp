package vn.tien.nvtimage.ui.detail;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.constant.Constant;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.databinding.ActivityDetailPhotoBinding;
import vn.tien.nvtimage.ui.infor_user.InforUserActivity;

public class DetailPhotoActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityDetailPhotoBinding mBinding;
    private Photo mPhoto;
    private ImageView mImageColor;
    private LinearLayout mLayoutUser;
    private TextView mTextDes;
    private Toolbar mToolbar;
    private FloatingActionButton mFabShow, mFabDown, mFabSetWall;
    private boolean mShow;
    private Bitmap mBitmap;
    private ImageView mImage;
    private DownloadManager mDownloadManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_photo);
        getData();
        initview();
        setBitmap();
        mBinding.setPhoto(mPhoto);
        mDownloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
    }

    private void setBitmap() {
        new SwitchToBitmap(mImage).execute(mPhoto.getUrl().getRegular());
    }

    private void initview() {
        mImage = mBinding.photo;
        mTextDes = mBinding.textDes;
        mImageColor = mBinding.imgColor;
        mImageColor.setBackgroundColor(Color.parseColor(mPhoto.getColor()));
        mLayoutUser = mBinding.layoutUser;
        mLayoutUser.setOnClickListener(this);
        if (mPhoto.getDescription() == null) {
            mTextDes.setVisibility(View.GONE);
        }
        mToolbar = mBinding.toolBar;
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mFabDown = mBinding.fab.fabDown;
        mFabShow = mBinding.fabShow;
        mFabSetWall = mBinding.fab.fabWall;

        mFabShow.setOnClickListener(this);
        mFabDown.setOnClickListener(this);
        mFabSetWall.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
                sharePhoto();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sharePhoto() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, mPhoto.getLink().getHtml());
        startActivity(Intent.createChooser(intent, "Share Image via"));
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        mPhoto = bundle.getParcelable(Constant.KEY_BUNDLE_PHOTO);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, DetailPhotoActivity.class);
        return intent;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_user:
                startInforActivity();
                break;
            case R.id.fab_show:
                showFab();
                break;
            case R.id.fab_down:
                downPhoto();
                break;
            case R.id.fab_wall:
                setWallpaper();
                break;
            default:
                break;
        }
    }

    private void setWallpaper() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(DetailPhotoActivity.this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;
        try {
            if (mBitmap != null) {
                wallpaperManager.setBitmap(mBitmap);
                Toast.makeText(this, "Set Wallpaper Success", Toast.LENGTH_SHORT).show();
                wallpaperManager.suggestDesiredDimensions(w, h);
            } else {
                Toast.makeText(this, "Set Wallpaper Failed", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void downPhoto() {
        DownloadManager.Request request =
                new DownloadManager.Request(Uri.parse(mPhoto.getUrl().getFull()));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Tiến Photo Download"); //set title download notification
        request.setDescription("Downloading Image ...");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                 ".png");
        mDownloadManager.enqueue(request);
    }

    private void showFab() {
        if (mShow == false) {
            mFabSetWall.show();
            mFabDown.show();
            mShow = true;
        } else {
            mFabSetWall.hide();
            mFabDown.hide();
            mShow = false;
        }

    }

    private void startInforActivity() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.KEY_BUNDLE_PHOTO, mPhoto);
        Intent intent = InforUserActivity.getIntent(this);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public class SwitchToBitmap extends AsyncTask<String, Void, Bitmap> {
        private ImageView mImageView;

        public SwitchToBitmap(ImageView imageView) {
            mImageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay = url[0];
            mBitmap = null;
            try {
                InputStream str = new java.net.URL(urldisplay).openStream();
                mBitmap = BitmapFactory.decodeStream(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mImageView.setImageBitmap(bitmap);
        }
    }

}
