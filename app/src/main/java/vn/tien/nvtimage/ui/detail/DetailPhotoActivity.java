package vn.tien.nvtimage.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.constant.Constant;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.databinding.ActivityDetailPhotoBinding;

public class DetailPhotoActivity extends AppCompatActivity {
    private ActivityDetailPhotoBinding mBinding;
    private Photo mPhoto;
    private ImageView mImageColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail_photo);
        getData();
        initview();
        mBinding.setPhoto(mPhoto);
    }

    private void initview() {
        mImageColor = mBinding.imgColor;
        mImageColor.setBackgroundColor(Color.parseColor(mPhoto.getColor()));
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        mPhoto = bundle.getParcelable(Constant.KEY_BUNDLE_PHOTO);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context,DetailPhotoActivity.class);
        return intent;
    }
}
