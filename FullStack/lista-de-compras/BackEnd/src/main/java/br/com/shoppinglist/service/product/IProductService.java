package br.com.shoppinglist.service.product;

import br.com.shoppinglist.service.product.request.RequestProduct;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IProductService {
    ResponseEntity<?> addProduct(RequestProduct requestProduct);

    ResponseEntity<?> getProducts();

    ResponseEntity<?> removeProduct(UUID id);

    ResponseEntity<?> editProduct(UUID id, RequestProduct requestProduct);
}
