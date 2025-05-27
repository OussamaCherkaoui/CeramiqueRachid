package com.CeramiqueRachid.exception;

public class PromotionNotFoundException extends RuntimeException {
    public PromotionNotFoundException() {
      super("Promotion not found !");
    }
}
