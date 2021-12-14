package com.shake_match.alchomist.amazon.service;

import com.shake_match.alchomist.amazon.Image;
import com.shake_match.alchomist.amazon.dto.ImageDto;
import com.shake_match.alchomist.amazon.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final String bucket = "team15-image-bucket";
    private final String region = "ap-northeast-2";

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImage(ImageDto imageDto) {
        imageRepository.save(imageDto.toEntity());
    }

    public List<ImageDto> getImageList() {
        List<Image> imageList = imageRepository.findAll();
        List<ImageDto> imageDtoList = new ArrayList<>();
        for (Image image : imageList) {
            imageDtoList.add(convertEntityToDto(image));
        }
        return imageDtoList;
    }

    private ImageDto convertEntityToDto(Image image) {
        return ImageDto.builder()
                .id(image.getId())
                .title(image.getTitle())
                .filePath(image.getFilePath())
                .imgFullPath("https://" + bucket + ".s3." + region + ".amazonaws.com/" + image.getFilePath())
                .build();
    }
}
