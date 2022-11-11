package wow;

public class Duel {

    Duel() {

    }

    void startDuel() {

        Druid narki = new Druid("Narki");
        Warrior wailer = new Warrior("Wailer");

        while (true) {

            if (narki.currentHp < narki.maxHp / 2) {
                narki.healingTouch();
            } else {
                narki.wrath(wailer);
            }

            if (wailer.currentHp <= 0) {
                System.out.println("GAME OVER! " + wailer.name + " IS DEAD!");
                System.exit(0);
            }

            wailer.mortalStrike(narki);

            if (narki.currentHp <= 0) {
                System.out.println("GAME OVER! " + narki.name + " IS DEAD!");
                System.exit(0);
            }

        }

    }


}
