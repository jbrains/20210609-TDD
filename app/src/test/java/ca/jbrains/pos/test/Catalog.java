package ca.jbrains.pos.test;

import java.util.Map;

public class Catalog {
    private final Map<String, String> pricesByBarcode;

    public Catalog(final Map<String, String> pricesByBarcode) {
        this.pricesByBarcode = pricesByBarcode;
    }

    public String findPrice(final String barcode) {
        return pricesByBarcode.get(barcode);
    }
}
