package wow;

public class Warrior extends Character {

    int maxRage = 100;
    int currentRage = 999999;

    int baseMeleeDamage = 2000;
    double critChance = 0.25;

    Warrior(String warriorName) {
        name = warriorName;
    }

    void mortalStrike(Character character) {
        int msRageCost = 30;

        if (currentRage < msRageCost) {
            System.out.println("Warning: NOT ENOUGH RAGE!");
            return;
        }
        currentRage = currentRage - msRageCost;
        if (currentRage < 0) {
            currentRage = 0;
        }

        int damageAmount = baseMeleeDamage;
        double roll = Math.random();
        if (roll <= critChance) {
            damageAmount = damageAmount * 2;
        }

        character.currentHp = character.currentHp - damageAmount;

        System.out.println(name + " mortal strike deals " + damageAmount + " damage");
    }

}
