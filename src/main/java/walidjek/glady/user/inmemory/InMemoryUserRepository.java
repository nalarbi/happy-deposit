package walidjek.glady.user.inmemory;

import walidjek.glady.user.User;
import walidjek.glady.user.UserRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class InMemoryUserRepository implements UserRepository {

    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public Optional<User> get(int userId) {
        User user = this.users.get(userId);
        if (user == null) {
            return Optional.empty();
        }

        return Optional.of(user);
    }

    @Override
    public void save(User user) {
        this.users.put(user.getId(), user);
    }

    @Override
    public void delete(int userId) {
        users.remove(userId);
    }
}
