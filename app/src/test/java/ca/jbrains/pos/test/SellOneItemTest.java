package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SellOneItemTest {
    @Test
    void productFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new HashMap() {{
            put("12345", "EUR 7.50");
        }});

        sale.onBarcode("12345");

        Assertions.assertEquals("EUR 7.50", display.getText());
    }

    @Test
    void anotherProductFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new HashMap() {{
            put("23456", "EUR 12.95");
        }});

        sale.onBarcode("23456");

        Assertions.assertEquals("EUR 12.95", display.getText());
    }

    @Test
    void productNotFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new HashMap());

        sale.onBarcode("99999");

        Assertions.assertEquals("Product not found: 99999", display.getText());
    }

    @Test
    void emptyBarcode() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new HashMap());

        sale.onBarcode("");

        Assertions.assertEquals("Scanning error: empty barcode", display.getText());
    }

    public static class Sale {
        private final Display display;
        private final Map<String, String> pricesByBarcode;

        public Sale(final Display display, final Map pricesByBarcode) {
            this.display = display;
            this.pricesByBarcode = pricesByBarcode;
        }

        public void onBarcode(final String barcode) {
            if ("".equals(barcode)) {
                displayEmptyBarcodeMessage();
            }
            else if (pricesByBarcode.containsKey(barcode)) {
                findPriceThenDisplayPrice(barcode);
            }
            else {
                displayProductNotFoundMessage(barcode);
            }
        }

        private void findPriceThenDisplayPrice(final String barcode) {
            display.text = pricesByBarcode.get(barcode);
        }

        private void displayEmptyBarcodeMessage() {
            display.text = "Scanning error: empty barcode";
        }

        private void displayProductNotFoundMessage(final String barcode) {
            display.text = "Product not found: " + barcode;
        }
    }

    public static class Display {
        public String text;

        public String getText() {
            return text;
        }
    }
}

