package pokemons;

//Bulbasaur 50 Cim
public class Bulbasaur extends game.Pokemon {
	private int hasarPuani;
	private boolean kartKullanildiMi;
	
	
	public Bulbasaur() {
		super("Bulbasaur","Cim",1,"/images/cards/Bulbasaur.png");
		this.kartKullanildiMi = false;
		this.hasarPuani = 50;
	}
	
	public Bulbasaur(String pokemonAdi, String pokemonTipi, int hasarPuani) {	
	}
	
        @Override
	public boolean isKartKullanildiMi() {
		return kartKullanildiMi;
	}
	
	public void setKartKullanildiMi(boolean kartKullanildiMi) {
		this.kartKullanildiMi = kartKullanildiMi;
	}
        @Override
	public int HasarPuaniGoster() {
		return hasarPuani;
	}
        
        
	

}



