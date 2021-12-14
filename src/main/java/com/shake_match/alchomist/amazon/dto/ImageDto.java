package com.shake_match.alchomist.amazon.dto;

import com.shake_match.alchomist.amazon.Image;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImageDto {

    private Long id;
    private String title;
    private String filePath;
    private String imgFullPath;

    public Image toEntity() {
        return Image.builder()
                .id(id)
                .title(title)
                .filePath(filePath)
                .build();
    }

    @Builder
    public ImageDto(Long id, String title, String filePath, String imgFullPath) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.imgFullPath = imgFullPath;
    }
}