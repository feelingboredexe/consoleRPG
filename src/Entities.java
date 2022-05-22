import java.util.Random;

public class Entities {
	int maxHealth;
	int health;
	int level;
	int damage;
	int expMultiplier;
	String name;
	Abilities[] abilityList = new Abilities[5];
	
	public int takeDmg(int attackerDamage, Random damageMultiplier, int critChance, double critDamage) {
		int crit = damageMultiplier.nextInt(100) + 1;
		double critMultiplier = 1;
		if (crit <= critChance) {
			System.out.println("Critical hit!");
			critMultiplier += critDamage;
		}
		int takenDamage = attackerDamage * (int)((((int)((0.5 + 1.3 * damageMultiplier.nextDouble()) + 0.5)) * critMultiplier) + 0.5);
		health -= takenDamage;
		return takenDamage;
	}
}

class Player extends Entities {
	int exp;
	Items[] items = new Items[100];
	Weapons weapon;
	int itemIndex = 0;
	boolean guarding;
	double critDamage = 0.5;
	int critChance = 5;

	public Player(int expVal) {
		exp = expVal;
		level = (int)Math.sqrt(expVal/100) + 1; 
		name = "Player";
	}
	
	public void setMaxHealth() {
		maxHealth = (5 + (1/3)) * level + 10;
		health = maxHealth;
	}
	
	public void addExp(int expVal) {
		exp += expVal;
		level = (int)Math.sqrt(exp/100) + 1; 
	}
	
	public void addItem(Items item) {
		items[itemIndex] = item;
		itemIndex++;
	}
	
	public void equipWeapon(Weapons equipWeapon) {
		weapon = equipWeapon;
	}
	
	public void setDamage() {
		if (weapon == null) {
			damage = level;
		} else {
			damage = weapon.damage;
		}
	}

	public void inspectSelf() {
		System.out.println("Your health is " + health);
		if(weapon == null) {
			System.out.println("You currently have no weapon equipped");
		} else {
			System.out.println("You have the " + weapon.name + " equipped");
		}
		setDamage();
		System.out.println("You have " + damage + " damage");
		System.out.println("You have " + exp + " experience points");
		System.out.println("You are at level " + level);
	}
}

class Slime extends Entities {
	public Slime(int mobLevel) {
		health = (int)(0.3*((mobLevel-1)*(mobLevel-1))) + 5;
		damage = (int)(0.03*(0.7*((mobLevel - 1)*(mobLevel - 1)))) + 1;
		level = mobLevel;
		expMultiplier = 1;
		name = "Slime";
	}
}

class Items {
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
		damageType = "Slashing";
		rarity = 0;
		name = "Training Sword";
	}
}

class Abilities {
	int cooldown;
	boolean castable;
}
