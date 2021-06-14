package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class SellOneItemTest {
    @Test
    void productFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(new HashMap() {{
            put("12345", "EUR 7.50");
        }}));

        sale.onBarcode("12345");

        Assertions.assertEquals("EUR 7.50", display.getText());
    }

    @Test
    void anotherProductFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(new HashMap() {{
            put("23456", "EUR 12.95");
        }}));

        sale.onBarcode("23456");

        Assertions.assertEquals("EUR 12.95", display.getText());
    }

    @Test
    void productNotFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(new HashMap()));

        sale.onBarcode("99999");

        Assertions.assertEquals("Product not found: 99999", display.getText());
    }

    @Test
    void emptyBarcode() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(new HashMap()));

        sale.onBarcode("");

        Assertions.assertEquals("Scanning error: empty barcode", display.getText());
    }

    public static class Sale {
        private final Display display;
        private final Catalog catalog;

        public Sale(final Display display, final Catalog catalog) {
            this.display = display;
            this.catalog = catalog;
        }

        public void onBarcode(final String barcode) {
            // REFACTOR Guard Clause. Move up the call stack.
            if ("".equals(barcode)) {
                display.displayEmptyBarcodeMessage();
                return;
            }

            final String price = catalog.findPrice(barcode);
            if (price == null) {
                display.displayProductNotFoundMessage(barcode);
            }
            else {
                display.displayPrice(price);
            }
        }
    }

    public static class Display {
        private String text;

        public String getText() {
            return text;
        }

        public void displayPrice(final String priceAsText) {
            text = priceAsText;
        }

        public void displayEmptyBarcodeMessage() {
            text = "Scanning error: empty barcode";
        }

        public void displayProductNotFoundMessage(final String barcode) {
            text = "Product not found: " + barcode;
        }
    }
}

