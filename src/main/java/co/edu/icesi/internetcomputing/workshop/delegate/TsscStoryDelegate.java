package co.edu.icesi.internetcomputing.workshop.delegate;

import co.edu.icesi.internetcomputing.workshop.model.TsscStory;

public interface TsscStoryDelegate {
	
	public Iterable<TsscStory> getAllStories();

	public TsscStory addStory(TsscStory story);

	public TsscStory getStory(Long id);
}
