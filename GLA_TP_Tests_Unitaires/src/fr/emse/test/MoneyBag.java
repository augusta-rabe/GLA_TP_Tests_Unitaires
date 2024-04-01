package fr.emse.test;

import java.util.Vector;

public class MoneyBag implements IMoney {
    private Vector<Money> fMonies = new Vector<Money>();

    public MoneyBag() {
        // Constructeur par défaut vide
    }

    public MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    public MoneyBag(Money bag[]) {
        for (int i = 0; i < bag.length; i++) {
            appendMoney(bag[i]);
        }
    }

    private void appendMoney(Money m) {
        if (fMonies.isEmpty()) {
            fMonies.add(m);
        } else {
            int i = 0;
            while (i < fMonies.size() && !fMonies.get(i).currency().equals(m.currency())) {
                i++;
            }
            if (i >= fMonies.size()) {
                fMonies.add(m);
            } else {
                fMonies.set(i, (Money) fMonies.get(i).add(m));
            }
        }
    }

    @Override
    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
    }

    @Override
    public IMoney addMoney(Money m) {
        MoneyBag result = new MoneyBag();
        result.addMoneyBag(this);
        result.appendMoney(m);
        return result.simplify();
    }

    @Override
    public IMoney addMoneyBag(MoneyBag mb) {
        MoneyBag result = new MoneyBag();
        Vector<Money> bagMonies = mb.getMonies();
        for (int i = 0; i < fMonies.size(); i++) {
            result.appendMoney(fMonies.get(i));
        }
        for (int i = 0; i < bagMonies.size(); i++) {
            result.appendMoney(bagMonies.get(i));
        }
        return result.simplify();
    }

    private Vector<Money> getMonies() {
        return fMonies;
    }

    private MoneyBag simplify() {
        MoneyBag result = new MoneyBag();
        for (Money money : fMonies) {
            result = (MoneyBag) result.addMoney(money);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MoneyBag moneyBag = (MoneyBag) obj;
        return fMonies.equals(moneyBag.fMonies);
    }

    @Override
    public String toString() {
        return "MoneyBag{" +
                "fMonies=" + fMonies +
                '}';
    }
}
