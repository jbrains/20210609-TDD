package ca.jbrains.pos.test;

import java.util.HashMap;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {
    @Override
    protected Catalog catalogWith(final String barcode, final Price matchingPrice) {
        final InMemoryCatalog catalog = new InMemoryCatalog(new HashMap<>() {{
            put("not " + barcode, Price.inCents(237));
            put(barcode, matchingPrice);
            put("certainly not " + barcode, Price.inCents(238));
        }});
        return catalog;
    }

    @Override
    protected Catalog catalogWithout(final String missingBarcode) {
        return new InMemoryCatalog(new HashMap() {{
            put("not " + missingBarcode, Price.inCents(237));
            put("certainly not " + missingBarcode, Price.inCents(238));
            put("I told you not to put " + missingBarcode + " in here!", Price.inCents(239));
        }});
    }
}
