package com.britishbroadcast.mvpapiapp;

import android.app.Person;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.britishbroadcast.mvpapiapp.model.data.Result;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {


    private List<Result> people;

    public PersonAdapter(List<Result> people) {
        this.people = people;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_item_layout, parent, false);

        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

        Result person = people.get(position);

        Glide.with(holder.itemView)
                .setDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(person.getPicture().getLarge())
                .into(holder.profileImage);
        holder.nameTextView.setText(person.getName().getFirst() + " " + person.getName().getLast());
        holder.dobTextView.setText(person.getDob().getDate());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public void updatePeople(List<Result> people) {
        this.people = people;
        notifyDataSetChanged();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {

        public ImageView profileImage;
        public TextView nameTextView;
        public TextView dobTextView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_imageview);
            nameTextView = itemView.findViewById(R.id.person_name_textview);
            dobTextView = itemView.findViewById(R.id.person_dob_textview);
        }
    }
}
