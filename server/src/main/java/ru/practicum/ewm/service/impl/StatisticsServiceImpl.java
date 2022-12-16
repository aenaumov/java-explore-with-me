package ru.practicum.ewm.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.mapper.HitMapper;
import ru.practicum.ewm.mapper.ViewStatsMapper;
import ru.practicum.ewm.model.Hit;
import ru.practicum.ewm.model.ViewStats;
import ru.practicum.ewm.model.dto.EndPointHitDto;
import ru.practicum.ewm.model.dto.ViewStatsDto;
import ru.practicum.ewm.repository.StatisticsRepository;
import ru.practicum.ewm.service.StatisticsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Override
    @Transactional
    public void hit(EndPointHitDto endPointHitDto) {
        Hit hit = HitMapper.toHit(endPointHitDto);
        statisticsRepository.save(hit);
    }

    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (unique) {
            List<Object[]> objectListDist = statisticsRepository.getStatsDist(uris, start, end);
            List<ViewStats> finalViewStatsList = new ArrayList<>();
            objectListDist.forEach(objects -> finalViewStatsList.add(
                    new ViewStats(
                            (String) objects[0],
                            (String) objects[1],
                            Long.parseLong(String.valueOf(objects[2]))
                    )
            ));
            return ViewStatsMapper.toDtoList(finalViewStatsList);
        }
        List<ViewStats> viewStatsList = statisticsRepository.getStats(uris, start, end);
        return ViewStatsMapper.toDtoList(viewStatsList);
    }
}
