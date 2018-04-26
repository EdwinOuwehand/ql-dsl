form taxOfficeExample {
  "Did you sell a house in 2010?"
    hasSoldHouse: boolean
  "Did you buy a house in 2010?"
    hasBoughtHouse: boolean
  "Did you enter a loan?"
    hasMainLoan: boolean

  if (hasSoldHouse) {
    "What was the selling price?"
      sellingPrice: money
    "Private debts for the sold house:"
      privateDebt: money
    "Value residue:"
      valueResidue: money =
        (sellingPrice - privateDebt)
  }

    "Param1:"
      x:boolean;
    "Param2:"
      y:boolean;
    "Param3:"
      z:integer;

      "What is the meaning of life" answer: string

      if (x && y || (z > 1)) {
          "What is NOT the meaning of life" invAnswer: string

      } else {
          if (!(x && y || 1)){
              "Thrid" other: string
          }
      }
}
