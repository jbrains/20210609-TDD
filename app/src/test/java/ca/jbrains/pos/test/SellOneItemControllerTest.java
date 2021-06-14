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

        // Stub = Fake
        // lambda expressions = 1-method interface = Delegate (C#), we can write findPrice = \ignored -> 795
        when(catalog.findPrice("12345")).thenReturn(795);

        sellOneItemController.onBarcode("12345");

        // Method expectation = assert method was invoked/called
        verify(display).displayPrice(795);
    }

    public static class SellOneItemController {
        private final Catalog catalog;
        private final Display display;

        public SellOneItemController(final Catalog catalog, final Display display) {
            this.catalog = catalog;
            this.display = display;
        }

        public void onBarcode(final String barcode) {
            display.displayPrice(catalog.findPrice(barcode));
        }
    }

    public interface Catalog {
        int findPrice(String barcode);
    }

    public interface Display {
        void displayPrice(int priceInCents);
    }
}
