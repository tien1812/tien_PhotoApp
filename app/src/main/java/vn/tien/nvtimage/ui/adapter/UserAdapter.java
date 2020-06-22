package vn.tien.nvtimage.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.tien.nvtimage.R;
import vn.tien.nvtimage.data.model.User;
import vn.tien.nvtimage.databinding.ItemUsersBinding;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> mUsers;
    private ItemUsersBinding mUsersBinding;
    private ListenerClickUser mClickUser;

    public void setUsers(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    public void setClickUser(ListenerClickUser clickUser) {
        mClickUser = clickUser;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        mUsersBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_users,
                parent, false);
        return new UserViewHolder(mUsersBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.bind(user);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickUser.onClick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers == null ? 0 : mUsers.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private ItemUsersBinding mUsersBinding;

        public UserViewHolder(final ItemUsersBinding usersBinding) {
            super(usersBinding.getRoot());
            mUsersBinding = usersBinding;
        }

        public void bind(User user) {
            mUsersBinding.setUser(user);
            mUsersBinding.executePendingBindings();
        }
    }

    public interface ListenerClickUser {
        void onClick(User user);
    }
}
