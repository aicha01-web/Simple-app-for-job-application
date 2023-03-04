package com.groupeis.cv.service;

import com.groupeis.cv.domain.CvUser;
import com.groupeis.cv.entity.CvUserEntity;
import com.groupeis.cv.repository.CvUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private CvUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CvUserEntity user = userRepository.findByName(username);
        if(user==null)
            throw new UsernameNotFoundException("User not present");
        return user;
    }
}
