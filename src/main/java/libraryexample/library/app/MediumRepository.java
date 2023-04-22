package dtu.library.app;

import java.util.stream.Stream;

import dtu.library.domain.Medium;

public interface MediumRepository {
	void addMedium(Medium medium);

	Stream<Medium> getAllMediaStream();

	void updateMedium(Medium m);
	void clearMediumDatabase();

}
