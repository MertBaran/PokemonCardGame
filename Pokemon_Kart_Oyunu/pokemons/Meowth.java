package pokemons;
public class Meowth extends game.Pokemon {

	//Meowth 40 Normal
	private int hasarPuani;
	private boolean kartKullanildiMi;
	public Meowth() {
		super("Meowth","Normal",9,"/images/cards/Meowth.png");
		this.kartKullanildiMi = false;
		this.hasarPuani = 40;
	}

    public Meowth(int hasarPuani, boolean kartKullanildiMi) {
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
