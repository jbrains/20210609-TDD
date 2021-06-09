package ca.jbrains.math.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddFractionsTest {
    @Test
    void zeroPlusZero() throws Exception {
        final Fraction sum = new Fraction(0).plus(new Fraction(0));
        Assertions.assertEquals(0, sum.intValue());
    }

    @Test
    void notZeroPlusZero() throws Exception {
        final Fraction sum = new Fraction(4).plus(new Fraction(0));
        Assertions.assertEquals(4, sum.intValue());
    }

    @Test
    void zeroPlusNotZero() throws Exception {
        final Fraction sum = new Fraction(0).plus(new Fraction(6));
        Assertions.assertEquals(6, sum.intValue());
    }

    public static class Fraction {
        private final int integerValue;

        public Fraction(final int integerValue) {
            this.integerValue = integerValue;
        }

        public Fraction plus(final Fraction that) {
            if (this.integerValue == 0)
                return that;
            else
                return this;
        }

        public int intValue() {
            return integerValue;
        }
    }
}
