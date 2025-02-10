package com.pragma.usuarios.infraestructure.out.jpa.adapter;


import com.pragma.usuarios.domain.modelo.User;
import com.pragma.usuarios.domain.spi.IUserPersistencePort;
import com.pragma.usuarios.infraestructure.exception.UserNotFoundException;
import com.pragma.usuarios.infraestructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.usuarios.infraestructure.out.jpa.repository.IUserRepository;
import com.pragma.usuarios.infraestructure.utils.Constants;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user){
        userRepository.save(userEntityMapper.toEntity(user));

    }

    public User existsByEmail(String email){
        return userEntityMapper.toUser(userRepository.findByEmail(email).orElse(null));
    }

    public User getUser(String email) {
        return userEntityMapper.toUser(userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException(Constants.USER_NOT_MESSAGE)));
    }

    @Override
    public User getUserById(Long id) {
        return userEntityMapper.toUser(userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(Constants.USER_NOT_MESSAGE)));
    }


}
