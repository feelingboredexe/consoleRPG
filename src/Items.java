public class Items {
    int rarity; // 4 tiers of item rarity, 0 - Common, 1 - Uncommon, 2 - Rare, 3 - Legendary
    String name;
}

class Weapons extends Items {
    int damage;
    String damageType;
    int critChance;
    double critDamage;
}

class BasicSword extends Weapons {
    public BasicSword() {
        damage = 3;
        damageType = "Physical";
        rarity = 0;
        name = "Training Sword";
        critChance = 0;
        critDamage = 0;
    }
}

class BoxingGloves extends Weapons {
    public BoxingGloves() {
        damage = 2;
        critChance = 31;
        rarity = 1;
        critDamage = 15;
        damageType = "Physical";
        name = "Boxing Gloves";
    }
}