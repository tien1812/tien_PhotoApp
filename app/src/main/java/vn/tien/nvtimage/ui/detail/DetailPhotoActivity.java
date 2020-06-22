package vn.tien.nvtimage.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_photo);
        getData();
        initview();
        mBinding.setPhoto(mPhoto);
    }

    private void initview() {
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
            default:
                break;
        }
    }

    private void startInforActivity() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.KEY_BUNDLE_PHOTO, mPhoto);
        Intent intent = InforUserActivity.getIntent(this);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
