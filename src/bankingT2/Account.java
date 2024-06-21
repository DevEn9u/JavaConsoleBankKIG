package bankingT2;

public class Account {
	public String accountNum;
	public String name;
	public int balance;
	
	public Account() {}
	
	public Account(String accountNum, String name, int balance) {
		this.accountNum = accountNum;
		this.name = name;
		this.balance = balance;
	}
	
	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("계좌번호: " + accountNum);
		System.out.println("고객이름: " + name);
		System.out.println("잔고: " + balance);
	};
	
	
	/* 
	 * 아래 2개의 메서드를 오버라이딩 하면 set에 인스턴스를 추가할때 자동으로
	   아래 메서드를 호출하여 중복된 인스턴스인지 확인한다.
	   여기서는 계좌번호에 대한 부분만 정의한다.
	 */
	
	@Override
	public int hashCode() {
		// 계좌번호의 해시값 반환
		return accountNum.hashCode();
	};
	
	@Override
	public boolean equals(Object obj) {
		Account acc = (Account) obj;
		// 계좌번호가 동일한 값인지 확인하여 boolean 값 반환
		if (acc.accountNum.equals(this.accountNum)) {
			return true;
		}
		else 
			return false;		
	}
	
	@Override
	public String toString() {
		String str = "계좌번호: " + accountNum + ", "
				+ "이름: " + name + ", "
				+ "잔고: " + balance;
		return str;
	}
}