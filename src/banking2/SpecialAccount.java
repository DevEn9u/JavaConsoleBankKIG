package banking2;

public class SpecialAccount extends NormalAccount {

	int dCnt;
	
	public SpecialAccount(String accountNum, String name, int balance,
			int baseRate) {
		super(accountNum, name, balance, baseRate);
		this.dCnt = 0;
	}
	
	public int getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(int baseRate) {
		this.baseRate = baseRate;
	}
	
	public int getdCnt() {
		return dCnt;
	}

	public void setdCnt(int dCnt) {
		this.dCnt = dCnt;
	}

	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.printf("입금 %d회차%n", getdCnt());
		System.out.println("-------------");
	}
	@Override
	public void deposit(int money) {
		setdCnt(dCnt + 1);
		if (this.dCnt % 2 == 0) {
			int bRate = this.getBalance() * this.baseRate / 100;
			int amount = this.getBalance() + bRate + money + 500;
			this.setBalance(amount);
		}
		else {
			super.deposit(money);			
		}
	}
}
