package com.example.demo.util;

import com.example.demo.dto.bedding.BeddingDto;
import com.example.demo.model.Bedding;

import java.util.List;
import java.util.stream.Collectors;

public class BeddingUtils {

  public static List<BeddingDto> listEntityToDTO(List<Bedding> beddings) {
    return beddings.stream().map(BeddingDto::new).collect(Collectors.toList());
  }
}
