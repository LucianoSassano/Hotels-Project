package com.example.demo.service;

import com.example.demo.dto.bedding.BeddingDto;
import com.example.demo.model.Bedding;
import com.example.demo.repository.BeddingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        return beddingDtoList;
    }

    public Optional<Bedding> getById(Long id) {

        return beddingRepository.findById(id);

    }

    public Optional<Bedding> delete(Long id) {

        Optional<Bedding> beddingToDelete = beddingRepository.findById(id);           //Generate an optional with the bedding to delete

        if (beddingToDelete.isPresent()) {                                        //If a bedding exists, we delete it and return it
            beddingRepository.deleteById(id);

            return beddingToDelete;
        }

        return beddingToDelete;                                                   //Else, just return the empty optional to be handled on controller layer
    }

    public Optional<Bedding> replace(Long id, BeddingDto beddingDto) {

        Optional<Bedding> beddingToUpdate = beddingRepository.findById(id);           //Generate an optional with the bedding to update

        if (beddingToUpdate.isPresent())                                          //If a bedding exists, we update it and return the new bedding with that id
        {
            Bedding updatedBedding = Bedding.buildBeddingEntity(beddingDto);
            updatedBedding.setId(id);
            beddingRepository.save(updatedBedding);

            return beddingRepository.findById(id);
        }

        return beddingToUpdate;                                                   //Else, just return the empty optional to be handled on controller layer
    }
}