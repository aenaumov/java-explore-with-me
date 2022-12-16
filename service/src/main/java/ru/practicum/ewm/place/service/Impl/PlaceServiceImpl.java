package ru.practicum.ewm.place.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.place.LocationTypeRepository;
import ru.practicum.ewm.place.PlaceRepository;
import ru.practicum.ewm.place.mapper.LocationTypeMapper;
import ru.practicum.ewm.place.mapper.PlaceMapper;
import ru.practicum.ewm.place.model.LocationType;
import ru.practicum.ewm.place.model.Place;
import ru.practicum.ewm.place.model.dto.LocationTypeDto;
import ru.practicum.ewm.place.model.dto.PlaceDto;
import ru.practicum.ewm.place.service.PlaceService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final LocationTypeRepository locationTypeRepository;
    private final PlaceRepository placeRepository;

    @Override
    @Transactional
    public LocationTypeDto addPlaceTypeByAdmin(LocationTypeDto locationTypeDto) {
        LocationType locationType = LocationTypeMapper.toLocationType(locationTypeDto);
        return LocationTypeMapper.toDto(locationTypeRepository.save(locationType));
    }

    @Override
    @Transactional
    public PlaceDto addPlaceByAdmin(PlaceDto placeDto) {
        LocationType locationType = getLocationTypeById(placeDto.getLocationType().getId());
        Place place = PlaceMapper.toPlace(placeDto, locationType);
        return PlaceMapper.toDto(placeRepository.save((place)));
    }

    @Override
    public List<PlaceDto> getAllPlacesByAdmin(List<Long> ids, Pageable pageable) {
        if (ids != null) {
            final List<Place> places = placeRepository.findAllById(ids);
            return PlaceMapper.toPlaceDtoList(places);
        }
        final List<Place> places = placeRepository.findAll(pageable).toList();
        return PlaceMapper.toPlaceDtoList(places);
    }

    private LocationType getLocationTypeById(Long locTypeId) {
        return locationTypeRepository.findById(locTypeId)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Тип локации with id=%d was not found", locTypeId)));
    }
}
