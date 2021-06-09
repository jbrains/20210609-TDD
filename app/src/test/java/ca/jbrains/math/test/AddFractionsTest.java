package ca.jbrains.math.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddFractionsTest {
    public static class BackwardsCompatibleWithIntegers {
        @Test
        void zeroPlusZero() throws Exception {
            final Fraction sum = new Fraction(0).plus(new Fraction(0));
            Assertions.assertEquals(new Fraction(0), sum);
        }

        @Test
        void notZeroPlusZero() throws Exception {
            final Fraction sum = new Fraction(4).plus(new Fraction(0));
            Assertions.assertEquals(new Fraction(4), sum);
        }

        @Test
        void zeroPlusNotZero() throws Exception {
            final Fraction sum = new Fraction(0).plus(new Fraction(6));
            Assertions.assertEquals(new Fraction(6), sum);
        }

        @Test
        void nonZeroIntegers() throws Exception {
            final Fraction sum = new Fraction(3).plus(new Fraction(5));
            Assertions.assertEquals(new Fraction(8), sum);
        }
    }

    @Test
    void sameDenominators() throws Exception {
        final Fraction sum = new Fraction(1, 5).plus(new Fraction(2, 5));
        Assertions.assertEquals(new Fraction(3, 5), sum);
    }

    @Test
    void relativelyPrimeDenominators() throws Exception {
        final Fraction sum = new Fraction(3, 7).plus(new Fraction(2, 5));
        Assertions.assertEquals(new Fraction(29, 35), sum);
    }

    @Test
    void denominatorsWithACommonFactor() throws Exception {
        final Fraction sum = new Fraction(3, 8).plus(new Fraction(5, 12));
        Assertions.assertEquals(new Fraction(19, 24), sum);
    }

    public static class CreateFractionsTest {
        @Test
        void zeroDenominator() throws Exception {
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> new Fraction(5, 0));
        }
    }
    public static class Fraction {
        private int numerator;
        private int denominator;

        public Fraction(final int integerValue) {
            this(integerValue, 1);
        }

        public Fraction(final int numerator, final int denominator) {
            if (denominator == 0)
                throw new IllegalArgumentException("The denominator of a Fraction can't be 0.");

            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(final Fraction that) {
            return new Fraction(
                    this.numerator * that.denominator + this.denominator * that.numerator,
                    this.denominator * that.denominator);
        }

        @Override
        public boolean equals(final Object other) {
            if (other instanceof Fraction) {
                Fraction that = (Fraction) other;
                return this.numerator * that.denominator == that.numerator * this.denominator;
            }
            else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return 31 * numerator + denominator;
        }
    }
}
