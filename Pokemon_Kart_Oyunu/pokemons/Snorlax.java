package pokemons;
//Snorlax 30 Normal
public class Snorlax extends game.Pokemon {
	private int hasarPuani;
	private boolean kartKullanildiMi;
	public Snorlax() {
		super("Snorlax","Normal",6,"/images/cards/Snorlax.png");
		this.kartKullanildiMi = false;
		this.hasarPuani = 30;
	}

    public Snorlax(int hasarPuani, boolean kartKullanildiMi, String pokemonAdi, String pokemonTipi, int pokemonID, String image) {
        super(pokemonAdi, pokemonTipi, pokemonID, image);
        this.hasarPuani = hasarPuani;
        this.kartKullanildiMi = kartKullanildiMi;
    }

	
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
