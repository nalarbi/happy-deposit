package walidjek.glady.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> get(int userId);

    void save(User save);

    void delete(int userId);
}
