package banking2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

class DepositMeasurementException extends Exception {
	public DepositMeasurementException() {
		super("입금/출금 금액은 500원 단위로 가능합니다. ex) 1000원, 2500원");
	}
}
class WithdrawMeasurementException extends Exception {
	public WithdrawMeasurementException() {
		super("입금/출금 금액은 1000원 단위로 가능합니다. ex) 1000원, 2000원");
	}
}

public class AccountManager {

	//멤버변수
	//	private Account accArr[];
	// accArr에 저장된 계좌의 수를 카운트 하는용, 추가할 때마다 1씩 증가할 예정.
	//	private int numOfAcc;
	/*
	 * 위 멤버변수는 기존 인스턴스 배열에 저장했던 것, 4단계부터는 컬렉션으로 변경
	   여기서는 HashSet 이용
	 */
	Scanner scan = new Scanner(System.in);
	HashSet<Account> accountSet = new HashSet<Account>();

	/* 
	 * 4단계에서 hashSet을 만들기 때문에 주석처리
	 * hashSet은 크기가 자동으로 설정되기 때문에 처음 조건인 '50개의 배열 생성'이 의미없음
	 */
	//생성자
	//	public AccountManager(int num) {
	//		 정보 저장을 위한 인스턴스배열(Account 타입) 생성
	//		accArr = new Account[num];
	//		numOfAcc = 0;
	//	}

