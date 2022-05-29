package pokemons;
//Psyduck 20 Su
public class Psyduck extends game.Pokemon {
	private int hasarPuani;
	private boolean kartKullanildiMi;
	public Psyduck() {
		super("Psyduck","Su",5,"/images/cards/Psyduck.png");
		this.kartKullanildiMi = false;
		this.hasarPuani = 20;
	}

    public Psyduck(int hasarPuani, boolean kartKullanildiMi) {
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
