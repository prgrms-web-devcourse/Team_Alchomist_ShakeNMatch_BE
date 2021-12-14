package com.shake_match.alchomist.amazon.controller;

import com.shake_match.alchomist.amazon.service.ImageService;
import com.shake_match.alchomist.amazon.service.S3Service;
import com.shake_match.alchomist.amazon.dto.ImageDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ImageController {
    private final S3Service s3Service;
    private final ImageService imageService;

    public ImageController(S3Service s3Service, ImageService imageService) {
        this.s3Service = s3Service;
        this.imageService = imageService;
    }

    @GetMapping("/image")
    public String displayWrite(Model model, MultipartFile file) throws IOException {
        List<ImageDto> imageDtoList = imageService.getImageList();
        model.addAttribute("imageList", imageDtoList);
        s3Service.getImage(file);
        return "/image";
    }

    @PostMapping("/image")
    public String execWrite(ImageDto imageDto, MultipartFile file) throws IOException {
        String imgPath = s3Service.upload(imageDto.getFilePath(), file);
        imageDto.setFilePath(imgPath);
        imageService.saveImage(imageDto);
        return "redirect:/image";
    }


}
