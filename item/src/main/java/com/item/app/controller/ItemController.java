package com.item.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(path = "/item")
public class ItemController {
    private List<Integer> keyboardPrices = Arrays.asList(35000, 30000, 40000);
    private List<Integer> mousePrices = Arrays.asList(12000, 20000, 35000);

    /*
    Pada method ini saya akan mencari rekomendasi dengan uang yang menjadi input
    oleh user. Saya melakukan perulangan untuk mencari harga yang paling sesuai
    dari kedua jenis barang, dan setiap akhir looping uang pelanggan akn dikurangi
    oleh best choice pada looping saat itu. Sampai ketika uang user sudah habis
    dikurangi, maka looping akan selesai bekerja dan memberikan resultnya.
     */
    @GetMapping
    private ResponseEntity<String> getRecommendation(@RequestParam("money") Integer money) {
        Map<String, Integer> purchases = new HashMap<>();
        int remainingMoney = money;

        while (remainingMoney > 0) {
            int bestChoice = -1;
            int minDifference = Integer.MAX_VALUE;
            String item = "";

            for (int price : keyboardPrices) {
                int difference = remainingMoney - price;
                if (difference >= 0 && difference < minDifference) {
                    minDifference = difference;
                    bestChoice = price;
                    item = "keyboard";
                }
            }

            for (int price : mousePrices) {
                int difference = remainingMoney - price;
                if (difference >= 0 && difference < minDifference) {
                    minDifference = difference;
                    bestChoice = price;
                    item = "mouse";
                }
            }

            if (bestChoice == -1) {
                break;
            }

            purchases.put(item, bestChoice);
            remainingMoney -= bestChoice;
        }

        return purchases.size() != 0
                ? ResponseEntity.ok("Pembelian yang paling efisien: " + purchases)
                : ResponseEntity.ok("Uang anda tidak cukup.");
    }
}
