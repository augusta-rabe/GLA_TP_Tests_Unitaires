package fr.emse.test;

class Money  implements IMoney {
    private int fAmount;
    private String fCurrency;

    public Money(int amount, String currency) {
        fAmount = amount;
        fCurrency = currency;
    }

    public int amount() {
        return fAmount;
    }

    public String currency() {
        return fCurrency;
    }

    @Override
    public IMoney add(IMoney aMoney) {
        if (aMoney.currency().equals(currency())) {
            return new Money(amount() + aMoney.amount(), currency());
        }
        return new MoneyBag(this, (Money) aMoney);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Money money = (Money) obj;
        return fAmount == money.fAmount && fCurrency.equals(money.fCurrency);
    }
}
