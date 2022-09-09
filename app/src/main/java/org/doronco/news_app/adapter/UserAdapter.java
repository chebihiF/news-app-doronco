package org.doronco.news_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.doronco.news_app.R;
import org.doronco.news_app.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_layout,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.showUser(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView name;
        TextView email;
        TextView lat;
        TextView lng;
        ImageView img;
        User user ;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            lat = itemView.findViewById(R.id.lat);
            lng = itemView.findViewById(R.id.lng);
            img = itemView.findViewById(R.id.img1);
        }

        void showUser(User user){
            this.user = user ;
            username.setText(user.getUsername());
            name.setText(user.getName());
            email.setText(user.getEmail());
            lat.setText(user.getLat());
            lng.setText(user.getLng());
        }

        public User getUser() {
            return user;
        }
    }
}
