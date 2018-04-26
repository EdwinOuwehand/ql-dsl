form exampleForm {

    "Param1:"
        moneyVar:money;
    "Param2:"
        stringVar:string;
    "Param3:"
        integerVar:integer;
    "Param4:"
        booleanVar:boolean;


    "Question content 1"
        var5:integer = moneyVar + booleanVar;

    "Question content 2"
        var6:integer = moneyVar + integerVar;

    "Question content 3"
        var7:boolean = !(stringVar);

    "Question content 4"
        var8:string = moneyVar > moneyVar;

    "Question content 5"
        var9:boolean = booleanVar > booleanVar;

    "Question content 6"
        var10:boolean = booleanVar + booleanVar;
}
