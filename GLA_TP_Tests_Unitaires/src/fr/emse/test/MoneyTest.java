package fr.emse.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    public void testSimpleAdd() {
        Money m12CHF = new Money(12, "CHF");
        Money m14CHF = new Money(14, "CHF");
        Money expected = new Money(26, "CHF");
        Money result = m12CHF.add(m14CHF);
        assertTrue(expected.equals(result));
    }
}
