package pokemons;
/*
 * Charmander 60 Ates
 */
public class Charmander extends game.Pokemon {

	private int hasarPuani;
	private boolean kartKullanildiMi;

	
        public Charmander() {
		super("Charmander","Ates",2,"/images/cards/Chamender.png");
		this.kartKullanildiMi = false;
		this.hasarPuani = 60;
	}

    public Charmander(int hasarPuani, boolean kartKullanildiMi) {
        this.hasarPuani = hasarPuani;
        this.kartKullanildiMi = kartKullanildiMi;
    }



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

