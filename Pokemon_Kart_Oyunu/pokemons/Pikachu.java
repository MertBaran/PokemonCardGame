package pokemons;
public class Pikachu extends game.Pokemon {

	private int hasarPuani;
	private boolean kartKullanildiMi;
	public Pikachu() {
		super("Pikachu","Elektrik",0,"/images/cards/Pikachu.png");
		this.kartKullanildiMi = false;
		this.hasarPuani = 40;
	}
	
	public Pikachu(String pokemonAdi, String pokemonTipi, int hasarPuani) {	
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
