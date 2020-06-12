package com.example.demo.service;

import com.example.demo.dto.bedding.BeddingDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Bedding;
import com.example.demo.repository.BeddingRepository;
import com.example.demo.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeddingService {

    private final BeddingRepository beddingRepository;

    public BeddingDto add(BeddingDto beddingDto) {
        Bedding result = beddingRepository.save(Bedding.buildBeddingEntity(beddingDto));

        return new BeddingDto(result);
    }

    public List<BeddingDto> getAll() {
        List<BeddingDto> beddingDtoList = beddingRepository.findAll()
                .stream()
                .map((bedding) -> new BeddingDto(bedding)).collect(Collectors.toList());

        if (beddingDtoList.isEmpty())
            throw new NotFoundException(ErrorMessage.BEDDING_NOT_FOUND);

        return beddingDtoList;
    }

    public Bedding getById(Long id) {

        return beddingRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.BEDDING_NOT_FOUND));
    }

    public Bedding delete(Long id) {

        Bedding beddingToDelete = beddingRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.BEDDING_NOT_FOUND));
        beddingRepository.deleteById(id);
        return beddingToDelete;

    }

    public Bedding replace(Long id, BeddingDto beddingDto) {

        beddingRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.BEDDING_NOT_FOUND));

        Bedding updatedBedding = Bedding.buildBeddingEntity(beddingDto);
        updatedBedding.setId(id);

        return beddingRepository.save(updatedBedding);
    }


}