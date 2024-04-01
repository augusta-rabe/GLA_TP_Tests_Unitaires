package fr.emse.test;

import static org.junit.Assert.*;

import org.junit.Test;


public class MoneyBagTest {
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
    public void testBagEquals() {
        assertTrue(!fMB1.equals(null));
        assertEquals(fMB1, fMB1);
        assertTrue(!fMB1.equals(f12CHF));
        assertTrue(!f12CHF.equals(fMB1));
        assertTrue(!fMB1.equals(fMB2));
    }
    
    @Test
    public void testSimplifyMoneyBag() {
        Money m7USD = new Money(7, "USD");
        Money m12CHF = new Money(12, "CHF");
        MoneyBag mb1 = new MoneyBag(m7USD, m12CHF);

        Money mMinus12CHF = new Money(-12, "CHF");

        // Ajout d'un Money à un MoneyBag
        IMoney result1 = mb1.add(mMinus12CHF);
        assertTrue(result1 instanceof Money);
        assertEquals(new Money(7, "USD"), result1);

        // Ajout d'un MoneyBag à un MoneyBag
        MoneyBag mb2 = new MoneyBag(new Money(5, "USD"), new Money(10, "CHF"));
        IMoney result2 = mb1.add(mb2);
        assertTrue(result2 instanceof MoneyBag);
        assertEquals(new Money(12, "CHF"), ((MoneyBag) result2).getMonies().get(0));
        assertEquals(new Money(12, "USD"), ((MoneyBag) result2).getMonies().get(1));
    }

    
}