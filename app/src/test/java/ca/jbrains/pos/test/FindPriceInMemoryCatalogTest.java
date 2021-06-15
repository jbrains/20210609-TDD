package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest {
    @Test
    void matchingPrice() throws Exception {
        final Price matchingPrice = Price.inCents(138);

        final Catalog catalog = catalogWith("12345", matchingPrice);
        Assertions.assertEquals(matchingPrice, catalog.findPrice("12345"));
    }

    private Catalog catalogWith(final String barcode, final Price matchingPrice) {
        final InMemoryCatalog catalog = new InMemoryCatalog(new HashMap<>() {{
            put(barcode, matchingPrice);
        }});
        return catalog;
    }

    @Test
    void noMatchingPrice() throws Exception {
        final Catalog catalog = catalogWithout("12345");
        Assertions.assertEquals(null, catalog.findPrice("12345"));
    }

    private Catalog catalogWithout(final String missingBarcode) {
        return new InMemoryCatalog(Collections.emptyMap());
    }

    public static class InMemoryCatalog implements Catalog {
        private final Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(final Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(final String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
