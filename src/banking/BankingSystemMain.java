package banking;

import java.util.Scanner;

public class BankingSystemMain {
	
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1. 계좌개설 ");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 전체 계좌 정보 출력");
		System.out.println("5. 프로그램 종료");
	};
	
	public static void makeAccount() {
		Scanner scan = new Scanner(System.in);
		System.out.println("***신규계좌개설***");
		String aNum, aName, aMoney;
		System.out.print("계좌번호: "); aNum = scan.nextLine();
		System.out.print("고객이름: "); aName = scan.nextLine();
		System.out.print("잔고: "); aMoney = scan.nextLine();
		System.out.println("계좌개설이 완료되었습니다.");
	};
	// 입금
	public static void depositMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("***입   금***");
		String aNum, aMoney;
		System.out.print("계좌번호: "); aNum = scan.nextLine();
		System.out.print("입금액: "); aMoney = scan.nextLine();
		System.out.println("입금이 완료되었습니다.");
	};
	// 출금
	public static void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		String aNum, aMoney;
		System.out.print("계좌번호: "); aNum = scan.nextLine();
		System.out.print("출금액: "); aMoney = scan.nextLine();
		System.out.println("출금이 완료되었습니다.");
	};
	
	public static void showAccInfo() {
//		for(Account acc : account) {
//			
//		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 여러 계좌 정보를 저장하기 위해 인스턴스 배열을 이용		
		// 계좌정보는 최대 50개까지만 저장할 수 있다.
		Account[] account = new Account[50];
		while(true) {
			showMenu();
			int choice = scan.nextInt();
			switch (choice) {
			case 1: 
				makeAccount();
				break;
			case 2:
				depositMoney();
				break;
			case 3:
				withdrawMoney();
				break;
			case 4:
				showAccInfo();
				break;
			case 5:
				System.out.println("프로그램 종료");
				return;
			}
		}
		
	}

}
