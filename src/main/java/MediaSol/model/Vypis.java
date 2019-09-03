package MediaSol.model;

import java.text.*;
import java.util.*;

/***********************************************************************************************
 * Každá instance této třídy uchovává všechny informace o výpisu, který byl načten ze souboru GPC.
 */

public class Vypis {

    public static final SimpleDateFormat FORMAT_GPC = new SimpleDateFormat("ddMMyy");
    public static final SimpleDateFormat FORMAT_OUTPUT = new SimpleDateFormat("dd.MM.yyyy");

    private	String	cisloUctu	;
    private	String	majitel	;
    private Date    datPocZustatek	;
    private	double	pocZustatek	;
    private	double	konZustatek	;
    private	double	debPolozky	;
    private	double	krePolozky	;
    private	int	    porCislo	;
    private	Date	datVypis	;

    private List<Transakce> transList = new ArrayList <Transakce>();

    public Vypis() {
    }
    
    public String getCisloUctu() {
        return cisloUctu;
    }

    public void setCisloUctu(String newValue) {
        this.cisloUctu = newValue;
    }

    public String getMajitel() {
        return majitel;
    }

    public void setMajitel(String newValue) {
        this.majitel = newValue;
    }

    public Date getDatPocZustatek() {
        return datPocZustatek;
    }

    public void setDatPocZustatek(String newValue) {
        try {

            Date date = FORMAT_GPC.parse(newValue);
            this.datPocZustatek=date;

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public double getPocZustatek() {
        return pocZustatek;
    }

    public void setPocZustatek(double newValue) {
        this.pocZustatek = newValue;
    }

    public double getKonZustatek() {
        return konZustatek;
    }

    public void setKonZustatek(double newValue) {
        this.konZustatek = newValue;
    }

    public double getDebPolozky() {
        return debPolozky;
    }

    public void setDebPolozky(double newValue) {
        debPolozky = newValue;
    }

    public double getKrePolozky() {
        return krePolozky;
    }

    public void setKrePolozky(double newValue) {
        this.krePolozky = newValue;
    }

    public int getPorCislo() {
        return porCislo;
    }

    public void setPorCislo(int newValue) {
        this.porCislo = newValue;
    }

    public Date getDatVypis() {
        return datVypis;
    }

    public void setDatVypis(String newValue) {
        try {

            Date date = FORMAT_GPC.parse(newValue);
            this.datVypis=date;

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<Transakce> getTransList() {
        return transList;
    }

    @Override
    public String toString () {
        String vypis_hlavicka =
                "Informace o výpisu z účtu: \n" +
                "Číslo účtu: "	+	this.cisloUctu		+"\n"+
                "Majitel: "	+	this.majitel		+"\n"+
                "Datum počátečního zůstatku: "	+	FORMAT_OUTPUT.format(this.datPocZustatek)		+"\n"+
                "Počáteční zůstatek: "	+	this.pocZustatek		+"\n"+
                "Koncový zůstatek: "	+	this.konZustatek		+"\n"+
                "Suma debetních (odchozích) položek: "	+	this.debPolozky		+"\n"+
                "Suma kreditních (příchozích) položek: "	+	this.krePolozky		+"\n"+
                "Pořadové číslo výpisu: "	+	this.porCislo		+"\n"+
                "Datum výpisu: "	+	FORMAT_OUTPUT.format(this.datVypis)
                +"\n\n"+
                "Transakce: \n\n";

        String vypis =  vypis_hlavicka;

                for (Transakce trans : this.getTransList()){
                    vypis =  vypis+trans.toString();
                }
                ;
        return vypis;
    }
}
