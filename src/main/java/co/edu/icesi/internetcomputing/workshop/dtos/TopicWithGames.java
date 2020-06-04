package co.edu.icesi.internetcomputing.workshop.dtos;

import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

public class TopicWithGames {
	
	private TsscTopic tsscTopic;
	
	private long totalGames;
	
	public TopicWithGames(TsscTopic tsscTopic, long totalGames) {
		this.tsscTopic = tsscTopic;
		this.totalGames = totalGames;
	}

	public TsscTopic getTsscTopic() {
		return tsscTopic;
	}

	public void setTsscTopic(TsscTopic tsscTopic) {
		this.tsscTopic = tsscTopic;
	}

	public long getTotalGames() {
		return totalGames;
	}

	public void setTotalGames(long totalGames) {
		this.totalGames = totalGames;
	}
	
	
}
