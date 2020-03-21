package com.example.jungsoosmarket.ui.basket;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jungsoosmarket.R;
import com.example.jungsoosmarket.data.local.entity.BasketItemWithProduct;
import com.example.jungsoosmarket.databinding.ItemVirtualBasketBinding;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;

/**
 * ViewHolder class for the item displayed in the basket.
 */
public class BasketItemViewHolder extends RecyclerView.ViewHolder {


    private ItemVirtualBasketBinding binding;


    public BasketItemViewHolder(@NonNull ItemVirtualBasketBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * Sets the value for each of the views in the item layout.
     * @param basketItem
     */
    public void bind(BasketItemWithProduct basketItem){
        binding.textViewName.setText(basketItem.product.getName());
        binding.textViewAmount.setText(basketItem.basketItem.getAmount());

        Picasso picasso = getPicassoOkHttp(binding.imageViewItem.getContext());
        if(null != picasso){
            picasso.load(basketItem.product.getThumbnail())
                    .placeholder(R.drawable.ic_product)
                    .error(R.drawable.ic_product)
                    .into(binding.imageViewItem);
        }
    }

    /**
     * Method creates an instance of the Picasso object.
     * @param context
     * @return
     */
    private Picasso getPicassoOkHttp(Context context){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(httpClient.build()))
                .listener((picasso, uri, exception) -> Log.e("Picasso","Unable to load image"))
                .indicatorsEnabled(false)
                .build();
    }
}
