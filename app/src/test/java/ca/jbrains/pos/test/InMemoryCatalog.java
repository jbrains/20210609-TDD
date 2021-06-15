package ca.jbrains.pos.test;

import java.util.Map;

public class InMemoryCatalog implements Catalog {
    private final Map<String, Price> pricesByBarcode;

    public InMemoryCatalog(final Map<String, Price> pricesByBarcode) {
        this.pricesByBarcode = pricesByBarcode;
    }

    public Price findPrice(final String barcode) {
        return pricesByBarcode.get(barcode);
    }
}
