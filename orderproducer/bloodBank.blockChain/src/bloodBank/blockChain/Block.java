package bloodBank.blockChain;
import java.util.ArrayList;

import java.util.Date;

public class Block {
	
	public String hash; // to hold the digital signature
	public String previousHash; // to hold the previous block’s hash 
	private String data; // to hold the block data
	private long timeStamp; 
	private int nonce;
	public String merkleRoot;
	public ArrayList<Donation> donations = new ArrayList<Donation>();

	
	public Block(String previousHash ) {
		
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
	}
	
	public String calculateHash() {
		String calculatedhash = DigitalSignature.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				merkleRoot
				);
		return calculatedhash;
	}
	
	

	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
		
	}
	public boolean addDonation(Donation donation) {
		//process transaction and check if valid, unless block is genesis block then ignore.
		if(donation == null) return false;		
		if((previousHash != "0")) {
			if((donation.processDonation() != true)) {
				System.out.println("Transaction failed to process. Discarded.");
				return false;
			}
		}
		donation.add(donation);
		System.out.println("Transaction Successfully added to Block");
		return true;
	}

}
