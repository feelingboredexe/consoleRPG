public class Items {
    int rarity;
    String name;
}

class Weapons extends Items {
    int damage;
    String damageType;
}

class BasicSword extends Weapons {
    public BasicSword(int userLevel) {
        damage = userLevel * 3;
        damageType = "Physical";
        rarity = 0;
        name = "Training Sword";
    }
}