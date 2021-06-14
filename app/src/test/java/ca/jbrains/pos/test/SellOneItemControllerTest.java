package ca.jbrains.pos.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SellOneItemControllerTest {
    @Test
    void productFound() throws Exception {
        final Catalog catalog = Mockito.mock(Catalog.class);
        final Display display = Mockito.mock(Display.class);
        final SellOneItemController sellOneItemController = new SellOneItemController(catalog, display);

        final Price matchingPrice = Price.inCents(795);
        // Stub = Fake
        // lambda expressions = 1-method interface = Delegate (C#), we can write findPrice = \ignored -> 795
        when(catalog.findPrice("12345")).thenReturn(matchingPrice);

        sellOneItemController.onBarcode("12345");

        // Method expectation = assert method was invoked/called
        verify(display).displayPrice(matchingPrice);
    }

    @Test
    void productNotFound() throws Exception {
        final Catalog catalog = Mockito.mock(Catalog.class);
        final Display display = Mockito.mock(Display.class);
        final SellOneItemController sellOneItemController = new SellOneItemController(catalog, display);

        when(catalog.findPrice("::missing barcode::")).thenReturn(null);

        sellOneItemController.onBarcode("::missing barcode::");

        verify(display).displayProductNotFoundMessage("::missing barcode::");
    }

    @Test
    void emptyBarcode() throws Exception {
        final Catalog catalog = Mockito.mock(Catalog.class);
        final Display display = Mockito.mock(Display.class);
        final SellOneItemController sellOneItemController = new SellOneItemController(catalog, display);

        sellOneItemController.onBarcode("");

        verify(display).displayEmptyBarcodeMessage();
    }

    public static class SellOneItemController {
        private final Catalog catalog;
        private final Display display;

        public SellOneItemController(final Catalog catalog, final Display display) {
            this.catalog = catalog;this.display = display;
        }

        public void onBarcode(final String barcode) {
            if ("".equals(barcode)) {
                display.displayEmptyBarcodeMessage();
                return;
            }

            final Price price = catalog.findPrice(barcode);
            if (price == null)
                display.displayProductNotFoundMessage(barcode);
            else
                display.displayPrice(price);
        }
    }

    public static class Price {
        public static Price inCents(int cents) {
            return new Price();
        }
    }

    public interface Catalog {
        Price findPrice(String barcode);
    }

    public interface Display {
        void displayPrice(Price priceInCents);

        void displayProductNotFoundMessage(String missingBarcode);

        void displayEmptyBarcodeMessage();
    }
}
