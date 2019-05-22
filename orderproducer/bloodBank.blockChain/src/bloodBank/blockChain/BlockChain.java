package bloodBank.blockChain;

import java.security.Security;
import java.util.ArrayList; 
import com.google.gson.GsonBuilder;
import java.util.Base64;
import java.util.HashMap;

public class BlockChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static HashMap<String,DonationOutput> UTXOs = new HashMap<String,DonationOutput>();
	
	public static int difficulty = 3;
	public static float minimumTransaction = 0.1f;
	public static Bank bankA;
	public static Bank bankB;
	public static Donation fristDonation ; //genesisTransaction

	public static void main(String[] args) {	
		//add our blocks to the blockchain ArrayList:
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); //Setup Bouncey castle as a Security Provider
		
		//Create wallets:
		bankA = new Bank();
		bankB = new Bank();		
		Bank coinbase = new Bank();
		
		//create genesis transaction, which sends 100 NoobCoin to walletA: 
		fristDonation = new Donation(coinbase.publicKey, bankA.publicKey, 100f, null);
		fristDonation.generateSignature(coinbase.privateKey);	 //manually sign the genesis transaction	
		fristDonation.DonationId = "0"; //manually set the transaction id
		fristDonation.outputs.add(new DonationOutput(fristDonation.reciepient, fristDonation.value, fristDonation.DonationId)); //manually add the Transactions Output
		UTXOs.put(fristDonation.outputs.get(0).id, fristDonation.outputs.get(0)); //its important to store our first transaction in the UTXOs list.
		
		System.out.println("Creating and Mining Genesis block... ");
		Block genesis = new Block("0");
		genesis.addDonation(fristDonation);
		addBlock(genesis);
		
		//testing
		Block block1 = new Block(genesis.hash);
		System.out.println("\nWalletA's balance is: " + bankA.getBalance());
		System.out.println("\nWalletA is Attempting to send funds (40) to WalletB...");
		block1.addDonation(bankA.sendFunds(bankB.publicKey, 40f));
		addBlock(block1);
		System.out.println("\nWalletA's balance is: " + bankA.getBalance());
		System.out.println("WalletB's balance is: " + bankB.getBalance());
		
		Block block2 = new Block(block1.hash);
		System.out.println("\nWalletA Attempting to send more funds (1000) than it has...");
		block2.addDonation(bankA.sendFunds(bankB.publicKey, 1000f));
		addBlock(block2);
		System.out.println("\nWalletA's balance is: " + bankA.getBalance());
		System.out.println("WalletB's balance is: " + bankB.getBalance());
		
		Block block3 = new Block(block2.hash);
		System.out.println("\nWalletB is Attempting to send funds (20) to WalletA...");
		block3.addDonation(bankB.sendFunds( bankA.publicKey, 20));
		System.out.println("\nWalletA's balance is: " + bankA.getBalance());
		System.out.println("WalletB's balance is: " + bankB.getBalance());
		
		isChainValid();
		
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		HashMap<String,DonationOutput> tempUTXOs = new HashMap<String,DonationOutput>(); //a temporary working list of unspent transactions at a given block state.
		tempUTXOs.put(fristDonation.outputs.get(0).id, fristDonation.outputs.get(0));
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("#Current Hashes not equal");
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("#Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("#This block hasn't been mined");
				return false;
			}
			
			//loop thru blockchains transactions:
			DonationOutput tempOutput;
			for(int t=0; t <currentBlock.donations.size(); t++) {
				Donation currentDonation = currentBlock.donations.get(t);
				
				if(!currentDonation.verifiySignature()) {
					System.out.println("#Signature on Transaction(" + t + ") is Invalid");
					return false; 
				}
				if(currentDonation.getInputsValue() != currentDonation.getOutputsValue()) {
					System.out.println("#Inputs are note equal to outputs on Transaction(" + t + ")");
					return false; 
				}
				
				for(DonationInput input: currentDonation.inputs) {	
					tempOutput = tempUTXOs.get(input.donationOutputId);
					
					if(tempOutput == null) {
						System.out.println("#Referenced input on Transaction(" + t + ") is Missing");
						return false;
					}
					
					if(input.UTXO.value != tempOutput.value) {
						System.out.println("#Referenced input Transaction(" + t + ") value is Invalid");
						return false;
					}
					
					tempUTXOs.remove(input.donationOutputId);
				}
				
				for(DonationOutput output: currentDonation.outputs) {
					tempUTXOs.put(output.id, output);
				}
				
				if( currentDonation.outputs.get(0).reciepient != currentDonation.reciepient) {
					System.out.println("#Transaction(" + t + ") output reciepient is not who it should be");
					return false;
				}
				if( currentDonation.outputs.get(1).reciepient != currentDonation.sender) {
					System.out.println("#Transaction(" + t + ") output 'change' is not sender.");
					return false;
				}
				
			}
			
		}
		System.out.println("Blockchain is valid");
		return true;
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}