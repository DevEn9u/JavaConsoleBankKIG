package bankingT2;

import java.util.Scanner;

public class BankingSystemMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AccountManager am = new AccountManager();
		
		AutoSaver as = null;
		
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
			case ICustomDefine.AUTO_SAVE:
				// 파일저장 확인하기
//				am.saveInfoTxt();
				
				try {
					/*
					 * 인스턴스가 생성되지 않은 상태에서 isAlive()를 호출하면
					   예외가 발생하므로 catch절에서 인스턴스를생성한다.
					 */
					if(!as.isAlive()) {
						as = new AutoSaver(am);
					}
				}
				catch (Exception e) {
					System.out.println("AutoSaver 인스턴스 생성되지 않음");
					as = new AutoSaver(am);
				}
				
				// 매니저 클래스의 메서드를 호출하면서 쓰레드 인스턴스를 전달
				am.dataSaveOption(as);
				break;
			case ICustomDefine.EXIT:
				System.out.println("프로그램 종료");
				return;
			}
		}
	}

}
