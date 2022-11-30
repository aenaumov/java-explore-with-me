package ru.practicum.ewm.user.service.Impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.common.EwmPageRequest;
import ru.practicum.ewm.user.UserMapper;
import ru.practicum.ewm.user.UserRepository;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.model.dto.UserDto;
import ru.practicum.ewm.user.service.UserService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers(List<Long> ids, int from, int size) {
        if (ids != null) {
            final List<User> users = userRepository.findAllById(ids);
            return UserMapper.toUserDtoList(users);
        }
        final Pageable pageable = new EwmPageRequest(from, size, Sort.unsorted());
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
