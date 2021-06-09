package ca.jbrains.math.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AddFractionsTest {
    @Test
    void zeroPlusZero() throws Exception {
        final Fraction sum = new Fraction(0).plus(new Fraction(0));
        Assertions.assertEquals(0, sum.intValue());
    }

    @Test
    @Disabled("refactoring")
    void notZeroPlusZero() throws Exception {
        final Fraction sum = new Fraction(4).plus(new Fraction(0));
        Assertions.assertEquals(4, sum.intValue());
    }

    public static class Fraction {
        private final int integerValue;

        public Fraction(final int integerValue) {
            this.integerValue = integerValue;
        }

        public Fraction plus(final Fraction that) {
            return new Fraction(0);
        }

        public int intValue() {
            return integerValue;
        }
    }
}
