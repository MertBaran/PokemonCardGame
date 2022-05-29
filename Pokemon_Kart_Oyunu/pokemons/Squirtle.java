package pokemons;
//Squirtle 30 Su
public class Squirtle extends game.Pokemon {
	private int hasarPuani;
	private boolean kartKullanildiMi;
	public Squirtle() {
		
		super("Squirtle","Su",3,"/images/cards/Squirtle.png");
		this.kartKullanildiMi = false;
		this.hasarPuani = 30;
	}

    public Squirtle(int hasarPuani, boolean kartKullanildiMi) {
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
