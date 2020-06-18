package vn.tien.nvtimage.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.data.model.Photo;
import vn.tien.nvtimage.databinding.ItemPhotoBinding;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    private List<Photo> mPhotos;
    private ItemPhotoBinding mPhotoBinding;

    public void setPhotos(List<Photo> photos) {
        mPhotos = photos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mPhotoBinding = DataBindingUtil.inflate(inflater, R.layout.item_photo, parent, false);
        return new PhotoHolder(mPhotoBinding);
    }


    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        Photo photo = mPhotos.get(position);
        holder.bind(photo);
    }

    @Override
    public int getItemCount() {
        return mPhotos == null ? 0 : mPhotos.size();
    }

    public class PhotoHolder extends RecyclerView.ViewHolder {
        private ItemPhotoBinding mPhotoBinding;

        public PhotoHolder(final ItemPhotoBinding itemPhotoBinding) {
            super(itemPhotoBinding.getRoot());
            mPhotoBinding = itemPhotoBinding;
        }

        public void bind(Photo photo) {
            mPhotoBinding.setPhoto(photo);
            mPhotoBinding.executePendingBindings();
        }
    }
}
