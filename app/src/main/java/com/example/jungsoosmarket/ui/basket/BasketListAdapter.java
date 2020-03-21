package com.example.jungsoosmarket.ui.basket;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.jungsoosmarket.R;
import com.example.jungsoosmarket.data.local.entity.BasketItemWithProduct;
import com.example.jungsoosmarket.databinding.ItemVirtualBasketBinding;
import com.example.jungsoosmarket.ui.common.recyclerviewhelper.BaseSwipeListAdapter;

import io.reactivex.subjects.PublishSubject;

/**
 * Adapter class for displaying the list of items in the basket.
 * This class extends a ListAdapter and has an implementation of  BaseSwipeListAdapter.
 * With ListAdapter extension, this can perform the DiffUtil.ItemCallback execution to
 * update the UI based on the data changes.
 * With the BaseSwipeListAdapter implementation, the list has option to listen for Swipe events
 * and allow the user to delete the item by swiping.
 */
public class BasketListAdapter extends ListAdapter<BasketItemWithProduct,BasketItemViewHolder> implements BaseSwipeListAdapter {

    protected BasketListAdapter() {
        super(DIFF_CALLBACK);
    }

    //A publishSubject to listen for delete events
    PublishSubject<BasketItemWithProduct> deleteItemSubject = PublishSubject.create();


    /**
     *  This is an implementation of the DiffUtil.ItemCallback.
     *  This implementation helps the ListAdapter to identify if the objects are same or not.
     *  In this case, I consider the object & content to be same if basketItemId are same.
     */
    private static DiffUtil.ItemCallback<BasketItemWithProduct> DIFF_CALLBACK = new DiffUtil.ItemCallback<BasketItemWithProduct>() {
        @Override
        public boolean areItemsTheSame(@NonNull BasketItemWithProduct oldItem, @NonNull BasketItemWithProduct newItem) {
            return oldItem.basketItem.getBasketItemId() == newItem.basketItem.getBasketItemId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull BasketItemWithProduct oldItem, @NonNull BasketItemWithProduct newItem) {
            return oldItem.basketItem.getBasketItemId() == newItem.basketItem.getBasketItemId();
        }
    };

    @NonNull
    @Override
    public BasketItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVirtualBasketBinding basketBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_virtual_basket,parent,false);
        return new BasketItemViewHolder(basketBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    /**
     *
     * @param position
     */
    @Override
    public void deleteItem(int position) {
        deleteItemSubject.onNext(getItem(position));
    }
}
