package TY_PROJECT.Programs.dto;

public class Destination {
	private String name;
	private String country;
	private String imagePath;
	public Destination(String name, String country, String imagePath, String price) {
		super();
		this.name = name;
		this.country = country;
		this.imagePath = imagePath;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	private  String price;
}
	