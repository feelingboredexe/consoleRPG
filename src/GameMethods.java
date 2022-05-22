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
	
	public static void playerTurn(Scanner input, Entities[] targets, Player player, Random damageMultiplier) { // Player's action
		char action, userTarget; // Choice of action and the target for the attack and ability action
		boolean isValidTarget, isValidAction; // Determines if the target is alive and in the field
		int mobCount = targets.length, targetIndex = 0;
		String targetList = "";
		player.guarding = false;
		// Menu
		do { // Keeps looping if invalid action is chosen
			System.out.println("What do you do? " + "\n[0] - Attack" + "\n[1] - Guard" + "\n[2] - Ability" + "\n[3] - Item");
			// Player choice
			action = input.next().charAt(0);
			if(action == '0') { // If the player chose to attack
				for (int i = 0; i < targets.length; i++) {
					targetList = targetList + String.valueOf(i + 1) + " "; // Creates a list of valid enemies to attack
				}
				do { // Loop until valid target is entered
					System.out.println("What enemy are you targeting? [ " + targetList + "]"); // Display the targets to the user
					userTarget = input.next().charAt(0);
					if (userTarget >= '0' || userTarget <= '9') { // Checks if they entered a number
						targetIndex = (int) userTarget - 49; // Sets targetIndex to the userTarget
						if (targetIndex > mobCount || targetIndex < 0) { //
							System.out.println("Please target a valid monster.");
							isValidTarget = false;
						} else if (targets[targetIndex].health <= 0) {
							System.out.println("That target is dead.");
							isValidTarget = false;
						} else { // If the target is valid and is not dead
							isValidTarget = true;
						}
					} else { // If they did not enter a number
						System.out.println("Please enter a valid number.");
						isValidTarget = false;
					}
				} while (!isValidTarget);
				// Set player damage if they change weapons
				player.setDamage();
				System.out.println("You did " + targets[targetIndex].takeDmg(player.damage, damageMultiplier, player.critChance, player.critDamage) + " to the " + targets[targetIndex].name);
				if (targets[targetIndex].health <= 0) {
					// Tells the player that their target is dead
					System.out.println("The " + targets[targetIndex].name + " is dead.");
				}
				isValidAction = true;
			} else if(action == '1') { // If the player chose to guard
				System.out.println("You are now guarding!");
				player.guarding = true;
				isValidAction = true;
			} else { // Invalid action choice
				System.out.println("Sorry, that feature hasn't been implemented yet!");
				isValidAction = false;
			}
		}while(!isValidAction); // Loops while the user has an invalid action choice
	}
	
	public static void monsterTurn(Entities[] monsters, Player player, Random damageMultiplier) {
		// Boolean to track if the monster ends their turn prematurely through the use of an ability
		boolean endTurn = false;
		for(Entities monster: monsters) { // Loop through each monster on the field
			if (player.health <= 0) { // If the player is dead stop the loop
				break;
			}
			if (monster.health > 0) { // Checks if monster is alive
				for (Abilities ability : monster.abilityList) { // Prioritizes abilities and runs through each one
					if (!(ability == null)) {
						if (ability.castable) {
							endTurn = true; // Ends the turn prematurely and casts the ability
							break;
						}
					}
				}
				if (!endTurn) { // If the monster did not end it's turn
					if (player.guarding) { // Is player guarding
						System.out.println("The " + monster.name + " did " + player.takeDmg(monster.damage / 2, damageMultiplier, monster.critChance, monster.critDamage) + " to you!");
					} else {
						System.out.println("The " + monster.name + " did " + player.takeDmg(monster.damage, damageMultiplier, monster.critChance, monster.critDamage) + " to you!");
					}
					System.out.println("You have " + player.health + " health");
				}
			}
		}
	}
	
	public static void forge(Scanner input, Player player) {
		char action;
		// Menu to select actions
		System.out.println("What would you like to do at the forge? " + "\n[0] - Check self");
		action = input.next().charAt(0);
		if (action == '0') { // If the player chooses to inspect themselves
			player.inspectSelf();
		} else if (action == '1') {

		}
	}

}
