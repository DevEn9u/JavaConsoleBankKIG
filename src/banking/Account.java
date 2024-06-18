package banking;

class Account {
	String accountNum;
	String name;
	int money;
	
	public Account() {
		
	}

	public Account(String accountNum, String name, int money) {
		this.accountNum = accountNum;
		this.name = name;
		this.money = money;
	}

	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("계좌번호: " + accountNum);
		System.out.println("고객이름: " + name);
		System.out.println("잔고: " + money);
	};

}

