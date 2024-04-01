package fr.emse.test;


interface IMoney {
    IMoney addMoney(Money m);
    IMoney addMoneyBag(MoneyBag mb);
	IMoney add(IMoney m);
}