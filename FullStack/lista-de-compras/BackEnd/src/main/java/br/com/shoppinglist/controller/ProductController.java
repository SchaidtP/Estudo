package br.com.shoppinglist.controller;

import br.com.shoppinglist.service.product.IProductService;
import br.com.shoppinglist.service.product.request.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @PostMapping()
    public ResponseEntity<?> addProduct(@RequestBody RequestProduct requestProduct){
        return service.addProduct(requestProduct);
    }

    @GetMapping()
    public ResponseEntity<?> getProducts() {
        return service.getProducts();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable("id") UUID id) {
        return service.removeProduct(id);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> editProduct(@PathVariable("id") UUID id, @RequestBody RequestProduct requestProduct) {
        return service.editProduct(id, requestProduct);
    }
}
