form Form1040 {

  /* Income */
 "Wages, salaries, tips etc.:" wages: int
 "Taxable interest:" interest: int
 "Total income:" totalInc: int (wages + interest)

  /* Adjusted gross income */
  
  "Do you have expenses?" hasExp: bool

  if (hasExp) {
    "Educator expenses:" edu: int
    "Moving expenses:" moving: int
    "Total expenses:" totalExp: int(edu + moving)
  }
  else {
    "Total expenses:" totalExp: int(0)
  }
  
 "Adjusted gross income" adjIncome: int(totalInc - totalExp)
 
 /* Tax and Credits */
 
 "Exemptions or deductions?" hasExDed: bool
 
 if (hasExDed) {
   "Deductions:" deduct: int
   "Exemptions:" exempt: int
 }
 
 if (exempt > adjIncome - deduct) {
   "Taxable income:" taxableInc: int(0)
 }
 else {
   "Taxable income:" taxableInc: int(adjIncome - deduct - exempt)
 }
 
 "Tax:" tax: int
 "Alternative minimum tax" altMinTax: int

 "Total taxes:" totalTax: int(tax + altMinTax)
 
 "Total credits:" totalCred: int
 if (totalCred > totalTax) {
   "Owed taxes:" owed: int(0)
 }
 else {
   "Owed taxes:" owed: int(totalTax - totalCred)
 }

}