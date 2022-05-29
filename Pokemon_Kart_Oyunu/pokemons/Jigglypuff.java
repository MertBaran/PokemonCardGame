package pokemons;
public class Jigglypuff extends game.Pokemon {

	//Jigglypuff 70 Ses
	private int hasarPuani;
	private boolean kartKullanildiMi;
	public Jigglypuff() {
		super("Jigglypuff","Ses",8,"/images/cards/Jigglypuff.png");
		this.kartKullanildiMi = false;
		this.hasarPuani = 70;
	}

    public Jigglypuff(int hasarPuani, boolean kartKullanildiMi) {
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
