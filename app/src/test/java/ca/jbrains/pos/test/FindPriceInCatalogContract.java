package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class FindPriceInCatalogContract {
    @Test
    void matchingPrice() throws Exception {
        final Price matchingPrice = Price.inCents(138);

        final Catalog catalog = catalogWith("12345", matchingPrice);
        Assertions.assertEquals(matchingPrice, catalog.findPrice("12345"));
    }

    protected abstract Catalog catalogWith(String barcode, Price matchingPrice);

    @Test
    void noMatchingPrice() throws Exception {
        final Catalog catalog = catalogWithout("12345");
        Assertions.assertEquals(null, catalog.findPrice("12345"));
    }

    protected abstract Catalog catalogWithout(String missingBarcode);
}
