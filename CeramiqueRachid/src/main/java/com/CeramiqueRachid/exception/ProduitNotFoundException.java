package com.CeramiqueRachid.exception;

public class ProduitNotFoundException extends RuntimeException {
    public ProduitNotFoundException() {
      super("Produit not found !");
    }
}
