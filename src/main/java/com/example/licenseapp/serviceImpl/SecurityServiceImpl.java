package com.example.licenseapp.serviceImpl;

import com.example.licenseapp.dto.ClientDTO;
import com.example.licenseapp.mapper.ClientMapper;
import com.example.licenseapp.model.security.Client;
import com.example.licenseapp.repository.ClientRepository;
import com.example.licenseapp.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    
    private final ClientRepository clientRepository;
    
    private final ClientMapper clientMapper;
    
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    @Transactional
    public ClientDTO register(ClientDTO clientDTO) {
        
        Client userToRegister = clientMapper.toEntity(clientDTO);
        userToRegister.setPassword(passwordEncoder.encode(userToRegister.getPassword()));
        
        clientRepository.insert(userToRegister);
        
        return clientDTO;
    }
    
    @Override
    public void changePassword(String login, String oldPassword, String newPassword) {
        
        Client user = clientRepository.getByLogin(login);
        
        String encodedPassword = passwordEncoder.encode(oldPassword);
        if (!encodedPassword.equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect password");
        }
        
        user.setPassword(passwordEncoder.encode(newPassword));
        clientRepository.save(user);
    }
}
