package br.com.shoppinglist.domain.product;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeEnum type;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public Product() {}

    public Product(String name, Float amount, String type) {
        this.name = name;
        this.amount = amount;
        this.type = TypeEnum.getTypeEnum(type);
    }
}
