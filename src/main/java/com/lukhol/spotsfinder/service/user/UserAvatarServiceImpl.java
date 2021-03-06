package com.lukhol.spotsfinder.service.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.lukhol.spotsfinder.exception.NotFoundUserException;
import com.lukhol.spotsfinder.model.User;
import com.lukhol.spotsfinder.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAvatarServiceImpl implements UserAvatarService {
	
	@Value("${user.avatar.path}")
	private String AVATARS_PATH;
	
	private final UserRepository userRepository;

	@Override
	@Transactional
	public void saveAvatar(byte[] avatarBytes, long userId) throws IOException {
		FileOutputStream fos = new FileOutputStream(String.format("%s/%d.jpg", AVATARS_PATH , userId)); 
		
		try {
		    fos.write(avatarBytes);
		}
		finally {
		    fos.close();
		}
	}

	@Override
	@Transactional
	public String setInternalAvatarUrl(User user) {
		user.setAvatarUrl(String.format(String.format("%s%d.jpg", "/user/avatar/", user.getId())));
		userRepository.save(user);
		return user.getAvatarUrl();
	}

	@Override
	@Transactional
	public byte[] getUserAvatar(long userId) throws NotFoundUserException, IOException {
		
		if(!userRepository.exists(userId))
			throw new NotFoundUserException(userId);
		
		File fileImage = new File(String.format("%s/%d.jpg", AVATARS_PATH , userId));
		byte[] imageBytes;
		
		if(fileImage.exists()){
			imageBytes = Files.readAllBytes(fileImage.toPath());
		}
		else{
			ClassPathResource anonymousUserImgRes = new ClassPathResource("/static/images/anonymous.jpg");
			imageBytes = FileCopyUtils.copyToByteArray(anonymousUserImgRes.getInputStream());
		}
		
		return imageBytes;
	}
}
