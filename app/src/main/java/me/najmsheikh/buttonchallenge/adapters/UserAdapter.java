package me.najmsheikh.buttonchallenge.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import me.najmsheikh.buttonchallenge.R;
import me.najmsheikh.buttonchallenge.models.User;

/**
 * Created by Najm Sheikh <hello@najmsheikh.me> on 3/5/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private OnUserClickListener mListener;
    private List<User> mUserList;

    // Listener interface to abstract away certain click/touch events
    public interface OnUserClickListener {
        void onUserSelected(User user);

        void onUserDeleted(User user);
    }

    public UserAdapter(OnUserClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_userlist, parent, false);
        return new UserViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        holder.bind(mUserList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mUserList != null ? mUserList.size() : 0;
    }

    public void setList(List<User> users) {
        mUserList = users;
        notifyDataSetChanged();
    }

    public void addUser(User user) {
        mUserList.add(user);
        notifyDataSetChanged();
    }

    public void deleteUser(User user) {
        mUserList.remove(user);
        notifyDataSetChanged();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnUserClickListener mListener;
        User mUser;

        TextView mUserId;
        TextView mUserName;
        TextView mUserEmail;
        ImageButton mDeleteButton;

        UserViewHolder(View itemView) {
            super(itemView);
            mUserId = itemView.findViewById(R.id.tv_user_id);
            mUserName = itemView.findViewById(R.id.et_user_name);
            mUserEmail = itemView.findViewById(R.id.et_user_email);
            mDeleteButton = itemView.findViewById(R.id.ib_user_delete);
        }

        void bind(final User user, OnUserClickListener listener) {
            mListener = listener;
            mUser = user;

            mUserId.setText(String.valueOf(user.getId()));
            mUserName.setText(user.getName());
            mUserEmail.setText(user.getEmail());

            mDeleteButton.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == itemView.getId()) {
                mListener.onUserSelected(mUser);
                return;
            }

            if (v.getId() == mDeleteButton.getId()) {
                mListener.onUserDeleted(mUser);
                return;
            }
        }
    }
}
