import java.util.Scanner;
import java.util.Random;

public class GameInstance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char action;
		int mobHealthSum;
		int expGain;
		Entities[] hostileMonsters;
		Random mobMaker = new Random();
		Random damageMultiplier = new Random();
		Scanner input = new Scanner(System.in);
		Player memberOne = new Player(0);
		memberOne.setMaxHealth();
		System.out.println("Welcome to my turn based game!");
		System.out.println("Shit's super beta proably pre-alpha and also bad, but it'll be just fine...");
		while (true) {
			// Menu
			System.out.println("What would you like to do? " + "\n[0] - Quit" + "\n[1] - Adventure" + "\n[2] - Forge");
			// User Action
			action = input.next().charAt(0);
			if (action == '0') {
				break;
			} else if (action == '1') {
				// Reset exp gained
				expGain = 0;
				hostileMonsters = GameMethods.createAdventure(mobMaker, memberOne.level);
				do {
					// Reset monster total health
					mobHealthSum = 0;
					System.out.println("Your turn!");
					GameMethods.playerTurn(input, hostileMonsters, memberOne, damageMultiplier);
					for(Entities mob: hostileMonsters) {
						mobHealthSum += mob.health;
					}
					if (mobHealthSum > 0) {
						System.out.println("Monsters' turn!");
						GameMethods.monsterTurn(hostileMonsters, memberOne, damageMultiplier);
					}
				}while(mobHealthSum > 0 && memberOne.health > 0);
				if (mobHealthSum <= 0) {
					for (Entities monster: hostileMonsters) {
						// Get exp worth of the fight
						expGain += (monster.level+1/memberOne.level) * monster.expMultiplier * 20;
					}
					System.out.println("You won!" + "\nGained " + expGain + " exp!");
					// Add exp
					memberOne.addExp(expGain);
					// Reset player health
					memberOne.setMaxHealth();
				} else {
					System.out.println("You died..." + "\nGame over.");
					break;
				}
			} else if (action == '2') {
				GameMethods.forge(input, memberOne);
			} else {
				System.out.println("Please enter a valid action.");
			}
		}
		input.close();
	}

}
