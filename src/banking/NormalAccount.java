package banking;

// 보통예금계좌: 최소한의 이자를 지급하는 자율 입출금식 계좌
public class NormalAccount  extends Account {

	// 확장한 멤버변수: 이율
	int baseRate;
	// 생성자를 통해 이율정보를 초기화할 수 있도록 정의
	public NormalAccount(String accountNum, String name, int balance,
			int baseRate) {
		super(accountNum, name, balance);
		this.baseRate = baseRate;
	}
	
	// 정보출력 메서드 오버라이딩
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본 이자%: " + baseRate + "%");
		System.out.println("-------------");
	}
	
	// 이자 계산 메서드 오버라이딩
//	@Override
//	public void deposit(int money) {
//		balance = balance + (balance * baseRate / 100) + money;
//	}
	public void deposit(int money) {
		int bRate = this.getBalance() * this.baseRate / 100;
		int amount = this.getBalance() + bRate + money;
		this.setBalance(amount);
	}
}
