package com.springcloud.photoapp.userservice.feignclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springcloud.photoapp.userservice.dto.AlbumResponseModel;

@FeignClient(name = "albums-ws", fallback = AlbumFallBack.class)
public interface AlbumServiceFeignClient {

	@GetMapping("/users/{id}/albums")
	public List<AlbumResponseModel> userAlbums(@PathVariable String id);
}

@Component
class AlbumFallBack implements AlbumServiceFeignClient {

	@Override
	public List<AlbumResponseModel> userAlbums(String id) {
		System.out.println("............Fall Back Method.........");
		return new ArrayList<AlbumResponseModel>();
	}

}