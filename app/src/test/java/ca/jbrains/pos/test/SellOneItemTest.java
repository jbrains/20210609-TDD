package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SellOneItemTest {
    @Test
    void productFound() throws Exception {
        final Sale sale = new Sale();
        final Display display = new Display();

        sale.onBarcode("12345");

        Assertions.assertEquals("EUR 7.50", display.getText());
    }

    public static class Sale {
        public void onBarcode(final String barcode) {
        }
    }

    public static class Display {
        public String getText() {
            return "EUR 7.50";
        }
    }
}
