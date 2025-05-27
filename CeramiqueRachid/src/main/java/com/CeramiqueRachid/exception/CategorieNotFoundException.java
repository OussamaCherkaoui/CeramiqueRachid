package com.CeramiqueRachid.exception;

public class CategorieNotFoundException extends RuntimeException {
    public CategorieNotFoundException() {
      super("Categorie not found !");
    }
}
