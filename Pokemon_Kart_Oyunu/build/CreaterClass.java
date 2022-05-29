package build;

import game.Oyuncu;
import game.Pokemon;
import java.util.ArrayList;
import java.util.Collections;
import players.BilgisayarOyuncu;
import players.InsanOyuncu;

/**
 *
 * @author engin
 */
public class CreaterClass {

    private ArrayList<Pokemon> cardList = new ArrayList<>();
    private ArrayList<Pokemon> playerOneList = new ArrayList<>();
    private ArrayList<Pokemon> playerTwoList = new ArrayList<>();
    public ArrayList<Pokemon> desk = new ArrayList<>();
    players.BilgisayarOyuncu bot = new BilgisayarOyuncu();
    private players.InsanOyuncu player = new InsanOyuncu();
    private players.BilgisayarOyuncu player2 = new BilgisayarOyuncu();

    private int gameType;

    CreaterClass() {
        this.gameType = 0;

    }

    public CreaterClass(int gameType) {
        this.gameType = gameType;
    }

    public void cards() {
        pokemons.Bulbasaur bulbasaur = new pokemons.Bulbasaur();
        pokemons.Butterfree butterfree = new pokemons.Butterfree();
        pokemons.Charmander charmander = new pokemons.Charmander();
        pokemons.Jigglypuff jigglypuff = new pokemons.Jigglypuff();
        pokemons.Meowth meowth = new pokemons.Meowth();
        pokemons.Pikachu pikachu = new pokemons.Pikachu();
        pokemons.Psyduck psyduck = new pokemons.Psyduck();
        pokemons.Snorlax snorlax = new pokemons.Snorlax();
        pokemons.Squirtle squirtle = new pokemons.Squirtle();
        pokemons.Zubat zubat = new pokemons.Zubat();

        cardList.add(bulbasaur);
        cardList.add(butterfree);
        cardList.add(charmander);
        cardList.add(jigglypuff);
        cardList.add(meowth);
        cardList.add(pikachu);
        cardList.add(psyduck);
        cardList.add(snorlax);
        cardList.add(squirtle);
        cardList.add(zubat);
        // Kartlar rastgele sıralanır.
        Collections.shuffle(cardList);
        distributeCards();


    }

    public void restart() {
        cardList.removeAll(cardList);
        playerOneList.removeAll(playerOneList);
        playerTwoList.removeAll(playerTwoList);
        desk.removeAll(desk);
        /*
        System.out.println(cardList.toString());
        System.out.println(playerOneList.toString());
        System.out.println(playerTwoList.toString());
        System.out.println(desk.toString());
         */

    }

    public void distributeCards() {
        for (int i = 0; i < 3; i++) {

            bot.listeyeKartEkle(cardList.get(i));
        }
        if (gameType == 0) {
            for (int i = 3; i < 6; i++) {
                player2.listeyeKartEkle(cardList.get(i));
            }
        } else if (gameType == 1) {
            for (int i = 3; i < 6; i++) {
                player.listeyeKartEkle(cardList.get(i));
            }
        }
        for (int i = 6; i < 10; i++) {
            desk.add(cardList.get(i));

        }
    }

    public InsanOyuncu getPlayer() {
        return player;
    }

    public BilgisayarOyuncu getComputer() {
        return player2;
    }

    public BilgisayarOyuncu getBot() {
        return bot;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public int getGameType() {
        return gameType;
    }

}
