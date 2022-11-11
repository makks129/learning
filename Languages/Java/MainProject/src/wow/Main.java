package wow;

public class Main {

    public static void main(String[] args) {


//        Duel narkiVsWailerDuel = new Duel();
//        narkiVsWailerDuel.startDuel();







	    Character character = new Character();
	    character.name = "Dima";
	    character.maxHp = 1000;
	    character.currentHp = 1000;

	    rename(character);

	    System.out.println(character.name);







    }


	static void rename(Character character) {
		character.name = "Petya";
	}



	static void method(Character character) {

		character.maxHp++;


	}



}
