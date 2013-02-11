
form Box1HouseOwning {
  "Did you sell a house in 2010?" hasSoldHouse: bool
  "Did you by a house in 2010?" hasBoughtHouse: bool
  "Did you enter a loan for maintenance?" hasMaintLoan: bool
  if (hasSoldHouse) {
    "Private debts for the sold house:" privateDebt: int
    "Price the house was sold for:" sellingPrice: int
    "Value residue:" valueResidue: int (sellingPrice - privateDebt)
    if (valueResidue > 0) {
       "Bla:" bla: int(privateDebt + sellingPrice)
    }
    else {
      "Foo:" foo: bool(true)
    } 
  }
}
