package MediaSol.model;

import java.lang.management.*;
import java.util.*;

/***********************************************************************************************
 * Třída poskytuje seznam všech položek a jejich umístění na řádku v souboru GPC.
 */

public class Polozka {

    private int start;
    private int end;

    private static final HashMap<String,Polozka> polozky= new HashMap<String,Polozka>() {
        {
            put("cisloUctu"	        ,new Polozka(	3	,	19	));
            put("majitel"	        ,new Polozka(	19	,	39	));
            put("datPocZustatek"	,new Polozka(	39	,	45	));
            put("pocZustatek"	    ,new Polozka(	45	,	60	));
            put("konZustatek"	    ,new Polozka(	60	,	75	));
            put("debPolozky"	    ,new Polozka(	75	,	90	));
            put("krePolozky"	    ,new Polozka(	90	,	105	));
            put("porCislo"	        ,new Polozka(	105	,	108	));
            put("datVypis"	        ,new Polozka(	108	,	114	));
            put("cisloUctuPS"	    ,new Polozka(	19	,	35	));
            put("typ"	            ,new Polozka(	35	,	36	));
            put("ID"	            ,new Polozka(	36	,	48	));
            put("castka"	        ,new Polozka(	48	,	60	));
            put("kod"	            ,new Polozka(	60	,	61	));
            put("varSymbol"	        ,new Polozka(	61	,	71	));
            put("kodBankaPS"	    ,new Polozka(	73	,	77	));
            put("konstSymbol"	    ,new Polozka(	77	,	81	));
            put("specSymbol"	    ,new Polozka(	81	,	91	));
            put("datValuty"	        ,new Polozka(	91	,	97	));
            put("nazevPS"	        ,new Polozka(	97	,	117	));
            put("mena"	            ,new Polozka(	117	,	122	));
            put("datZauctovani"	    ,new Polozka(	122	,	128	));
        };
    };

    public static HashMap<String, Polozka> getPolozky() {
        return polozky;
    }

    public Polozka(int start,int end)     {
        this.start = start;
        this.end = end;
    }


    public int getStart() {
        return start;
    }

    public void setStart(int newValue) {
        start = newValue;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int newValue) {
        end = newValue;
    }


}
