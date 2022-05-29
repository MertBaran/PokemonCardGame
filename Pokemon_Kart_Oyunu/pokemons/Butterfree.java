package pokemons;
//Butterfree 10 Hava
public class Butterfree extends game.Pokemon {

	//Butterfree 10 Hava
	private int hasarPuani;
	private boolean kartKullanildiMi;
	public Butterfree() {
		super("Butterfree","Hava",7,"/images/cards/Butterfree.png");
		this.kartKullanildiMi = false;
		this.hasarPuani = 10;
	}

    public Butterfree(int hasarPuani, boolean kartKullanildiMi) {
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
