package LspActivity;

public class SmartPhone extends Mobile {
	@Override
	public void playMusic(String fileName) {
		System.out.println("Playing music " + fileName);
	}

	private void sendSMS() {
		System.out.println("sending sms..");
	}

	private void call() {
		System.out.println("calling");
	}

	private void playVideo(String videoFileName) {
		System.out.println("Playing video " + videoFileName);
	}

}
