package com.CeramiqueRachid.exception;

public class CommandeNotFoundException extends RuntimeException {
    public CommandeNotFoundException() {
      super("Commande not found !");
    }
}
