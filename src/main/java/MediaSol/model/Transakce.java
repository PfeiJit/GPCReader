package MediaSol.model;

import java.text.*;
import java.util.*;

/***********************************************************************************************
 * Každá instance této třídy uchovává všechny informace o každé transakci, která byla načtena ze souboru GPC.
 */

public class Transakce {

    private	String	cisloUctuPS	;
    private	int	    typ	;
    private	String	ID	;
    private	double	castka	;
    private	int	    kod	;
    private	String	varSymbol	;
    private	String	kodBankaPS	;
    private	String	konstSymbol	;
    private	String	specSymbol	;
    private Date    datValuty	;
    private	String	nazevPS	;
    private	String	mena	;
    private	Date	datZauctovani	;



    public Transakce() {
    }

    public String getCisloUctuPS() {
        return cisloUctuPS;
    }

    public void setCisloUctuPS(String newValue) {
        cisloUctuPS = newValue;
    }

    public int getTyp() {
        return typ;
    }

    public void setTyp(int newValue) {
        typ = newValue;
    }

    public String getID() {
        return ID;
    }

    public void setID(String newValue) {
        ID = newValue;
    }

    public double getCastka() {
        return castka;
    }

    public void setCastka(double newValue) {
        castka = newValue;
    }

    public int getKod() {
        return kod;
    }

    public void setKod(int newValue) {
        kod = newValue;
    }

    public String getVarSymbol() {
        return varSymbol;
    }

    public void setVarSymbol(String newValue) {
        varSymbol = newValue;
    }

    public String getKodBankaPS() {
        return kodBankaPS;
    }

    public void setKodBankaPS(String newValue) {
        kodBankaPS = newValue;
    }

    public String getKonstSymbol() {
        return konstSymbol;
    }

    public void setKonstSymbol(String newValue) {
        konstSymbol = newValue;
    }

    public String getSpecSymbol() {
        return specSymbol;
    }

    public void setSpecSymbol(String newValue) {
        specSymbol = newValue;
    }

    public Date getDatValuty() {
        return datValuty;
    }

    public void setDatValuty(String newValue) {
        try {

            Date date = Vypis.FORMAT_GPC.parse(newValue);
            this.datValuty=date;

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getNazevPS() {
        return nazevPS;
    }

    public void setNazevPS(String newValue) {
        nazevPS = newValue;
    }

    public String getMena() {
        return mena;
    }

    public void setMena(String newValue) {
        mena = newValue;
    }

    public Date getDatZauctovani() {
        return datZauctovani;
    }

    public void setDatZauctovani(String newValue) {
        try {

            Date date = Vypis.FORMAT_GPC.parse(newValue);
            this.datZauctovani=date;

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString () {
        String transakce =
                "Číslo účtu: "			+	this.cisloUctuPS		+"\n"+
                "Typ transakce: "			+	this.typ		+"\n"+
                "Identifikátor transakce: "			+	this.ID		+"\n"+
                "Částka zaúčtované transakce: "			+	this.castka		+"\n"+
                "Kód účtování: "			+	this.kod		+"\n"+
                "Variabilní symbol: "			+	this.varSymbol		+"\n"+
                "Kód banky protistrany: "			+	this.kodBankaPS		+"\n"+
                "Konstantní symbol: "			+	this.konstSymbol		+"\n"+
                "Specifický symbol: "			+	this.specSymbol		+"\n"+
                "Datum valuty: "			+	Vypis.FORMAT_OUTPUT.format(this.datValuty)		+"\n"+
                "Název protistrany: "			+	this.nazevPS		+"\n"+
                "Číselný kód měn: "			+	this.mena		+"\n"+
                "Datum zaúčtování: "			+	Vypis.FORMAT_OUTPUT.format(this.datZauctovani)	+"\n\n";

        return transakce;
    }
}

