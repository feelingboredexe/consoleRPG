import java.util.Random;
import java.util.Scanner;

public class GameMethods {

	public static Entities[] createAdventure(Random mobMaker, int level) {
		Entities[] hostileMonsters;
		int mobCount, mobType;
		// Generates random amount of monsters to fight
		if(level <= 1) {
			mobCount = mobMaker.nextInt(2) + 1;
		} else {
			mobCount = mobMaker.nextInt(3) + 1;
		}
		// Sets object array to size of monster count
		hostileMonsters = new Entities[mobCount];
		for (int i = 0; i < mobCount; i++) {
			// Makes a new monster each time
			mobType = mobMaker.nextInt(1) + 1;
			if (mobType == 1) { // Incomplete monster type creator
				hostileMonsters[i] = new Slime(level - 1);
			} else {
				hostileMonsters[i] = new Slime(level - 1);
			}
		}
		System.out.println("On your adventure you encountered " + mobCount + " monster(s)!" + "\nPrepare to fight!");
		return hostileMonsters;
	}
	
	public static void playerTurn(Scanner input, Entities[] targets, Player player, Random damageMultiplier) {
		char action, userTarget;
		int mobCount = targets.length, targetIndex;
		String targetList = "";
		player.guarding = false;
		// Menu
		while(true) {
			System.out.println("What do you do? " + "\n[0] - Attack" + "\n[1] - Guard" + "\n[2] - Ability" + "\n[3] - Item");
			action = input.next().charAt(0);
			if(action == '0') {
				for (int i = 0; i < targets.length; i++) {
					targetList = targetList + String.valueOf(i + 1) + " ";
				}
				while(true) {
					System.out.println("What enemy are you targeting? [ " + targetList + "]");
					userTarget = input.next().charAt(0);
					if(userTarget < '0' || userTarget > '9') {
						System.out.println("Please enter a valid number.");
					} else {
						targetIndex = (int)userTarget - 49;
						if(targetIndex > mobCount || targetIndex < 0) {
							System.out.println("Please target a valid monster.");
						} else if(targets[targetIndex].health <= 0) {
							System.out.println("That target is dead.");
						} else {
							break;
						}
					}
				}
				// Set player damage if they change weapons
				player.setDamage();
				System.out.println("You did " + targets[targetIndex].takeDmg(player.damage, damageMultiplier, player.critChance, player.critDamage) + " to the " + targets[targetIndex].name);
				if(targets[targetIndex].health <= 0) {
					// Tells the player that their target is dead
					System.out.println("The " + targets[targetIndex].name + " is dead.");
				}
				break;
			} else if(action == '1') {
				System.out.println("You are now guarding!");
				player.guarding = true;
				break;
			} else {
				System.out.println("Sorry, that feature hasn't been implemented yet!");
			}
		}
	}
	
	public static void monsterTurn(Entities[] monsters, Player player, Random damageMultiplier) {
		// Boolean to track if the monster ends their turn prematurely through the use of an ability
		boolean endTurn = false;
		for(Entities monster: monsters) {
			if (player.health <= 0) {
				break;
			}
			for(Abilities ability: monster.abilityList) {
				if (!(ability == null)) {
					if(ability.castable) {
						endTurn = true;
						break;
					}
				}
			}
			if (!endTurn && monster.health > 0) {
				if (player.guarding) { // Is player guarding
					System.out.println("The " + monster.name + " did " + player.takeDmg(monster.damage/2, damageMultiplier, 1, 0.5) + " to you!");
				} else {
					System.out.println("The " + monster.name + " did " + player.takeDmg(monster.damage, damageMultiplier, 1, 0.5) + " to you!");
				}
				System.out.println("You have " + player.health + " health");
			}
		}
	}
	
	public static void forge(Scanner input, Player player) {
		char action;
		// Menu to select actions
		System.out.println("What would you like to do at the forge? " + "\n[0] - Check self");
		action = input.next().charAt(0);
		if (action == '0') {
			player.inspectSelf();
		} else if (action == '1') {

		}
	}

}
