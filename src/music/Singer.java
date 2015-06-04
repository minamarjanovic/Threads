package music;

public class Singer extends Thread {

	private String singerName;
	private Voice voice;
	private Performance performance;
	private boolean stop;
	private Synchronizer synch;
	
	
	public Singer(String singerName, Voice voice, Performance performance,
			boolean stop, Synchronizer synch) {
		super();
		this.singerName = singerName;
		this.voice = voice;
		this.performance = performance;
		this.stop = stop;
		this.synch = synch;
	}

	public Singer(String name, Voice voice, Performance performance) {
		super();
		this.singerName = name;
		this.voice = voice;
		this.performance = performance;
		

	}

	public Singer() {
		super();
	}
	
	public void sing(Song song, int noOfRepetitions) {
		for (int i = 0; i < noOfRepetitions; i+=2) {
			if(this.voice == Voice.LEAD) {
				System.out.println(song.getLyrics().get(i % song.getLyrics().size()));
			} else {
				if(this.voice == Voice.BACKING) {
					System.out.println(song.getLyrics().get(i % song.getLyrics().size()+1));
				
			}
			
		}
		
	}
	
}
	
	public synchronized void singWithDelay(Song song, int noOfRepetitions) {
		for (int i = 0; i < noOfRepetitions; i+=2) {
			if(this.voice == Voice.LEAD) {
				try {
					wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(song.getLyrics().get(i % song.getLyrics().size()));
			} else {
				if(this.voice == Voice.BACKING) {
					try {
						wait(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(song.getLyrics().get(i % song.getLyrics().size()+1));
				
			}
			
		}
		
	}
	
}
	
	private void sing() {
		
		Song song = this.performance.getSong();
		long delay = this.performance.getDelay();
		
		int i = 0;
		String line = null;
		
		while (!stop) {
			if(this.voice == Voice.LEAD) {
				line = song.getLyrics().get(i % song.getLyrics().size());
				synch.singLeadLine(line, delay);
			}
			if(this.voice == Voice.BACKING) {
				line = '\t' + song.getLyrics().get(i % song.getLyrics().size()+1);
				synch.singBackingLine(line, delay);
			}
			
			i += 2;
			
		}
	}
	
	@Override
	public void run() {
		sing();
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public Performance getPerformance() {
		return performance;
	}

	public void setPerformance(Performance performance) {
		this.performance = performance;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public Synchronizer getSynch() {
		return synch;
	}

	public void setSynch(Synchronizer synch) {
		this.synch = synch;
	}
	
}
