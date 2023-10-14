package br.com.shoppinglist.service.product;

import br.com.shoppinglist.domain.product.Product;
import br.com.shoppinglist.domain.product.TypeEnum;
import br.com.shoppinglist.repository.IProductRepository;
import br.com.shoppinglist.service.product.request.RequestProduct;
import br.com.shoppinglist.service.product.response.ResponseProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository repository;

    @Override
    public ResponseEntity<?> addProduct(RequestProduct requestProduct) {
        try {
            Product product = new Product(requestProduct.name(), requestProduct.amount(), requestProduct.type());
            repository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product add successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getProducts() {
        try {
            var listProducts = repository.findAll();
            List<ResponseProduct> listProductsNew = listProducts.stream()
                    .map(product -> {
                        return new ResponseProduct(
                                product.getId(),
                                product.getName(),
                                product.getAmount(),
                                TypeEnum.getCodeFromEnum(product.getType())
                        );
                    }).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(listProductsNew);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> removeProduct(UUID id) {
        try {
            repository.deleteById(id);
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted product");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> editProduct(UUID id, RequestProduct requestProduct) {
        try {
            Product product = repository.findById(id).orElse(null);
            if (product != null) {
                if(requestProduct.name() != null) {
                    product.setName(requestProduct.name());
                }

                if(requestProduct.amount() != null) {
                    product.setAmount(requestProduct.amount());
                }

                if(requestProduct.type() != null) {
                    product.setType(TypeEnum.getTypeEnum(requestProduct.type()));
                }

                repository.save(product);
                return ResponseEntity.status(HttpStatus.OK).body("Product edited successfully");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
