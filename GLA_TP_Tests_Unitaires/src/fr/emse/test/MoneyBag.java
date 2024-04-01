package fr.emse.test;

import java.util.Vector;

class MoneyBag implements IMoney {
    private Vector<Money> fMonies = new Vector<Money>();

    public MoneyBag() {
        // Constructeur par défaut vide
    }

    MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    MoneyBag(Money bag[]) {
        for (int i = 0; i < bag.length; i++)
            appendMoney(bag[i]);
    }

    private void appendMoney(Money m) {
        if (fMonies.isEmpty()) {
            fMonies.add(m);
        } else {
            int i = 0;
            while ((i < fMonies.size()) && (!(fMonies.get(i).currency().equals(m.currency()))))
                i++;
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
        return new MoneyBag();
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
        return result;
    }

    private Vector<Money> getMonies() {
        return fMonies;
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
}
