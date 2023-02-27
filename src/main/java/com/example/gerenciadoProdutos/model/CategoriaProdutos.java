package com.example.gerenciadoProdutos.model;

public enum CategoriaProdutos {

   ELETRONICO("eletronico"), ELETRODOMESTICO("eletrodoméstico"),INFORMATICA("informática"),SMARTPHONE("smartphone");
   private String value;

   private CategoriaProdutos(String value){
       this.value = value;
   }

    public String getValue() {
        return value;
    }
}
