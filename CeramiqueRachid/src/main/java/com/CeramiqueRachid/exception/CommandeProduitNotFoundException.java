package com.CeramiqueRachid.exception;

public class CommandeProduitNotFoundException extends RuntimeException {
    public CommandeProduitNotFoundException() {
      super("Commande Produit not found !");
    }
}
