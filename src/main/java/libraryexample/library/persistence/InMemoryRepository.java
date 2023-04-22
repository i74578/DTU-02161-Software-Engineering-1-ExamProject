package dtu.library.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import dtu.library.app.MediumRepository;
import dtu.library.app.UserRepository;
import dtu.library.domain.Medium;
import dtu.library.domain.User;

public class InMemoryRepository implements MediumRepository, UserRepository {

	List<Medium> media = new ArrayList<>();
	List<User> users = new ArrayList<>();

	@Override
	public void addMedium(Medium medium) {
		media.add(medium);
	}

	@Override
	public Stream<Medium> getAllMediaStream() {
		return media.stream();
	}

	@Override
	public void updateMedium(Medium m) {
		
	}

	@Override
	public boolean contains(User user) {
		return users.contains(user);
	}

	@Override
	public void addUser(User user) {
		users.add(user);
	}

	@Override
	public Stream<User> getAllUsersStream() {
		return users.stream();
	}

	@Override
	public void removeUser(User user) {
		users.remove(user);
	}

	@Override
	public void updateUser(User user) {
		user.getBorrowedMedia().stream().forEach(m -> this.updateMedium(m));
	}

	@Override
	public void clearMediumDatabase() {
		media.clear();
		
	}

	@Override
	public void clearUserDatabase() {
		users.clear();
		
	}

}
