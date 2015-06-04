package music;

public class Synchronizer {

	public Synchronizer(boolean leadVoiceFlag) {
		super();
		this.leadVoiceFlag = leadVoiceFlag;
	}

	private boolean leadVoiceFlag;
	
	public synchronized void singLeadLine(String line, long delay) {
		while(!leadVoiceFlag) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		singOneLine(line, delay);
	}
	
	public synchronized void singBackingLine(String line, long delay) {
		while(leadVoiceFlag) {  // dok prvi glas peva, ja cekam
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		singOneLine(line, delay);
	}
	
	private void singOneLine(String line, long delay) {
		try {
			wait(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(line);
		leadVoiceFlag = !leadVoiceFlag;
		notifyAll();
	}

	public boolean isLeadVoiceFlag() {
		return leadVoiceFlag;
	}

	public void setLeadVoiceFlag(boolean leadVoiceFlag) {
		this.leadVoiceFlag = leadVoiceFlag;
	}
}
