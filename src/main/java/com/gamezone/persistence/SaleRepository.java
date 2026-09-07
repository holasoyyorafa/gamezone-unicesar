package com.gamezone.persistence;

import com.gamezone.model.Sale;
import java.util.ArrayList;
import java.util.List;


public class SaleRepository {
    private List<Sale> sales;

    public SaleRepository() {
        this.sales = new ArrayList<>();
    }

   
    public void save(Sale sale) {
        sales.add(sale);
    }

  
    public List<Sale> findAll() {
        return sales;
    }
}