	// 메뉴 호출
	public void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1. 계좌개설 ");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 전체 계좌 정보 출력");
		System.out.println("5. 계좌 정보 삭제");
		System.out.println("6. 저장");
		System.out.println("7. 프로그램 종료");
	};

	// 계좌 개설
	public void makeAccount(int choice) {
		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택-----");
		System.out.println("1. 보통계좌");
		System.out.println("2. 신용신뢰계좌");
		System.out.println("3. 특판계좌");

		// 보통계좌, 신용계좌를 선택하는 번호
		int sel = scan.nextInt();
		// scan.nextInt() 이후에 생기는 버퍼 제거
		scan.nextLine();
		System.out.print("계좌번호: ");
		String aNum = scan.nextLine();
		System.out.print("고객이름: ");
		String aName = scan.nextLine();
		System.out.print("잔고: ");
		int aBal = scan.nextInt();
		scan.nextLine();

		Account acc = null;

		// 입력선택(select)에 따라 일반계좌 or 신용계좌로 나뉜다
		switch (sel) {
		case 1: 
			// 일반계좌는 기본이자를 입력받는다.
			System.out.println("기본 이자율(%)(정수형태로입력): ");
			int aBaseRate = scan.nextInt();
			scan.nextLine();
			// NormalAccount 타입의 일반계좌 인스턴스를 만들고
			acc = new NormalAccount(
					aNum, aName, aBal, aBaseRate);
			// 계좌 배열에 추가한다.
			//			accountSet.add(acc);
			//			accArr[numOfAcc++] = normal;
			break;
		case 2:
			try {
				// 신용신뢰계좌는 기본이좌를 입력받는다.
				System.out.println("기본 이자율(%)(정수형태로입력): ");
				// Account a1 = new HighCreditAccount("111-111", "김", 1000, 1000, "1");
				int aBaseRateHigh = scan.nextInt();
				scan.nextLine();
				System.out.println("신용등급(A,B,C등급): ");
				String aRating = scan.nextLine().toUpperCase();

				// 예외 처리: 신용등급이 A, B, C가 아닌 경우 예외 발생
				if (!aRating.equals("A") && !aRating.equals("B") && !aRating.equals("C")) {
					throw new CreditRateException();
				}

				// HighCreditAccount 타입의 일반계좌 인스턴스를 만들고
				acc = new HighCreditAccount(
						aNum, aName, aBal, aBaseRateHigh, aRating);
			}
			catch (InputMismatchException e) {
				System.out.println("정수를 입력하세요.");
			}
			catch (CreditRateException e) {
				System.out.println(e.getMessage());
			}
			// 계좌 배열에 추가한다.
			//			accountSet.add(acc);
			break;
		case 3:
			// 특판계좌는 기본이자를 추가로 
			System.out.println("기본 이자율(%)(정수형태로입력): ");
			aBaseRate = scan.nextInt();
			scan.nextLine();
			// NormalAccount 타입의 일반계좌 인스턴스를 만들고
			acc = new SpecialAccount(
					aNum, aName, aBal, aBaseRate);
			break;
		default: 
			System.out.println("1, 2, 3중 하나를 입력하세요.");
		}

		boolean isAdded = accountSet.add(acc);
		if (isAdded == true) {
			System.out.println("계좌 개설이 완료되었습니다.");
		}
		else {
			System.out.println("중복된 계좌를 발견하였습니다. 덮어쓸까요? (y / n)");
			String answer = scan.nextLine();

			if (answer.equalsIgnoreCase("y")) {
				accountSet.remove(acc);
				accountSet.add(acc);
				System.out.println("계좌를 덮어쓰고 개설하였습니다.");
			}
			else {
				System.out.println("계좌 개설을 취소하였습니다.");
			}
		}
		//		System.out.println("계좌개설이 완료되었습니다.");
	}

	// 입금 메서드
	public void depositMoney() {
		try {
			System.out.println("***입   금***");
			System.out.print("계좌번호: ");
			String aNum = scan.nextLine();
			System.out.print("입금액: ");
			int addBal = scan.nextInt();
			scan.nextLine();
			
			if(addBal % 500 == 0) {
				// 입금시 deposit 메서드에 입력된 이자 계산 실행
			}
			// 입금액이 500원 단위가 아닐때 예외 발생
			else {
				throw new DepositMeasurementException();
			}

			if(addBal > 0) {
				boolean found = false;
				for (Account acc : accountSet) {
					if(acc.getAccountNum().equals(aNum)) {
						acc.deposit(addBal);
						System.out.println("입금이 완료되었습니다.");
						found = true;
						return;
					}
				}
				if (!found) {
					System.out.println("해당 계좌가 존재하지 않습니다.");
				}
			}
			// 음수 금액 또는 0원 입금시 입금불가
			else {
				System.out.println("0원 또는 음수액은 입금이 불가합니다.");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("정수를 입력하세요.");
		}
		catch (DepositMeasurementException e) {
			System.out.println(e.getMessage());
		}

	}
	// 출금
	public void withdrawMoney() {
		try {
			System.out.println("***출   금***");
			System.out.println("계좌번호와 출금할 금액을 입력하세요.");
			System.out.print("계좌번호: ");
			String aNum = scan.nextLine();
			System.out.print("출금액: ");
			int withBal = scan.nextInt();
			scan.nextLine();

			if (withBal > 0) {
				for (Account acc : accountSet) {
					if (acc.getAccountNum().equals(aNum)) {
						if (withBal % 1000 == 0) {
							if(withBal > acc.getBalance()) {
								System.out.println("잔고가 부족합니다. 금액 전체를 출금할까요?");
								System.out.println("YES : 금액전체 출금처리");
								System.out.println("NO : 출금요청취소");
								String answer = scan.nextLine();
								// equalsIgnoreCase()는 대소문자 구분 없이 동일 유무 확인
								if (answer.equalsIgnoreCase("yes")) {
									acc.setBalance(0);
								}
								else if(answer.equalsIgnoreCase("no")) {
									System.out.println("출금이 취소되었습니다.");
									return;
								}
								else {
									System.out.println("다시 입력하세요.");
									return;
								}
							}
							else {
								int a = acc.getBalance() - withBal;
								acc.setBalance(a);
								System.out.println("출금이 완료되었습니다.");
								return;
							}
						}
						// 출금액이 1000원 단위가 아닐때 예외 발생
						else {
							throw new WithdrawMeasurementException();
						}
					}
					else {
						System.out.println("해당 계좌가 존재하지 않습니다.");
					}
				}
			}
			// 0원 또는 음수 출금시 예외 발생
			else {
				System.out.println("0원 또는 음수액은 출금이 불가합니다.");
			}
		}
		catch (InputMismatchException e) {
			System.out.println("정수를 입력하세요.");
		}

		catch (WithdrawMeasurementException e) {
			System.out.println(e.getMessage());
		}
	}

	public void showAccInfo() {
		for(Account acc : accountSet) {
			acc.showAccInfo();				
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	};

	public void deleteAccount() {

		System.out.println("삭제할 계좌의 계좌번호를 입력하세요.");
		String delAccNum = scan.nextLine();
		//		int delIdx = -1;

		Iterator<Account> itr = accountSet.iterator();
		while (itr.hasNext()) {
			Account acc = itr.next();
			if(delAccNum.equals(acc.getAccountNum())) {
				//				accountSet.remove(acc);
				itr.remove();
				System.out.println("삭제하려는 계좌를 찾았습니다.");
				System.out.println(delAccNum + " 계좌가 삭제되었습니다.");
				saveAccounts();
				//				delIdx++;
				break;
			}
		}
		System.out.println("계좌가 존재하지 않습니다.");
		//		if(delIdx != -1) {
		//		}
	}

	// 직렬화하여 파일 정보를 객체에 저장하는 메서드와 불러오는 메서드 정의
	public void saveAccounts() {
		try {
			// 인스턴스를 파일로 저장하기 위한 출력스트림
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("src/banking/AccountInfo.obj"));
			// accountSet을 파일로 저장
			out.writeObject(accountSet);
			System.out.println("계좌 정보를 저장했습니다.");
		}
		catch (IOException e) {
			System.out.println("정보 저장중 오류가 발생했습니다." +
					e.getMessage());
		}
	}  
	@SuppressWarnings("unchecked")
	public void loadAccounts() {
		try {
			// 인스턴스를 파일로 저장하기 위한 출력스트림
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("src/banking/AccountInfo.obj"));
			// accountSet을 파일로 저장
			accountSet = ((HashSet<Account>)in.readObject());
		}
		catch (IOException | ClassNotFoundException e) {
			System.out.println("정보 저장중 오류가 발생했습니다." +
					e.getMessage());
		}
	}

	public void saveToFile() {
		// try-with-resources 사용 try(리소스초기화){} catch(Exception e){}
		try (PrintWriter out = new PrintWriter(new 
				/*
				 * FileWriter에 인자로 true를 기술하면 파일을 덮어씌우는 것이 아니라
					   기존 파일의 맨 끝에 추가하는 방식이 된다. 
				 */
				FileWriter("src/banking/AutoSaveAccount.txt", true))) {
			// 계좌 정보 저장
			for (Account acc : accountSet) {
				// 계좌정보를 AutoSaveAccount.txt에 기록
				//				out.println(acc);
				out.println("-------------");
				out.println("계좌번호: " + acc.getAccountNum());
				out.println("고객이름: " + acc.getName());
				out.println("잔고: " + acc.getBalance());
				if (acc instanceof NormalAccount) {
					NormalAccount norAcc = (NormalAccount) acc;
					out.println("기본 이자(%): " +
							norAcc.getBaseRate() + "%");
				}
				else if (acc instanceof HighCreditAccount) {
					HighCreditAccount highAcc = (HighCreditAccount) acc;
					out.println("기본 이자(%): " +
							highAcc.getBaseRate() + "%");
					out.println("신용등급(A, B, C등급): " +
							highAcc.getCreditRating());
				}
				out.println("-------------");
			}
			System.out.println("자동 저장 완료");
			out.close();
		}
		catch (IOException e) {
			System.out.println("파일 저장 중 예외 발생" + e.getMessage());
		}
	}

	public void autoSaveOption(AutoSaver as) {
		// 쓰레드 출력 메서드 여기에 쓰고
		System.out.println("저장 옵션을 선택하세요.");
		System.out.println("1. 자동저장 On");
		System.out.println("2. 자동저장 Off");
		int menu = scan.nextInt();
		if(menu == 1) {
			if (!as.isAlive()) {
				as.setDaemon(true);
				as.start();
				System.out.println("자동저장을 시작합니다.");
			}
			else {
				System.out.println("자동저장이 이미 실행중입니다.");
			}
		}
		else if(menu == 2) {
			if(as.isAlive()) {
				as.interrupt();
				System.out.println("자동저장을 종료합니다.");
			}
		}
		else {
			System.out.println("메뉴 입력을 잘못하셨습니다.");
		}
	}

}
