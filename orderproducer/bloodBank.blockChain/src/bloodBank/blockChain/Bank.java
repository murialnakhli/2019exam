package bloodBank.blockChain;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bank {
	
	
	public PrivateKey privateKey;
	public PublicKey publicKey;
	
	public HashMap<String,DonationOutput> UTXOs = new HashMap<String,DonationOutput>();
	
	
	public Bank(){
		generateKeyPair();	
	}
	
	
	public void generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			
			keyGen.initialize(ecSpec, random);   
	        	KeyPair keyPair = keyGen.generateKeyPair();
	        	
	        	privateKey = keyPair.getPrivate();
	        	publicKey = keyPair.getPublic();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public float getBalance() {
		float total = 0;	
        for (Map.Entry<String, DonationOutput> item: BlockChain.UTXOs.entrySet()){
        	DonationOutput UTXO = item.getValue();
            if(UTXO.isMine(publicKey)) { //if output belongs to me ( if coins belong to me )
            	UTXOs.put(UTXO.id,UTXO); //add it to our list of unspent transactions.
            	total += UTXO.value ; 
            }
        }  
		return total;
	}
	
	public Donation sendFunds(PublicKey _recipient,float value ) {
		if(getBalance() < value) { //gather balance and check funds.
			System.out.println("#Not Enough funds to send transaction. Transaction Discarded.");
			
		
		return null;
	}
	
	
	ArrayList<DonationInput> inputs = new ArrayList<DonationInput>();
    
	float total = 0;
	
	for (Map.Entry<String, DonationOutput> item: UTXOs.entrySet()){
		DonationOutput UTXO = item.getValue();
		total += UTXO.value;
		inputs.add(new DonationInput(UTXO.id));
		if(total > value) break;
	}
	
	Donation newDonation = new Donation(publicKey, _recipient , value, inputs);
	newDonation.generateSignature(privateKey);
	
	for(DonationInput input: inputs){
		UTXOs.remove(input.DonationOutputId);
	}
	return newDonation;
}

}
