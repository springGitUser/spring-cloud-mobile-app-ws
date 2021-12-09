package com.springcloud.photoapp.userservice.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springcloud.photoapp.userservice.dto.UserRequestDTO;
import com.springcloud.photoapp.userservice.entity.UserEntity;
import com.springcloud.photoapp.userservice.repos.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserEntity createUser(UserRequestDTO userDTO) {
		ModelMapper mp = new ModelMapper();
		mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		userDTO.setUserId(UUID.randomUUID().toString());
		UserEntity userEntity = mp.map(userDTO, UserEntity.class);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		UserEntity savedUser = userRepo.save(userEntity);
		return savedUser;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException(username);

		return new User(user.getEmail(), user.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	public UserRequestDTO getUserDetailsByUsername(String username) {
		UserEntity user = userRepo.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException(username);

		return new ModelMapper().map(user, UserRequestDTO.class);
	}
}
