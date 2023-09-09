
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import unsignedNumbers.UByte;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnsignedByteTest {
    @Nested
    class AdditionTest {
        @Test
        public void addUByteWithZero() {
            UByte uByte = new UByte(0xff);
            UByte wByte = new UByte(0);
            assertEquals(uByte.add(wByte).getValue(), 0xff);
        }

        @Test
        public void addUByteWithUByteMaxValue(){
            UByte uByte = new UByte(UByte.MAX_VALUE);
            UByte wByte = new UByte(UByte.MAX_VALUE);
            assertEquals(uByte.add(wByte).getValue(), 0xfe);
        }

        @Test
        public void addMultipleTimes(){
            UByte uByte = new UByte(0xA0, true);
            UByte wByte = new UByte(0x0A, true);

            uByte.add(wByte.add(1).add(null))
                    .add(null)
                    .add(-1)
                    .add(0xff)
                    .increment()
                    .add(new UByte(1).add(2).getValue());

            assertEquals(uByte.getValue(), 0xAD);
        }

        @Test
        public void addImmediateZero() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.add(0).getValue(), 0xff);
        }

        @Test
        public void addNegativeOne() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.add(-1).getValue(), 0xfe);
        }

        @Test
        public void addWithWrapAround() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.add(1).getValue(), 0);
        }

        @Test
        public void addUByteMaxValue() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.add(UByte.MAX_VALUE).getValue(), 0xfe);
        }

        @Test
        public void addIntegerMaxValue() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.add(Integer.MAX_VALUE).getValue(), 0xfe);
        }

        @Test
        public void addIntegerMinValue() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.add(Integer.MIN_VALUE).getValue(), 0xff);
        }

        @Test
        public void addUByteMaxValuePlusOne() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.add(UByte.MAX_VALUE + 1).getValue(), 0xff);
        }

        @Test
        public void addIntegerMaxValuePlusOne() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.add(Integer.MAX_VALUE + 1).getValue(), 0xff);
        }

        @Test
        public void addIntegerMinValuePlusOne() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.add(Integer.MIN_VALUE + 1).getValue(), 0);
        }

        @Test
        public void addNullObject() {
            assertThrows(NullPointerException.class, () -> {
                UByte uByte = new UByte(0xff);
                uByte.add(null);
            });
        }

        @Test
        public void addNullObjectWithZeroFlag() {
            UByte uByte = new UByte(0xff, true);
            assertEquals(uByte.add(null).getValue(), 0xff);
        }

        @Test
        public void addNegativeOneToZero() {
            UByte uByte = new UByte(0);
            assertEquals(uByte.add(-1).getValue(), 0xff);
        }

    }

    @Nested
    public class InitializeTest {
        @Test
        public void initializeToZero() {
            UByte uByte = new UByte(0);
            assertEquals(uByte.getValue(), 0);
        }

        @Test
        public void initializeToNegativeOne() {
            assertThrows(IllegalArgumentException.class, () -> {
                UByte unsignedByte = new UByte(-1);
            });
        }

        @Test
        public void initializeToIntegerMinValue() {
            assertThrows(IllegalArgumentException.class, () -> {
                UByte unsignedByte = new UByte(Integer.MIN_VALUE);
            });
        }

        @Test
        public void initializeTo0xFF() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.getValue(), 0xff);
        }

        @Test
        public void initializeToLargeBitValue() {
            UByte uByte = new UByte(0xffff);
            assertEquals(uByte.getValue(), 0xff);
        }

        @Test
        public void initializeToIntegerMaxValue() {
            UByte uByte = new UByte(Integer.MAX_VALUE);
            assertEquals(uByte.getValue(), 0xff);
        }
    }

    @Nested
    public class IncrementTest {

        @Test
        public void incrementValue() {
            UByte uByte = new UByte(0x12);
            assertEquals(uByte.increment().getValue(), 0x13);
        }

        @Test
        public void incrementMaxValue() {
            UByte uByte = new UByte(UByte.MAX_VALUE);
            assertEquals(uByte.increment().getValue(), 0);
        }
    }

    @Nested
    class SubtractionTest {
        @Test
        public void subtractUByteWithZero() {
            UByte uByte = new UByte(0xff);
            UByte wByte = new UByte(0);
            assertEquals(uByte.subtract(wByte).getValue(), 0xff);
        }

        @Test
        public void subtractImmediateZero() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.subtract(0).getValue(), 0xff);
        }

        @Test
        public void subtractOne() {
            UByte uByte = new UByte(0xff);
            UByte wByte = new UByte(1);
            assertEquals(uByte.subtract(wByte).getValue(), 0xfe);
        }

        @Test
        public void subtractNegativeOne() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.subtract(-1).getValue(), 0);
        }
        @Test
        public void subtractNullNoFlag(){
            assertThrows(NullPointerException.class, () -> {
                UByte uByte = new UByte(0xff);
                uByte.subtract(null);
            });
        }
        @Test
        public void subtractNullWithFlag(){
            UByte uByte = new UByte(0xff, true);
            assertEquals(uByte.subtract(null).getValue(), 0xff);
        }

        @Test
        public void subtractIntegerMaxValue() {
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.subtract(Integer.MAX_VALUE).getValue(), 0);
        }
    }

    @Nested
    class DecrementTest{
        @Test
        public void decrementValue(){
            UByte uByte = new UByte(0x12);
            assertEquals(uByte.decrement().getValue(), 0x11);
        }

        @Test
        public void decrementMinValue(){
            UByte uByte = new UByte(0);
            assertEquals(uByte.decrement().getValue(), 0xff);
        }
    }

    @Nested
    class MultiplyTest{

        @Test
        public void multiplyByZero(){
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.multiply(0).getValue(), 0);
        }

        @Test
        public void multiplyByOne(){
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.multiply(1).getValue(), 0xff);
        }

        @Test
        public void multiplyByTwo(){
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.multiply(2).getValue(), 0xfe);
        }
        @Test
        public void multiplyByTwoOnRandomNumber(){
            UByte uByte = new UByte(0xf);
            assertEquals(uByte.multiply(2).getValue(), 0b11110);
        }

        @Test
        public void multiplyByNegativeNumber(){
            assertThrows(IllegalArgumentException.class, () -> {
                UByte uByte = new UByte(0xff);
                uByte.multiply(-1);
            });
        }

        @Test
        public void multiplyByMaxValue(){
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.multiply(UByte.MAX_VALUE).getValue(), 0x01);
        }

        @Test
        public void multiplyByMaxValuePlusOne(){
            UByte uByte = new UByte(0xff);
            assertEquals(uByte.multiply(UByte.MAX_VALUE + 1).getValue(), 0x0);
        }
    }

    @Nested
    class RightShiftTest{

    }

    @Nested
    class LeftShiftTest{

    }

    @Nested
    class AndTest{

    }

    @Nested
    class OrTest{

    }

}

