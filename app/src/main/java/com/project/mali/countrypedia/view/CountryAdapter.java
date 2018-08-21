package com.project.mali.countrypedia.view;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.project.mali.countrypedia.R;
import com.project.mali.countrypedia.data.local.entity.CountryEntity;
import com.project.mali.countrypedia.util.glidesvg.SvgSoftwareLayerSetter;
import java.util.ArrayList;
import java.util.List;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context context;
    private OnCountryClick onCountryClick;
    private List<CountryEntity> countryEntitiesList = new ArrayList<>();
    private RequestBuilder<PictureDrawable> requestBuilder;

    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        return new CountryViewHolder(v);
    }

    public CountryAdapter(Context context, List<CountryEntity> countryEntityList, OnCountryClick onCountryClick) {
        this.context = context;
        this.onCountryClick = onCountryClick;
        this.countryEntitiesList = countryEntityList;
    }

    @Override
    public void onBindViewHolder(final CountryViewHolder holder, final int position) {
        final CountryEntity item = countryEntitiesList.get(position);
        if (item.getName() != null) {
            holder.countryName.setText(item.getName());
            holder.countryCapital.setText(item.getCapital());

            requestBuilder = Glide.with(context)
                    .as(PictureDrawable.class)
                    .listener(new SvgSoftwareLayerSetter());

            Uri uri = Uri.parse(item.getFlag());
            requestBuilder.load(uri)
                    .into(holder.imageView);


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onCountryClick.onClick(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return countryEntitiesList.size();

    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {
        private TextView countryName;
        private TextView countryCapital;
        private ImageView imageView;


        public CountryViewHolder(View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.countryList_name);
            countryCapital = itemView.findViewById(R.id.countryList_capital);
            imageView = itemView.findViewById(R.id.countryList_image);

        }
    }

    public interface OnCountryClick {
        void onClick(int position);
    }
}
