package MediaSol.services;

import java.io.*;
import java.nio.charset.*;

import java.nio.file.*;
import java.text.*;
import java.util.*;
import org.apache.logging.log4j.util.*;
import org.springframework.core.io.*;
import org.springframework.stereotype.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.*;

import MediaSol.model.*;
import MediaSol.storage.*;

/***********************************************************************************************
 * Tato třída poskytuje metodu {@link #getVypis(File)}, která přijme jako parametr textový soubor
 * GPC a vrátí instanci objektu {@link Vypis}
 */

@Component
    public class GPCConverter {

        public static final int CHAR_DESETINNA_CAST=2;              //počet desetinných míst na konci číselného řetězce v souboru GPC
        public static final int CHAR_TYP_ZAZNAMU = 3;               //počet míst na začátku řádku, která odpovídají typu záznamu
        public static final String HLAVICKA_ID = "074";             //kod pro typ záznamu hlavička
        public static final String POHYBY_ID = "075";               //kod pro typ záznamu pohyb na účtu
        public static final int RADEK_LENGTH = 128;                 //počet znaků na řádku

        private static HashMap<String, Polozka> polozky = Polozka.getPolozky();

        private Vypis vypis;
        private Transakce trans;

        //Metoda převede číselný řetězec v souboru GPC na double
        public static double numberConvert (String cislo){
            int length = cislo.length();
            String znamenko =(cislo.substring(length - 1));
            String celaCast = cislo.substring(0,length-CHAR_DESETINNA_CAST-1);
            String desetinnaCast = cislo.substring(length-CHAR_DESETINNA_CAST-1,length-1);
            String desetinne_cislo = znamenko+celaCast+"."+desetinnaCast;
            return  Double.parseDouble(desetinne_cislo);
        }

        /***********************************************************************************************
         * Metoda přijme soubor GPC jako parametr a vrátí jednotlivé řádky souboru. Pokud je GPC soubor
         * ve špatném formátu, vyhodí výjimku.
         */
        private static List<String> readFromFile(File file)
                throws IOException {

            List<String> radky = new ArrayList<String>();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"windows-1250")))
            {
                String radek;
                while ((radek = br.readLine()) != null ) {
                    if (radek.length() == RADEK_LENGTH) {
                        radky.add(radek);}
                        else if (radek.length() == 0){}
                        else {throw new IOException();}
                }
            }
            catch (IOException e)
            {
                throw new StorageException("Soubor je ve špatném formátu");
            }
            return radky;
        }

     /***********************************************************************************************
     * Metoda přijme soubor GPC jako parametr a  a vrátí instanci objektu {@link Vypis}
     */
        public Vypis getVypis(File file) throws IOException {
            List<String> radky = readFromFile(file);

            for(String radek:radky){
               String typ_zaznamu = radek.substring(0,CHAR_TYP_ZAZNAMU);

               switch (typ_zaznamu) {

                   case HLAVICKA_ID:
                       this.vypis = new Vypis();
                       zapisHlavicku(vypis, polozky, radek);
                       break ;
                   case POHYBY_ID:
                       this.trans = new Transakce();
                       zapisTransakce(trans, polozky, radek);
                       vypis.getTransList().add(trans);
                       break;
                   default:
                       throw new StorageException("Soubor je ve špatném formátu");

               }
           }
            return vypis;
        }


        //Tato metoda přiřadí do atributů výpisu jednotlivé položky hlavičky.
        public void zapisHlavicku(Vypis vypis, HashMap<String, Polozka> polozky, String radek) {

            vypis.setCisloUctu(vytvorPolozku("cisloUctu",polozky,radek));
            vypis.setMajitel(vytvorPolozku("majitel",polozky,radek));
            vypis.setDatPocZustatek(vytvorPolozku("datPocZustatek",polozky,radek));
            vypis.setDatVypis(vytvorPolozku("datVypis",polozky,radek));

            double pocZustatek = numberConvert(vytvorPolozku("pocZustatek",polozky,radek));
            double konZustatek = numberConvert(vytvorPolozku("konZustatek",polozky,radek));
            double debPolozky = numberConvert(vytvorPolozku("debPolozky",polozky,radek));
            double krePolozky = numberConvert(vytvorPolozku("krePolozky",polozky,radek));

            int porCislo = Integer.parseInt(vytvorPolozku("porCislo",polozky,radek));

            vypis.setPocZustatek(pocZustatek);
            vypis.setKonZustatek(konZustatek);
            vypis.setDebPolozky(debPolozky);
            vypis.setKrePolozky(krePolozky);
            vypis.setPorCislo(porCislo);
            
        }

    //Tato metoda přiřadí do atributů transakce její jednotlivé položky.
    public void zapisTransakce(Transakce trans, HashMap<String, Polozka> polozky, String radek) {

            trans.setCisloUctuPS(vytvorPolozku("cisloUctuPS",polozky,radek));
            trans.setID(vytvorPolozku("ID",polozky,radek));
            trans.setVarSymbol(vytvorPolozku("varSymbol",polozky,radek));
            trans.setKodBankaPS(vytvorPolozku("kodBankaPS",polozky,radek));
            trans.setKonstSymbol(vytvorPolozku("konstSymbol",polozky,radek));
            trans.setSpecSymbol(vytvorPolozku("specSymbol",polozky,radek));
            trans.setDatValuty(vytvorPolozku("datValuty",polozky,radek));
            trans.setNazevPS(vytvorPolozku("nazevPS",polozky,radek));
            trans.setMena(vytvorPolozku("mena",polozky,radek));
            trans.setDatZauctovani(vytvorPolozku("datZauctovani",polozky,radek));

            double castka = numberConvert(vytvorPolozku("castka",polozky,radek));

            int typ = Integer.parseInt(vytvorPolozku("typ",polozky,radek));
            int kod = Integer.parseInt(vytvorPolozku("kod",polozky,radek));

            trans.setCastka(castka);
            trans.setTyp(typ);
            trans.setKod(kod);
        }

        //Tato metoda vytvoří položku z řádku v souboru GPC podle klíče v parametru key
        private String vytvorPolozku(String key, HashMap<String, Polozka> polozky, String radek)   {
            return radek.substring(polozky.get(key).getStart(),polozky.get(key).getEnd())  ;
        }
}


