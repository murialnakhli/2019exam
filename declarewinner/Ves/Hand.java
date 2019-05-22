

import java.io.Serializable;
import java.util.Random;

public class Hand implements Serializable {

	private static final long serialVersionUID = 1L;
	private char hand;

	public Hand(char hand) {
		this.hand = hand;
	}

	public Hand() {
		char[] hands = { 'r', 'p', 's' };
		this.hand = hands[new Random().nextInt(hands.length)];
	}

	public boolean validHand() {
		if (hand == 's' || hand == 'p' || hand == 'r')
			return true;

		return false;
	}

	public char getHand() {
		return this.hand;
	}

}
