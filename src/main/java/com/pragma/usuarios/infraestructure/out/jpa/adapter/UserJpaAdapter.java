package com.pragma.usuarios.infraestructure.out.jpa.adapter;

import com.pragma.usuarios.domain.modelo.User;
import com.pragma.usuarios.domain.spi.IUserPersistencePort;
import com.pragma.usuarios.infraestructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.usuarios.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        if(userRepository.findBynumberDocument(user.getNumberDocument()).isPresent()){
            return;
        }
        userRepository.save(userEntityMapper.toEntity(user));

    }

    public User getUser(String email) {
        return userEntityMapper.toUser(userRepository.findByEmail(email).orElse(null));
    }
}
