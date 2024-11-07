package services;
import entities.User;
import errorHandling.UserAlreadyExistsException;
import errorHandling.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    @Transactional
    public User createNewUser(User user) {
        // Additional custom validations
        validateUserUniqueness(user);

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    private void validateUserUniqueness(User user) {
        // Check if email already exists
        userRepository.findByEmail(user.getEmail())
                .ifPresent(existingUser -> {
                    throw new UserAlreadyExistsException("Email is already in use");
                });
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}