package ca.jbrains.math.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddFractionsTest {
    public static class BackwardsCompatibleWithIntegers {
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

        @Test
        void nonZeroIntegers() throws Exception {
            final Fraction sum = new Fraction(3).plus(new Fraction(5));
            Assertions.assertEquals(8, sum.intValue());
        }
    }

    @Test
    void sameDenominators() throws Exception {
        final Fraction sum = new Fraction(1, 5).plus(new Fraction(2, 5));

        Assertions.assertEquals(3, sum.getNumerator());
        Assertions.assertEquals(5, sum.getDenominator());
    }

    public static class Fraction {
        private int integerValue;

        private int numerator;
        private int denominator;

        public Fraction(final int integerValue) {
            this.integerValue = integerValue;
        }

        public Fraction(final int numerator, final int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(final Fraction that) {
            if (this.denominator == 0 || that.denominator == 0)
                return new Fraction(this.integerValue + that.integerValue);
            else
                return new Fraction(this.numerator + that.numerator, this.denominator);
        }

        public int intValue() {
            return integerValue;
        }

        public int getNumerator() {
            return numerator;
        }

        public int getDenominator() {
            return denominator;
        }
    }
}
