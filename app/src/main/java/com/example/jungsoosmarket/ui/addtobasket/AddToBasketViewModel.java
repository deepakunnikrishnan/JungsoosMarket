package com.example.jungsoosmarket.ui.addtobasket;

import androidx.lifecycle.ViewModel;

import com.example.jungsoosmarket.data.BasketRepository;
import com.example.jungsoosmarket.data.ProductsRepository;
import com.example.jungsoosmarket.data.local.entity.BasketItem;
import com.example.jungsoosmarket.data.local.entity.Product;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ViewModel class for AddToBasket feature.
 * This class interacts with the repository layer of the app codebase in providing the
 * data required for the View.
 *
 */
public class AddToBasketViewModel extends ViewModel {

    private final ProductsRepository productsRepository;
    private final BasketRepository basketRepository;

    public AddToBasketViewModel(ProductsRepository productsRepository, BasketRepository basketRepository){
        this.productsRepository = productsRepository;
        this.basketRepository = basketRepository;
    }

    /**
     * Method returns a Completable for the insertion of the product to the basket.
     * @param productId
     * @return
     */
    Completable insertProductToBasket(String productId){
        return this.productsRepository.getProduct(productId)
                .flatMapCompletable(product -> basketRepository.insert(createBasketItem(product)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     *  Creates the BasketItem object for the selected Product.
     * @param product
     * @return
     */
    private BasketItem createBasketItem(Product product){
        BasketItem basketItem = new BasketItem();
        basketItem.setProductId(product.getId());
        basketItem.setAmount(product.getPrice());
        return basketItem;
    }
}
