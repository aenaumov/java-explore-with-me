package ru.practicum.ewm.user.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.user.UserMapper;
import ru.practicum.ewm.user.UserRepository;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.model.dto.UserDto;
import ru.practicum.ewm.user.service.UserService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers(List<Long> ids, Pageable pageable) {
        if (ids != null) {
            final List<User> users = userRepository.findAllById(ids);
            return UserMapper.toUserDtoList(users);
        }
        final List<User> users = userRepository.findAll(pageable).toList();
        return UserMapper.toUserDtoList(users);
    }

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
        User user = userRepository.save(UserMapper.toUser(userDto));
        return UserMapper.toDto(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
