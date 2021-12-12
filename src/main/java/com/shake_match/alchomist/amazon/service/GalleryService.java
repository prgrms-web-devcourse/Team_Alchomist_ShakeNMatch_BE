package com.shake_match.alchomist.amazon.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.shake_match.alchomist.amazon.Gallery;
import com.shake_match.alchomist.amazon.dto.GalleryDto;
import com.shake_match.alchomist.amazon.repository.GalleryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GalleryService {
    private GalleryRepository galleryRepository;
    private AmazonS3Client s3Service;

    public void savePost(GalleryDto galleryDto) {
        galleryRepository.save(galleryDto.toEntity());
    }

    public List<GalleryDto> getList() {
        List<Gallery> galleryEntityList = galleryRepository.findAll();
        List<GalleryDto> galleryDtoList = new ArrayList<>();
        for (Gallery gallery : galleryEntityList) {
            galleryDtoList.add(convertEntityToDto(gallery));
        }
        return galleryDtoList;
    }

    private GalleryDto convertEntityToDto(Gallery gallery) {
        return GalleryDto.builder()
                .id(gallery.getId())
                .title(gallery.getTitle())
                .filePath(gallery.getFilePath())
                .imgFullPath("https://" + s3Service.getRegion() + "/" + gallery.getFilePath())
                .build();
    }
}
