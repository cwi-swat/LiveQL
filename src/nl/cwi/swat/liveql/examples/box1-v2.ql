
form Box1HouseOwning {
  "Hello world?" 
     hasSoldHouse: bool
  "Did you enter a loan for maintenance?" 
     hasMaintLoan: bool
  "Did you by a house in 2010?" 
     hasBoughtHouse: bool
     
  if (!hasSoldHouse) {
    "Private debts for the sold house:" 
       privateDebt: int
    "Price the house was sold for:" 
       sellingPrice: int
    "Value residue:" 
       valueResidue: int (sellingPrice - privateDebt)
  }
}
