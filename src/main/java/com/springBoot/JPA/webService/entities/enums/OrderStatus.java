package com.springBoot.JPA.webService.entities.enums;

public enum OrderStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    
    // PARA PODER USAR OS VALORES ENUMERADOS NO ENUM //
    private int code;
    
    private OrderStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    // PARA CONVERTER VALOR NÚMERICO EM TIPO ENUMERADO
    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
