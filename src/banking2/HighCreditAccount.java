package banking2;


public class HighCreditAccount extends Account {

	// 확장한 멤버변수: 이율, 신용등급, 신용이자
	int baseRate;
	String creditRating;
	int creditRate;
	
	// 생성자를 통해 이율정보를 초기화할 수 있도록 정의
	public HighCreditAccount(String accountNum, String name, int balance,
			int baseRate, String creditRating) {
		super(accountNum, name, balance);
		this.baseRate = baseRate;
		this.creditRating = creditRating;
		this.creditRate = 0;
	}
	

	public int getBaseRate() {
		return baseRate;
	}


	public void setBaseRate(int baseRate) {
		this.baseRate = baseRate;
	}


	public String getCreditRating() {
		return creditRating;
	}


	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}


	public int getCreditRate() {
		return creditRate;
	}


	public void setCreditRate(int creditRate) {
		this.creditRate = creditRate;
	}


	// 정보출력 메서드 오버라이딩
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		switch (creditRating) {
		 case "A":
			 setCreditRate(7);
			 break;
		 case "B":
			 setCreditRate(4);
			 break;
		 case "C":
			 setCreditRate(2);
			 break;
		default:

			return;
		}
		System.out.println("기본 이자%: " + baseRate + "%");
		System.out.println("신용등급(A, B, C등급): " + creditRating);
		System.out.println("-------------");
	}
	
	@Override
	public void deposit(int money) {
//		int baseRate = this.getBalance() * this.getBalance() / 100;
		int cRate = this.getBalance() * this.creditRate / 100;
		int amount = this.getBalance() + baseRate + cRate + money;
		this.setBalance(amount);
	}
	@Override
	public String toString() {
		super.toString();
		String str =  super.toString()
				+ ("신용등급(A, B, C등급): "  + getCreditRating());
		return str;
	}
}
