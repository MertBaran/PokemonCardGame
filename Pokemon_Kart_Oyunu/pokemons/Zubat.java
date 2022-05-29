package pokemons;
//Zubat 50 Hava
public class Zubat extends game.Pokemon {
	private int hasarPuani;
	private boolean kartKullanildiMi;
	public Zubat() {
		super("Zubat","Hava",4,"/images/cards/Zubat.png");
		this.kartKullanildiMi = false;
		this.hasarPuani = 50;
	}

    public Zubat(int hasarPuani, boolean kartKullanildiMi) {
        this.hasarPuani = hasarPuani;
        this.kartKullanildiMi = kartKullanildiMi;
    }

	
        @Override
	public boolean isKartKullanildiMi() {
		return kartKullanildiMi;
	}
	
        @Override
	public void setKartKullanildiMi(boolean kartKullanildiMi) {
		this.kartKullanildiMi = kartKullanildiMi;
	}
        @Override
	public int HasarPuaniGoster() {
		return hasarPuani;
	}
        
}
