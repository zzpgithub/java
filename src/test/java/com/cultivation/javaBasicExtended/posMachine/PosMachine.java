package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})
public class PosMachine {
    final Map<String, Product> productsMap = new HashMap<>();
    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        List<Product> productList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        Product[] products = objectMapper.readValue(reader, Product[].class);
        for(Product product : products){
            productsMap.put(product.getId(),product);
        }
        //throw new NotImplementedException();
        // --end-->
    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        if(productsMap.size() == 0){
            throw new IllegalStateException();
        }
        if(barcodeContent == null || barcodeContent.equals("[]")){
            return "Receipts" + "\n" +
                    "------------------------------------------------------------" + "\n" +
                    "------------------------------------------------------------" + "\n" +
            "Price: 0" + "\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Receipts" + "\n").append("------------------------------------------------------------" + "\n");
        ObjectMapper objectMapper = new ObjectMapper();
        String[] inputOrders = objectMapper.readValue(barcodeContent, String[].class);
        Map<String, Integer> ordersMap = new LinkedHashMap<>();
        for (String inputOrder : inputOrders) {
            if (!ordersMap.containsKey(inputOrder)) {
                ordersMap.put(inputOrder,1);
            }else{
                ordersMap.put(inputOrder,ordersMap.get(inputOrder)+1);
            }
        }

        int totalPrice = 0;
        for(String contentID : ordersMap.keySet()){
            Product product = productsMap.get(contentID);
            int quantity = ordersMap.get(contentID);
            sb.append(String.format("%-32s%-11d%d\n",product.getName(),product.getPrice(),quantity));
            totalPrice += product.getPrice()*quantity;
        }
        sb.append("------------------------------------------------------------" + "\n")
                .append("Price: "+ totalPrice + "\n");
        return sb.toString();
        //throw new NotImplementedException();
        // --end-->
    }
}

@SuppressWarnings("unused")
class Product {
    private String id;
    private String name;
    private Integer price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Product other = (Product) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}