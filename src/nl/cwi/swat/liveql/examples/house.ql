form Form1040 {

  // Income
 "Wages, salaries, tips etc." wages: int
 "Taxable interest" interest: int
 "Total income" totalInc: int (wages + interest)

  // Adjusted gross income

 "Educator expenses:" edu: int
 "Moving expenses:" moving: int
 "Total expenses:" totalExp: int(edu + moving)

 "Adjusted gross income" adjIncome: int(totalInc - totalExp)
 
 // Tax and Credits
 
 "Deductions:" deduct: int
 "Exemptions:" exempt: int
 
 if (exempt > adjIncome - deduct) {
   "Taxable income" taxableInc: int(0)
 }
 else {
   "Taxable income" taxableInc: int(adjIncome - deduct - exempt)
 }
 
 "Forms 8814:" forms8814: bool
 "Form 4972:" form4972: bool
 "962 election:" election: bool
 
 "Tax:" tax: int
 "Alternative minimum tax": altMinTax: int

 "Total taxes:" totalTax: int(tax + altMinTax)
 
 "Total credits:" totalCred: int(47 + ... 53
 if (totalCred > line46) {
   endResult: int(0)
 }
 else {
   endResult: int(totalTax - totalCred);
 }

}