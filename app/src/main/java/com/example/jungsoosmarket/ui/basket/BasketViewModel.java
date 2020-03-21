package com.example.jungsoosmarket.ui.basket;

import androidx.lifecycle.ViewModel;

import com.example.jungsoosmarket.data.BasketRepository;
import com.example.jungsoosmarket.data.ProductsRepository;
import com.example.jungsoosmarket.data.local.entity.BasketItem;
import com.example.jungsoosmarket.data.local.entity.BasketItemWithProduct;
import com.example.jungsoosmarket.ui.common.Formatter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * The ViewModel for the BasketFragment.
 * This viewmodel interacts with the BasketRepository to provide
 * the items as well as the total of the basket
 */
public class BasketViewModel extends ViewModel {

    private final ProductsRepository productsRepository;
    private final BasketRepository basketRepository;


    public BasketViewModel(ProductsRepository productsRepository, BasketRepository basketRepository){
        this.productsRepository = productsRepository;
        this.basketRepository = basketRepository;
    }

    /**
     * Method provides a Flowable that provides the updated list of items in the basket.
     * @return Flowable<List<BasketItemWithProduct>>
     */
    public Flowable<List<BasketItemWithProduct>> getItemsInBasket(){
        return this.basketRepository.getBasket();
    }

    /**
     * Method provides a Complete which will execute its onComplete when the item is deleted
     * from the basket.
     * @param basketItem
     * @return Completable
     */
    public Completable deleteItemFromBasket(BasketItem basketItem){
        return this.basketRepository.delete(basketItem);
    }

    /**
     * Method provides a Flowable which provides with the total of the basket.
     * When an item is added or deleted from the basket, the Flowable will emit the new total
     * value.
     * @return Flowable<String>
     */
    public Flowable<String> getBasketTotal(){
        return this.basketRepository.getBasketTotal()
                .observeOn(Schedulers.io())
                .map(Formatter::format);
    }
}
