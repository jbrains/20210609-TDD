package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class FindPriceInCatalogContractUsingComposition {
    private static FindPriceInCatalogFactory findPriceInCatalogFactory;

    @BeforeAll
    public static void initializeImplementationFactory() {
        // REFACTOR Move this anonymous implementation into a class
        // REFACTOR Replace with Parameterized Test pattern for 1 factory per implementation
        findPriceInCatalogFactory = new FindPriceInCatalogFactory() {
            @Override
            public Catalog catalogWith(final String barcode, final Price matchingPrice) {
                return new InMemoryCatalog(new HashMap<>() {{
                    put(barcode, matchingPrice);
                }});
            }

            @Override
            public Catalog catalogWithout(final String missingBarcode) {
                return new InMemoryCatalog(new HashMap<>() {{
                    put("not " + missingBarcode, Price.inCents(-1));
                }});
            }
        };
    }

    @Test
    void matchingPrice() throws Exception {
        final Price matchingPrice = Price.inCents(138);

        final Catalog catalog = findPriceInCatalogFactory.catalogWith("12345", matchingPrice);
        Assertions.assertEquals(matchingPrice, catalog.findPrice("12345"));
    }

    @Test
    void noMatchingPrice() throws Exception {
        final Catalog catalog = findPriceInCatalogFactory.catalogWithout("12345");
        Assertions.assertEquals(null, catalog.findPrice("12345"));
    }

    public interface FindPriceInCatalogFactory {
        Catalog catalogWith(String barcode, Price matchingPrice);

        Catalog catalogWithout(String missingBarcode);
    }
}
