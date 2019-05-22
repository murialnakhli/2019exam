package bloodBank.blockChain;

import java.security.*;
import java.util.ArrayList;

public class Donation {
	
	public String DonationId; // this is also the hash of the transaction.
	public PublicKey sender; // senders address/public key.
	public PublicKey reciepient; // Recipients address/public key.
	public float value;
	public byte[] signature; // this is to prevent anybody else from spending funds in our wallet.
	
	public ArrayList<DonationInput> inputs = new ArrayList<DonationInput>();
	public ArrayList<DonationOutput> outputs = new ArrayList<DonationOutput>();
	
	private static int sequence = 0; // a rough count of how many transactions have been generated. 
	
	// Constructor: 
	public Donation(PublicKey from, PublicKey to, float value,  ArrayList<DonationInput> inputs) {
		this.sender = from;
		this.reciepient = to;
		this.value = value;
		this.inputs = inputs;
	}
	
	// This Calculates the transaction hash (which will be used as its Id)
	private String calulateHash() {
		sequence++; 
		return DigitalSignature.applySha256(
				DigitalSignature.getStringFromKey(sender) +
				DigitalSignature.getStringFromKey(reciepient) +
				Float.toString(value) + sequence
				);
	}
	
	public void generateSignature(PrivateKey privateKey) {
		String data = DigitalSignature.getStringFromKey(sender) + DigitalSignature.getStringFromKey(reciepient) + Float.toString(value)	;
		signature = DigitalSignature.applyECDSASig(privateKey,data);		
	}

	

	public boolean verifiySignature() {
		String data = DigitalSignature.getStringFromKey(sender) + DigitalSignature.getStringFromKey(reciepient) + Float.toString(value)	;
		return DigitalSignature.verifyECDSASig(sender, data, signature);
	}
public boolean processDonation() {
		
		if(verifiySignature() == false) {
			System.out.println("#Transaction Signature failed to verify");
			return false;
		}
		
				
		//gather transaction inputs (Make sure they are unspent):
		for(DonationInput i : inputs) {
			i.UTXO = BlockChain.UTXOs.get(i.DonationOutputId);
		}

		//check if transaction is valid:
		if(getInputsValue() < BlockChain.minimumDonation) {
			System.out.println("#Transaction Inputs to small: " + getInputsValue());
			return false;
		}
		
		//generate transaction outputs:
		float leftOver = getInputsValue() - value; //get value of inputs then the left over change:
		DonationId = calulateHash();
		outputs.add(new DonationOutput( this.reciepient, value,DonationId)); //send value to recipient
		outputs.add(new DonationOutput( this.sender, leftOver,DonationId)); //send the left over 'change' back to sender		
				
		//add outputs to Unspent list
		for(DonationOutput o : outputs) {
			BlockChain.UTXOs.put(o.id , o);
		}
		
		//remove transaction inputs from UTXO lists as spent:
		for(DonationInput i : inputs) {
			if(i.UTXO == null) continue; //if Transaction can't be found skip it 
			BlockChain.UTXOs.remove(i.UTXO.id);
		}
		
		return true;
	}
	
//returns sum of inputs(UTXOs) values
	public float getInputsValue() {
		float total = 0;
		for(DonationInput i : inputs) {
			if(i.UTXO == null) continue; //if Transaction can't be found skip it 
			total += i.UTXO.value;
		}
		return total;
	}

//returns sum of outputs:
	public float getOutputsValue() {
		float total = 0;
		for(DonationOutput o : outputs) {
			total += o.value;
		}
		return total;
	}

	

}
