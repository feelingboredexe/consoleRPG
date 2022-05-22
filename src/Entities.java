import java.util.Random;

public class Entities {
	int maxHealth, health;
	int level;
	int damage;
	int expMultiplier;
	double critDamage = 0.5;
	int critChance = 5;
	String name;
	Abilities[] abilityList = new Abilities[5];

	public int takeDmg(int attackerDamage, Random damageMultiplier, int critChance, double critDamage) {
		int crit = damageMultiplier.nextInt(100) + 1; // Generate number between 1 and 100
		double critMultiplier = 1; // Crit multiplier that is calculated with crit damage
		if (crit <= critChance) { // If the crit number is less than or equal to the crit chance
			System.out.println("Critical hit!");
			critMultiplier += critDamage; // Modify crit multiplier to have added crit damage onto it
		}
		int takenDamage = attackerDamage * (int)((((int)((0.5 + 1.3 * damageMultiplier.nextDouble()) + 0.5)) * critMultiplier) + 0.5);
		health -= takenDamage;
		return takenDamage;
	}
}

class Player extends Entities {
	int exp; // player experience
	Items[] items = new Items[100]; // player items
	Weapons weapon; // Equipped weapon
	int itemIndex = 0; // The amount of items in their inventory
	boolean guarding; // Whether or not they are guarding

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
		critChance = 5; // Reset crit values
		critDamage = 0.5;
		if (weapon == null) {
			damage = level;
		} else {
			damage = weapon.damage;
			critChance += weapon.critChance; // Adds on the bonus crit values on items to the character
			critDamage += weapon.critDamage;
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
		health = (int) (0.3 * ((mobLevel - 1) * (mobLevel - 1))) + 5;
		damage = (int) (0.03 * (0.7* ((mobLevel - 1) * (mobLevel - 1)))) + 1;
		level = mobLevel;
		expMultiplier = 1;
		name = "Slime";
	}
}
