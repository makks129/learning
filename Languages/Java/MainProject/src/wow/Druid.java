package wow;

public class Druid extends Character {


    int maxMana = 50000;
    int currentMana = 999999999;

    int baseSpellDamage = 1000;
    double critChance = 0.3;

    Druid(String druidName) {
        name = druidName;
    }

    void healingTouch() {
        int htManaCost = 500;

        if (currentMana < htManaCost) {
            System.out.println("Warning: LOW MANA!");
            return;
        }
        currentMana = currentMana - htManaCost;
        if (currentMana < 0) {
            currentMana = 0;
        }

        int healAmount = baseSpellDamage * 2;
        double roll = Math.random();
        if (roll <= critChance) {
            healAmount = healAmount * 2;
        }

        currentHp = currentHp + healAmount;
        if (currentHp > maxHp) {
            currentHp = maxHp;
        }

        System.out.println(name + " HT heals " + healAmount + " hp");
    }

    void wrath(Character character) {
        int wrathManaCost = 1000;

        if (currentMana < wrathManaCost) {
            System.out.println("Warning: LOW MANA!");
            return;
        }
        currentMana = currentMana - wrathManaCost;
        if (currentMana < 0) {
            currentMana = 0;
        }

        int damageAmount = baseSpellDamage;
        double roll = Math.random();
        if (roll <= critChance) {
            damageAmount = damageAmount * 2;
        }

        character.currentHp = character.currentHp - damageAmount;

        System.out.println(name + " wrath deals " + damageAmount + " damage");
    }


}
