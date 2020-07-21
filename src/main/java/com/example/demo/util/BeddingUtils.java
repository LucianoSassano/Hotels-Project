package com.example.demo.util;

import com.example.demo.dto.bedding.BeddingDtoOutput;
import com.example.demo.model.Bedding;

import java.util.List;
import java.util.stream.Collectors;

public class BeddingUtils {

  public static List<BeddingDtoOutput> listEntityToDTO(List<Bedding> beddings) {
    return beddings.stream().map(BeddingDtoOutput::new).collect(Collectors.toList());
  }
}
