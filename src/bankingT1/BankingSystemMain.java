package bankingT1;

import java.util.Scanner;

import banking.ICustomDefine;

public class BankingSystemMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AccountManager am = new AccountManager();
		
		while(true) {
			am.showMenu();
			int choice = scan.nextInt();
			switch (choice) {
			case ICustomDefine.MAKE: 
				am.makeAccount();
				break;
			case ICustomDefine.DEPOSIT:
				am.depositMoney();
				break;
			case ICustomDefine.WITHDRAW:
				am.withdrawMoney();
				break;
			case ICustomDefine.INQUIRE:
				am.showAccInfo();
				break;
			case ICustomDefine.EXIT:
				System.out.println("프로그램 종료");
				return;
			}
		}
	}

}
