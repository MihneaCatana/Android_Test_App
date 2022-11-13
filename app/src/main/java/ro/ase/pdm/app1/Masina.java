package ro.ase.pdm.app1;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Masina implements Serializable {

    static int id = 0;

    String marca;

    int anProductie;

    Date dataVanzare ;

    TipMasina tip;

    public Masina()
    {
       Masina.id++;
    }

    public Masina( String marca, int anProductie, Date dataVanzare , TipMasina tip) {
        Masina.id++;
        this.marca = marca;
        this.anProductie = anProductie;
        this.dataVanzare = dataVanzare;
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnProductie() {
        return anProductie;
    }

    public void setAnProductie(int anProductie) {
        this.anProductie = anProductie;
    }

    public TipMasina getTip() {
        return tip;
    }

    public void setTip(TipMasina tip) {
        this.tip = tip;
    }

    public Date getDataVanzare() {
        return dataVanzare;
    }

    public void setDataVanzare(Date dataVanzare) {
        this.dataVanzare = dataVanzare;
    }

    @Override
    public String toString() {
        return "Masina: {" +
                "marca='" + marca +
                "\n anProductie=" + anProductie +
                "\n dataVanzare=" + dataVanzare +
                "\n tip=" + tip +
                '}';
    }
}
