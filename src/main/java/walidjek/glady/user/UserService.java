package walidjek.glady.user;

import walidjek.glady.deposit.Deposit;
import walidjek.glady.deposit.DepositType;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class UserService {

    UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(int userId) throws UserNotFoundException {
        Optional<User> userOptional = this.userRepository.get(userId);
        return userOptional.orElseThrow(() ->
                new UserNotFoundException(String.format(("User with id [%s] not found"), userId))
        );
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public void deleteUser(int userId) {
        this.userRepository.delete(userId);
    }

    public int getBalance(User user, DepositType depositType) throws UserNotFoundException {
        return user.getDeposits()
                .stream()
                .filter(Deposit::isValid)
                .filter(deposit -> deposit.getDepositType().equals(depositType))
                .map(Deposit::getAmount)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public int getBalance(int userId, DepositType depositType) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.get(userId);
        User user = userOptional.orElseThrow(() -> new UserNotFoundException(String.format(("User with id [%s] not found"), userId)));
        return this.getBalance(user, depositType);
    }
}
