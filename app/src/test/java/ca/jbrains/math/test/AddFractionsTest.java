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

    @Test
    void relativelyPrimeDenominators() throws Exception {
        final Fraction sum = new Fraction(3, 7).plus(new Fraction(2, 5));

        Assertions.assertEquals(29, sum.getNumerator());
        Assertions.assertEquals(35, sum.getDenominator());
    }

    @Test
    void denominatorsWithACommonFactor() throws Exception {
        final Fraction sum = new Fraction(3, 8).plus(new Fraction(5, 12));

        Assertions.assertEquals(19, sum.getNumerator());
        Assertions.assertEquals(24, sum.getDenominator());
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
            else if (this.denominator == that.denominator)
                return new Fraction(this.numerator + that.numerator, this.denominator);
            else
                return new Fraction(
                        this.numerator * that.denominator + this.denominator * that.numerator,
                        this.denominator * that.denominator);
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
