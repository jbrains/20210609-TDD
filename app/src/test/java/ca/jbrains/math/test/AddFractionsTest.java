package ca.jbrains.math.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddFractionsTest {
    @Test
    void zeroPlusZero() throws Exception {
        Assertions.assertEquals(new Fraction(0), new Fraction(0).plus(new Fraction(0)));
    }

    public static class Fraction {
        public Fraction(final int integerValue) {
        }

        public Fraction plus(final Fraction that) {
            return new Fraction(0);
        }
    }
}
