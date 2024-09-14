package com.streammusic.strawberryfields.domain.track.service.command;

import static com.streammusic.strawberryfields.global.exception.resultcode.BusinessExceptionCode.*;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.streammusic.strawberryfields.domain.track.persistence.domain.Track;
import com.streammusic.strawberryfields.domain.track.persistence.repository.command.TrackCommandRepository;
import com.streammusic.strawberryfields.domain.track.service.dto.UploadTrackDto;
import com.streammusic.strawberryfields.domain.user.persistence.domain.User;
import com.streammusic.strawberryfields.domain.user.service.query.UserQueryService;
import com.streammusic.strawberryfields.global.exception.BusinessException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TrackCommandService {
	private static final List<String> ALLOWED_EXTENSIONS = List.of("mp3", "wav");

	private final TrackCommandRepository trackCommandRepository;
	private final UserQueryService userQueryService;

	@Transactional
	public UploadTrackDto.Response uploadTrack(MultipartFile file, Long userId) {
		User user = userQueryService.getById(userId);

		try {
			Track track = saveTrack(file, user);

			return new UploadTrackDto.Response(track.getId(), track.getTitle());

		} catch (IOException e) {
			throw new BusinessException(FILE_UPLOAD_FAILED);
		}
	}

	private Track saveTrack(MultipartFile file, User user) throws IOException {
		validateFile(file);

		String fileNameWithoutExtension = getFileNameWithoutExtension(file.getOriginalFilename());
		byte[] fileData = file.getBytes();

		Track track = Track.createOf(user, fileNameWithoutExtension, fileData);

		return trackCommandRepository.save(track);
	}

	private void validateFile(MultipartFile file) {
		if (file.isEmpty() || file.getOriginalFilename() == null) {
			throw new BusinessException(NO_FILE_UPLOADED);
		}

		String extension = extractFileExtension(file.getOriginalFilename());
		if (!ALLOWED_EXTENSIONS.contains(extension)) {
			throw new BusinessException(INVALID_TRACK_TYPE);
		}
	}

	private String extractFileExtension(String fileName) {

		return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	}

	private String getFileNameWithoutExtension(String fileName) {
		if (fileName != null && fileName.contains(".")) {
			return fileName.substring(0, fileName.lastIndexOf('.'));
		}

		return fileName;
	}
}
