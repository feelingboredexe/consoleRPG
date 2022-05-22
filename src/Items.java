abstract class Items {
    int rarity; // 4 tiers of item rarity, 0 - Common, 1 - Uncommon, 2 - Rare, 3 - Legendary
    String name;
}

abstract class Weapons extends Items {
    int damage;
    String damageType;
    int critChance;
    double critDamage;
}

class BasicSword extends Weapons {
    public BasicSword() {
        damage = 2;
        damageType = "Physical";
        rarity = 0;
        name = "Training Sword";
        critChance = 0;
        critDamage = 0;
    }
}

class ChippedKnife extends Weapons {
    public ChippedKnife() {
        damage = 1;
        damageType = "Physical";
        rarity = 0;
        name = "Chipped Knife";
        critChance = 15;
        critDamage = 0.4;
    }
}
class BoxingGloves extends Weapons {
    public BoxingGloves() {
        damage = 1;
        critChance = 31;
        rarity = 1;
        critDamage = 0.15;
        damageType = "Physical";
        name = "Boxing Gloves";
    }
}

class Myrtenaster extends Weapons {
    public Myrtenaster() {
        damage = 1;
        critChance = 60;
        rarity = 3;
        damageType = "Magical";
        name = "Myrtenaster";
        critDamage = 0.25;
    }
}