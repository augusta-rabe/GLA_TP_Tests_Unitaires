package fr.emse.test;

import static org.junit.Assert.*;
import org.junit.Test;


public class MoneyTest {
    private Money f12CHF;
    private Money f14CHF;
    private Money f7USD;
    private Money f21USD;
    private MoneyBag fMB1;
    private MoneyBag fMB2;
    
    @BeforeEach
    public void setUp() {
        f12CHF = new Money(12, "CHF");
        f14CHF = new Money(14, "CHF");
        f7USD = new Money(7, "USD");
        f21USD = new Money(21, "USD");
        fMB1 = new MoneyBag(f12CHF, f7USD);
        fMB2 = new MoneyBag(f14CHF, f21USD);
    }

    @Test
    public void testMixedSimpleAdd() {
        // [12 CHF] + [7 USD] == {[12 CHF][7 USD]}
        Money bag[] = { f12CHF, f7USD };
        MoneyBag expected = new MoneyBag(bag);
        assertEquals(expected, f12CHF.add(f7USD));
    }

    @Test
    public void testBagSimpleAdd() {
        // [12 CHF][7 USD] + [14 CHF] == {[12 CHF][7 USD][14 CHF]}
        Money expected[] = { f12CHF, f7USD, f14CHF };
        MoneyBag result = (MoneyBag) fMB1.add(f14CHF);
        MoneyBag expectedBag = new MoneyBag(expected);
        assertEquals(expectedBag, result);
    }

    @Test
    public void testSimpleBagAdd() {
        // [12 CHF] + [14 CHF][21 USD] == {[12 CHF][14 CHF][21 USD]}
        Money expected[] = { f12CHF, f14CHF, f21USD };
        MoneyBag result = (MoneyBag) f12CHF.add(fMB2);
        MoneyBag expectedBag = new MoneyBag(expected);
        assertEquals(expectedBag, result);
    }

    @Test
    public void testBagBagAdd() {
        // [12 CHF][7 USD] + [14 CHF][21 USD] == {[12 CHF][7 USD][14 CHF][21 USD]}
        Money expected[] = { f12CHF, f7USD, f14CHF, f21USD };
        MoneyBag result = (MoneyBag) fMB1.add(fMB2);
        MoneyBag expectedBag = new MoneyBag(expected);
        assertEquals(expectedBag, result);
    }
}
