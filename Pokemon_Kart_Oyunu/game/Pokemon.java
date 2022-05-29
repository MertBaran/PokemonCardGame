package game;

public class Pokemon {
	private String pokemonAdi;
	private String pokemonTipi;
	private int pokemonID = 999;//Not Found
        private boolean kartKullanildiMi;
        private String image;
        
	
	public Pokemon(String pokemonAdi, String pokemonTipi,int pokemonID,String image) {
		this.pokemonID = pokemonID;
		this.pokemonAdi = pokemonAdi;
		this.pokemonTipi = pokemonTipi;
                this.image = image;
	}

	public Pokemon() {
		
	}
	public boolean isKartKullanildiMi() {
		return false;
	}
        public String isImage() {
		return image;
	}
	
	public void setKartKullanildiMi(boolean kartKullanildiMi) {
		this.kartKullanildiMi = kartKullanildiMi;
	}
	public String getPokemonAdi() {
		return pokemonAdi;
	}
	public String getPokemonTipi() {
		return pokemonTipi;
	}
        public int HasarPuaniGoster() {
		return 0;
	}
	@Override
	public String toString() {
		return "\nPokemon [pokemonAdi= " + pokemonAdi + ",   pokemonTipi= " + pokemonTipi + ",   pokemonID= " + pokemonID + "]\n";
	}

	

	
	
	
	
}
