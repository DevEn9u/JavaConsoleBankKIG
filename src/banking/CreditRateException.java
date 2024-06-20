package banking;

class CreditRateException extends Exception	 {
	public CreditRateException() {
		super("A, B, C 중 하나를 입력하세요.");	
	}
}