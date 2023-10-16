package br.com.shoppinglist.domain.product;

import java.util.HashMap;
import java.util.Map;

public enum TypeEnum {
    QUILOGRAMA("kg"),
    GRAMA("g"),
    LITRO("L"),
    MILILITRO("mL"),
    UNIDADE("Un");

    private String code;
    private static final Map<String, TypeEnum> getType = new HashMap<>();

    TypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    static {
        for (TypeEnum type: TypeEnum.values()) {
            getType.put(type.getCode(), type);
        }
    }

    public static TypeEnum getTypeEnum(String code) {
        return getType.get(code);
    }

    public static String getCodeFromEnum(TypeEnum type) {
        return type.getCode();
    }
}
