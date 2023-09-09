package unsignedNumbers;

//TODO: Add tests for all methods
//TODO: Add Flags for operations, like overflow, carry, negative, zero, etc.
public class UByte implements Comparable<UByte>{

    class Flags {
        private boolean overflow;
        private boolean carry;
        private boolean negative;
        private boolean zero;

        public Flags() {
            clear();
        }

        public void clear() {
            overflow = false;
            carry = false;
            negative = false;
            zero = false;
        }
    }

    public static final int MAX_VALUE = 0xff;
    public static final int MIN_VALUE = 0x0;

    private final int BYTE_BITMASK = 0xff;
    private final boolean NULL_IS_ZERO;
    private Flags flags;
    private int number;

    public UByte(int initialValue) {
        this(initialValue, false);
    }

    public UByte(int initialValue, boolean nullIsZero){
        setValue(initialValue);
        this.flags = new Flags();
        NULL_IS_ZERO = nullIsZero;
    }
    public int getValue() {
        return getAsByte(this.number);
    }

    public void setValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value is negative");
        }
        this.number = getAsByte(value);
    }

    public UByte add(UByte uByte) {
        if (NULL_IS_ZERO && uByte == null) {
            return add(0);
        } else if (!NULL_IS_ZERO && uByte == null) {
            throw new NullPointerException("NULL values are NOT set to be converted into 0");
        }
        return add(uByte.getValue());
    }

    //Main method for adding. This takes care of adding and flags
    public UByte add(int immediate) {
        clearFlags();
        this.number = getAsByte(getValue() + getAsByte(immediate));
        return this;
    }

    public UByte increment() {
        return add(1);
    }

    public UByte subtract(UByte uByte) {
        if (NULL_IS_ZERO && uByte == null) {
            return subtract(0);
        } else if (!NULL_IS_ZERO && uByte == null) {
            throw new NullPointerException("NULL values are NOT set to be converted into 0");
        }

        return subtract(uByte.getValue());
    }

    //Main subtract method. This takes care of subtracting and flags
    public UByte subtract(int immediate) {
        clearFlags();
        this.number = getAsByte(getValue() - getAsByte(immediate));
        return this;
    }

    public UByte decrement() {
        return subtract(1);
    }

    public UByte multiply(UByte uByte) {
        multiply(uByte.getValue());
        return this;
    }

    public UByte multiply(int immediate) {
        clearFlags();
        if(immediate < 0){
            throw new IllegalArgumentException("Immediate is negative");
        }
        this.number = getAsByte(getValue() * getAsByte(immediate));
        return this;
    }

    public UByte rightShift(UByte uByte) {
        return this;
    }

    public UByte rightShift(int immediate) {
        clearFlags();
        return this;
    }

    public UByte leftShift(UByte uByte) {
        return this;
    }
    public UByte leftShift(int immediate) {
        clearFlags();
        return this;
    }

    public UByte and(UByte uByte) {
        return this;
    }
    public UByte and(int immediate) {
        clearFlags();
        return this;
    }

    public UByte or(UByte uByte) {
        return this;
    }
    public UByte or(int immediate) {
        clearFlags();
        return this;
    }


    private int getAsByte(int value) {
        return (value & BYTE_BITMASK);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(UByte o) {
        return Integer.compare(number, o.getValue());
    }

    private void clearFlags() {
        flags.clear();
    }
}
