package banking2;

import java.io.Serializable;

abstract class Account implements Serializable {
	private String accountNum;
	private String name;
	private int balance;
	
	public Account() {};

	public Account(String accountNum, String name, int balance) {
		this.accountNum = accountNum;
		this.name = name;
		this.balance = balance;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("-------------");
		System.out.println("계좌번호: " + accountNum);
		System.out.println("고객이름: " + name);
		System.out.println("잔고: " + balance);
	};
	
	// 입금 이후 이자계산을 위한 메서드
	public void deposit(int money) {}
	
	// 중복값 처리를 위한 hashCode(), equals() 메서드
	@Override
	public int hashCode() {
		return accountNum.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		Account acc = (Account) obj;
		if (acc.accountNum.equals(this.accountNum)) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public String toString() {
		String str = 
				"-----------------\n" 
				+ "계좌번호: " + accountNum + "\n"
				+ "이름" + name + "\n"
				+ "잔고" + balance;
		return str;
	}
}

