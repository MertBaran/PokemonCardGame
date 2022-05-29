package game;

import java.util.ArrayList;

public abstract class Oyuncu {

    private int oyuncuID;
    private String oyuncuAdi;
    private ArrayList<Pokemon> cardList = new ArrayList<>();
    private int Skor;

    public Oyuncu() {

    }

    public Oyuncu(int oyuncuID, String oyuncuAdi, int Skor) {
        this.oyuncuID = oyuncuID;
        this.oyuncuAdi = oyuncuAdi;
        this.Skor = Skor;
    }
    
    public abstract Pokemon kartSec();
    
    public int getOyuncuID() {
        return oyuncuID;
    }

    public void setOyuncuID(int oyuncuID) {
        this.oyuncuID = oyuncuID;
    }

    public String getOyuncuAdi() {
        return oyuncuAdi;
    }

    public void setOyuncuAdi(String oyuncuAdi) {
        this.oyuncuAdi = oyuncuAdi;
    }

    public int getSkor() {
        return Skor;
    }

    public void setSkor(int skor) {
        this.Skor += skor;
    }
    public void resetSkor()
    {
        this.Skor = 0;
    }
    

    public void listeyeKartEkle(Pokemon a) {
        cardList.add(a);
    }
    public void listedenKartCikart(int a) {
        cardList.remove(a);
    }
    public ArrayList<Pokemon> kartListesi() {
        return cardList;
    }
}
