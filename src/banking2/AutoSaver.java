package banking2;

public class AutoSaver extends Thread {

	AccountManager am;

	public AutoSaver(AccountManager am) {
		this.am = am;
	}

	@Override
	public void run() {
		try {
			while (true) {
				// txt파일로 자동저장하는 메서드 실행
				am.saveToFile();
				// 5초마다 자동 저장
				sleep(5000);
			}
		}
		// Interrupt() 메서드로 발생한 예외 처리
		catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
