package com.ozan.be.product;

import com.ozan.be.review.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(Integer id){
        return productRepository.findProductById(id).orElse(null);
    }

    public Product updateProductRating(Integer productID, Review review){
        Product product = getProduct(productID);
        Double rating = product.getRating();
        Integer numberOfRating = product.getNumberOfRating();
        Double newRating = ( rating * numberOfRating + review.getRating() ) / (numberOfRating+1);

        product.setRating(newRating);
        product.setNumberOfRating(numberOfRating+1);

        return productRepository.save(product);
    }
}
