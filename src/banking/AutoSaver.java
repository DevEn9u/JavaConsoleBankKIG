package banking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class AutoSaver extends Thread {
	private HashSet<Account> accountSet;
	private boolean running;

	public AutoSaver(HashSet<Account> accountSet, boolean running) {
		this.accountSet = accountSet;
		this.running = true;
		// 데몬쓰레드로 설정
		setDaemon(true);
	}

	@Override
	public void run() {
		while(true) {
			// 자동저장 메서드 실행
			saveToFile();
			try {
				// 5초마다 자동 저장
				sleep(5000);
			}
			// Interrupt() 메서드로 발생한 예외 처리
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	// 데몬쓰레드로 파일 저장하는 메서드
	public void saveToFile() {
		try {
			PrintWriter out = new PrintWriter(new 
					FileWriter("src/ex20io/AutoSaveAccount.txt"));
			// 계좌 정보 저장
			for (Account acc : accountSet) {
				// 계좌정보를 AutoSaveAccount.txt에 기록
				out.println(acc.toString());
			}
			out.close();
		}
		catch (IOException e) {
			System.out.println("파일 저장 중 예외 발생" + e.getMessage());
		}
	}
	// 쓰레드 종료 메서드
	public void shutDown() {
		running = false;
		interrupt();
	}
}
