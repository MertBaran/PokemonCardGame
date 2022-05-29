package players;

import game.Pokemon;

public class InsanOyuncu extends game.Oyuncu {
    private int type = 1; // Insan 1
    
    public InsanOyuncu(){
        super(0,"Oyuncu",0);
    }
        public InsanOyuncu(int type){
        this.type = type;
    }
    @Override
    public Pokemon kartSec() {
       
        return super.kartListesi().get(3);//BOŞ
    }
    
}
