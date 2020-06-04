package co.edu.icesi.internetcomputing.workshop.delegate;

import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

public interface TsscTopicDelegate {
	
	public Iterable<TsscTopic> getAllTopics();

	public TsscTopic addTopic(TsscTopic topic);

	public TsscTopic getTopic(Long id);
	
	public void removeTopic(TsscTopic topic) throws Exception;

	public void updateTopic(TsscTopic topic);
}
