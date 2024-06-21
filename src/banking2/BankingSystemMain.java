package banking2;

import java.util.InputMismatchException;
import java.util.Scanner;

// 개발자 정의 예외 클래스: 메뉴 번호 입력 예외
class MenuInputNumberErrorException extends Exception {
	public MenuInputNumberErrorException() {
		super("1 ~ 7사이의 수를 입력하세요.");
	}
}

public class BankingSystemMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		 * haseSet으로 인해 항목 자동 생성으로 인해 의미없어 생성자와 인스턴스 생성 코드 주석처리
		// 여러 계좌 정보를 저장하기 위해 인스턴스 배열을 이용
		// 항목이 50개인 Account 인스턴스를 저장할 수 있는 배열
		 */
		AccountManager accManager = new AccountManager();
		AutoSaver as = null;
		
		accManager.loadAccounts();
		
		while(true) {
			try {
				accManager.showMenu();
				int choice = scan.nextInt();
				scan.nextLine();
				if (choice < 1 || choice > 7) {
					throw new MenuInputNumberErrorException();
				}
				switch (choice) {
				case ICustomDefine.MAKE: 
					accManager.makeAccount(choice);
					break;
				case ICustomDefine.DEPOSIT:
					accManager.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					accManager.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE:
					accManager.showAccInfo();
					break;
				case ICustomDefine.DELETE:
					accManager.deleteAccount();
					break;
				case ICustomDefine.AUTO_SAVE:
					try {
						if (!as.isAlive()) {
							as = new AutoSaver(accManager);
						}
					}
					catch (Exception e) {
						System.out.println("AutoSaver 인스턴스 생성되지 않음");
						as = new AutoSaver(accManager);
					}
					accManager.autoSaveOption(as);
					break;
				case ICustomDefine.EXIT:
					accManager.saveAccounts();
					System.out.println("프로그램 종료");
					return;
				}
			}
			catch (InputMismatchException e) {
				System.out.println("(여기 메세지 수정 필요)메뉴는 정수로만 입력하세요.");
				scan.nextLine();
			}
			catch (MenuInputNumberErrorException e) {
				System.out.println("메뉴 입력 예외 발생");
				System.out.println(e.getMessage());
				scan.nextLine();
			}
		}
	}
	
}
