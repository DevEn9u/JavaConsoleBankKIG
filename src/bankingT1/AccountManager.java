package bankingT1;

import java.util.HashSet;
import java.util.Scanner;

public class AccountManager {

//	public static Account accArr[] = new Account[50];
//	public static int numOfAcc = 0;
	
	// 기존 인스턴스 배열을 컬렉션 기반으로 변경
	HashSet<Account> hashSet = new HashSet<Account>();
	
	// 메뉴 호출
	public void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1. 계좌개설 ");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 전체 계좌 정보 출력");
		System.out.println("5. 프로그램 종료");
	};
	// 계좌 개설
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		System.out.println("***신규계좌개설***");
		System.out.print("계좌번호: ");
		String aNum = scan.nextLine();
		System.out.print("고객이름: ");
		String aName = scan.nextLine();
		System.out.print("잔고: ");
		int aBal = scan.nextInt();
		scan.nextLine();

		// 입력받은 정보를 토대로 Account 인스턴스 생성
		Account account = new Account(aNum, aName, aBal);
		// 계좌 개설 이후 인스턴스 배열에 계좌 추가
//		accArr[numOfAcc++] = account;
		
		// 컬렉션에 인스턴스 추가
		boolean yn = hashSet.add(account);
		if (yn == true) {
			System.out.println("입력 완료");
		}
		else {
			System.out.println("중복된 인스턴스가 발견되었습니다.");
			System.out.println("덮어쓸까요? (y / n)");
			String con = scan.nextLine();
			if(con.equalsIgnoreCase("y")) {
				// 덮어쓰기 진행
				/*
				 * 새롭게 인력한 인스턴스로 기존에 저장된 인스턴스를 삭제한다.
				   우리의 입장에서는 서로 다른 인스턴스이지만 Set의 입장에서는
				   hashCode() 메서드와 equals()메서드에 의해 동일한 인스턴스이므로
				   삭제가 가능하다.
				 */
				hashSet.remove(account);
				// 새롭게 입력한 인스턴스를 추가한다.
				hashSet.add(account);
			}
			else {
				// 무시
			}
		}
//		System.out.println("계좌개설이 완료되었습니다.");
	};
	// 입금
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("***입   금***");
		System.out.print("계좌번호: ");
		String aNum = scan.nextLine();
		System.out.print("입금액: ");
		int aBal = scan.nextInt();
		System.out.println("입금이 완료되었습니다.");
	};
	// 출금
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String aNum = scan.nextLine();
		System.out.print("출금액: ");
		int aBal = scan.nextInt();
		System.out.println("출금이 완료되었습니다.");
	};
	// 계좌정보출력
	public void showAccInfo() {
		for(Account ac : hashSet) {
			ac.showAccInfo();
		}
	}
	

}
