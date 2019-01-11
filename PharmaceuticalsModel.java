package models;

public class PharmaceuticalsModel {
	private int pharmaId;
	private String pharmaName;
	private String category;
	private int quantity;
	private int threshold;
	
	public int getPharmaId() {
		return pharmaId;
	}
	public void setPharmaId(int pharmaId) {
		this.pharmaId = pharmaId;
	}
	public String getPharmaName() {
		return pharmaName;
	}
	public void setPharmaName(String pharmaName) {
		this.pharmaName = pharmaName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	
	
}
