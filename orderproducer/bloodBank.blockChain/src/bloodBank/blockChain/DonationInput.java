package bloodBank.blockChain;

public class DonationInput {
	
	public String DonationOutputId; //Reference to TransactionOutputs -> transactionId
	public DonationOutput UTXO; //Contains the Unspent transaction output
	
	public DonationInput(String DonationOutputId) {
		this.DonationOutputId = DonationOutputId;
	}

}
