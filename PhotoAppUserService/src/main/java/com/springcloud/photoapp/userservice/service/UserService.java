package com.springcloud.photoapp.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springcloud.photoapp.userservice.dto.AlbumResponseModel;
import com.springcloud.photoapp.userservice.dto.UserAlbumResponseDTO;
import com.springcloud.photoapp.userservice.dto.UserRequestDTO;
import com.springcloud.photoapp.userservice.entity.UserEntity;
import com.springcloud.photoapp.userservice.feignclient.AlbumServiceFeignClient;
import com.springcloud.photoapp.userservice.repos.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment env;
	@Autowired
	private AlbumServiceFeignClient feignClient;
	
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

	public UserAlbumResponseDTO getUserById(String userId) {
		UserEntity findByUserId = userRepo.findByUserId(userId);
		ModelMapper mp = new ModelMapper();
		mp.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserAlbumResponseDTO userAlbumDto = mp.map(findByUserId, UserAlbumResponseDTO.class);
		//Calling Albums-WS for getting the list of albums by userId...
		/*
		 * String albumUrl=String.format(env.getProperty("albums.ws.url"), userId);
		 * ResponseEntity<List<AlbumResponseModel>>
		 * albumResponseList=restTemplate.exchange(albumUrl, HttpMethod.GET,null,new
		 * ParameterizedTypeReference<List<AlbumResponseModel>>() {
		 * 
		 * })
		 * List<AlbumResponseModel>  = albumResponseList.getBody();
		 */
		List<AlbumResponseModel> albumLst= feignClient.userAlbums(userId);
		userAlbumDto.setAlbums(albumLst);
		return userAlbumDto;
	}
}
