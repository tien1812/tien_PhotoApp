package vn.tien.nvtimage.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.data.model.Collection;
import vn.tien.nvtimage.databinding.ItemCollectionBinding;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionHolder> {
    private List<Collection> mCollections;
    private ItemCollectionBinding mCollectionBinding;
    private ListenerClickItem mListenerClickItem;

    public void setListenerClickItem(ListenerClickItem listenerClickItem) {
        mListenerClickItem = listenerClickItem;
    }

    public void setCollections(List<Collection> collections) {
        mCollections = collections;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CollectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mCollectionBinding = DataBindingUtil.inflate(inflater, R.layout.item_collection, parent,
                false);
        return new CollectionHolder(mCollectionBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionHolder holder, int position) {
        Collection collection = mCollections.get(position);
        holder.bind(collection);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListenerClickItem.onClick(collection);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCollections == null ? 0 : mCollections.size();
    }

    public class CollectionHolder extends RecyclerView.ViewHolder {
        private ItemCollectionBinding mCollectionBinding;

        public CollectionHolder(final ItemCollectionBinding collectionBinding) {
            super(collectionBinding.getRoot());
            mCollectionBinding = collectionBinding;
        }

        public void bind(Collection collection) {
            mCollectionBinding.setCollection(collection);
            mCollectionBinding.executePendingBindings();
        }
    }

    public interface ListenerClickItem{
        void onClick(Collection collection);
    }
}
