package br.com.shoppinglist.service.product.response;

import java.util.UUID;

public record ResponseProduct(UUID id, String name, Float amount, String type) {
}
