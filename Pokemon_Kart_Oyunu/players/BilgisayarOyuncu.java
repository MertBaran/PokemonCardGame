package players;

import game.Pokemon;
import java.util.Random;

public class BilgisayarOyuncu  extends game.Oyuncu {
    private int type = 0; // PC 0 
    private static int id = 1;

    public BilgisayarOyuncu(){
        super(id,"Bilgisayar "+ String.valueOf(id),0);
        id++;
    }
        public BilgisayarOyuncu(int type, int id){
        this.type = type;
        this.id = id;
    }
    
    
    @Override
    public Pokemon kartSec() {
        
        

    Random r=new Random(); //random sınıfı
    int a=r.nextInt(super.kartListesi().size());
    while(super.kartListesi().get(a).isKartKullanildiMi() == true)
    {
     a=r.nextInt(super.kartListesi().size());
    }
        
       return super.kartListesi().get(a); 
    }
    
}
