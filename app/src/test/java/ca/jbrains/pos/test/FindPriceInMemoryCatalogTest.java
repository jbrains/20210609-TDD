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

        final InMemoryCatalog catalog = new InMemoryCatalog(new HashMap<>() {{
            put("12345", matchingPrice);
        }});
        Assertions.assertEquals(matchingPrice, catalog.findPrice("12345"));
    }

    @Test
    void noMatchingPrice() throws Exception {
        final InMemoryCatalog catalog = new InMemoryCatalog(Collections.emptyMap());

        Assertions.assertEquals(null, catalog.findPrice("12345"));
    }

    public static class InMemoryCatalog {
        private final Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(final Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(final String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
