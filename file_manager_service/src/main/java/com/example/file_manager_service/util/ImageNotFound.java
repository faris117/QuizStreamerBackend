package com.example.file_manager_service.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ImageNotFound  extends Throwable{
    private String errorMessage;
}
