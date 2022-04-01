package com.example.licenseapp.model.security;

import com.example.licenseapp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Client client = clientRepository.getByLogin(username);

        if (Objects.isNull(client)) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return new User(client.getLogin(), client.getPassword(), List.of(client.getRole()));
    }
}
