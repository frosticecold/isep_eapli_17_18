package eapli.framework.domain.money;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import javax.persistence.Embeddable;

/**
 * represents money values.
 *
 * based on http://martinfowler.com/eaaDev/quantity.html
 *
 */
@Embeddable
public class Money implements Comparable<Money>, Serializable, ValueObject {

    private static final long serialVersionUID = 1L;
    private static final transient int HUNDRED = 100;
    //@Convert(converter = MoneyAmountConverter.class)
    private final BigInteger amount;
    private final Currency currency;

    /**
     * For ORM tool only
     */
    protected Money() {
        // for ORM tool only
        amount = null;
        currency = null;
    }

    /**
     * Constructs a new Money object.
     *
     * You'll notice there are no setters. Money is a Value Object and is thus
     * immutable. It helps to have a variety of constructors to make it easy to
     * make monies. Constructors that convert from basic numeric types are
     * always useful.
     *
     * @param amount the amount in the units of currency, e.g., 100.50 EUR
     * @param currency
     */
    public Money(final double amount, final Currency currency) {
        this.amount = BigInteger.valueOf(Math.round(amount *HUNDRED ));
        this.currency = currency;
    }

    public Money(final long amount, final Currency currency) {
        this.amount = BigInteger.valueOf(amount * HUNDRED);
        this.currency = currency;
    }

    /**
     *
     * @param amountInCents
     * @param currency
     */
    private Money(final BigInteger amountInCents, final Currency currency) {
        assert amountInCents != null;
        assert currency != null;

        amount = amountInCents;
        this.currency = currency;
    }

    /**
     * If you use one currency a lot, you may want a special constructor for
     * that currency.
     */
    public static Money dollars(final double amount) {
        return new Money(amount, Currency.getInstance("USD"));
    }

    /**
     * If you use one currency a lot, you may want a special constructor for
     * that currency.
     */
    public static Money euros(final double amount) {
        return new Money(amount, Currency.getInstance("EUR"));
    }

    /**
     * Returns the amount portion of this Money object.
     *
     * Notice that I use a BigInteger. In Java I could equally well use a
     * BigDecimal, but in many languages an integer is the only decent option,
     * so using an integer seems the best for explanation. Don't be afraid to
     * choose your representation of the amount part of a quantity based on
     * performance factors. The beauty of objects is that you can choose any
     * data structure you like on the inside, providing you hide it on the
     * outside.
     */
    public double amount() {
        return amount.doubleValue();
    }

    public BigDecimal amountAsDecimal() {
        return BigDecimal.valueOf(amount());
    }

    /**
     * Returns the currency of this Money object.
     *
     * @return
     */
    public Currency currency() {
        return currency;
    }

    /**
     * Adds two Money objects and returns the result as a new object.
     *
     * For addition and subtraction I'm not trying to do any fancy conversion.
     * Notice that I'm using a special constructor with a marker argument.
     */
    public Money add(final Money arg) {
        if (!currency.equals(arg.currency)) {
            throw new IllegalArgumentException("Cannot add different currencies");
        }
        return new Money(amount.add(arg.amount), currency);
    }

    /**
     * Subtracts two Money instances and returns a third one with the result.
     *
     * @param arg
     * @return
     */
    public Money subtract(final Money arg) {
        return add(arg.negate());
    }

    public Money negate() {
        return new Money(amount.negate(), currency);
    }

    /**
     * Multiplies two Money objects and returns the result a new object.
     *
     */
    public Money multiply(final double arg) {
        return new Money(amount() * arg, currency);
    }

    /**
     * Divides this Money object by a certain integer denominator doing the
     * right allocation of cents.
     *
     * Multiplication is very straightforward. But division is not, as we have
     * to take care of errant pennies. We'll do that by returning an array of
     * monies, such that the sum of the array is equal to the original amount,
     * and the original amount is distributed fairly between the elements of the
     * array. Fairly in this sense means those at the begining get the extra
     * pennies.
     */
    public Money[] divide(final int denominator) {
        final BigInteger bigDenominator = BigInteger.valueOf(denominator);
        final Money[] result = new Money[denominator];
        final BigInteger simpleResult = amount.divide(bigDenominator);
        for (int i = 0; i < denominator; i++) {
            result[i] = new Money(simpleResult, currency);
        }
        final int remainder = amount.subtract(simpleResult.multiply(bigDenominator)).intValue();
        for (int i = 0; i < remainder; i++) {
            result[i] = result[i].add(new Money(BigInteger.valueOf(1), currency));
        }
        return result;
    }

    /**
     * Compares two Money objects.
     *
     * Next we'll look at comparing monies, in Java the approach is to implement
     * comparable.
     */
    @Override
    public int compareTo(final Money arg) {
        if (!currency.equals(arg.currency)) {
            throw new IllegalArgumentException("Cannot add different currencies");
        }
        return amount.compareTo(arg.amount);
    }

    /**
     * Compares two Money objects.
     *
     * It's also useful to provide some better named operations such as: That
     * makes methods that need the comparison much easier to read.
     */
    public boolean greaterThan(final Money arg) {
        return compareTo(arg) > 0;
    }

    /**
     * Compares two Money objects.
     */
    public boolean lessThan(final Money arg) {
        return compareTo(arg) < 0;
    }

    /**
     * Compares two Money objects.
     */
    public boolean greaterThanOrEqual(final Money arg) {
        return compareTo(arg) >= 0;
    }

    /**
     * Compares two Money objects.
     */
    public boolean lessThanOrEqual(final Money arg) {
        return compareTo(arg) <= 0;
    }

    /**
     * Since money is a value, it should override equals.
     */
    @Override
    public boolean equals(final Object arg) {
        if (!(arg instanceof Money)) {
            return false;
        }
        final Money other = (Money) arg;
        return currency.equals(other.currency) && amount.equals(other.amount);
    }

    /**
     * Since you override equals, don't forget to also override hash (here's a
     * simple suggestion for that).
     */
    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    public int signum() {
        return amount.signum();
    }

    @Override
    public String toString() {
        return amount()/HUNDRED + " " + currency();
    }
}
