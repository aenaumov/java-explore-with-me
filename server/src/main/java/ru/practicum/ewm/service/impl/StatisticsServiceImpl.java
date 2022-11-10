package ru.practicum.ewm.service.impl;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.mapper.HitMapper;
import ru.practicum.ewm.model.Hit;
import ru.practicum.ewm.model.QHit;
import ru.practicum.ewm.model.dto.EndPointHitDto;
import ru.practicum.ewm.model.dto.ViewStatsDto;
import ru.practicum.ewm.repository.StatisticsRepository;
import ru.practicum.ewm.service.StatisticsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    @Transactional
    public void hit(EndPointHitDto endPointHitDto) {
        Hit hit = HitMapper.toHit(endPointHitDto);
        statisticsRepository.save(hit);
    }

    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        QHit hit = QHit.hit;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(start != null ? hit.timestamp.goe(start) : hit.timestamp.isNotNull());
        booleanBuilder.and(end != null ? hit.timestamp.loe(end) : hit.timestamp.isNotNull());
        booleanBuilder.and(hit.uri.in(uris));
        List<Hit> hitsAll = statisticsRepository.findAll(booleanBuilder, Pageable.unpaged()).toList();
        List<ViewStatsDto> viewStatsDtos = new ArrayList<>();
        for (String uri : uris) {
            List<Hit> hits = hitsAll.stream().filter(h -> h.getUri().equals(uri)).collect(Collectors.toList());
            if (unique) {
                hits = findUnique(hits);
            }
            List<String> apps = getServiceNames(hits);
            viewStatsDtos.addAll(toViewStatsDto(hits, apps, uri));
        }
        return viewStatsDtos;
    }

    private List<Hit> findUnique(List<Hit> notUniqueHits) {
        HashMap<String, Hit> uniqueHits = new HashMap<>();
        for (Hit hit : notUniqueHits) {
            String key = hit.getApp() + "/" + hit.getIp();
            if (!uniqueHits.containsKey(key)) {
                uniqueHits.put(key, hit);
            }
        }
        return List.copyOf(uniqueHits.values());
    }

    private List<String> getServiceNames(List<Hit> hits) {
        return hits.stream().map(Hit::getApp).distinct().collect(Collectors.toList());
    }

    private List<ViewStatsDto> toViewStatsDto(List<Hit> hits, List<String> apps, String uri) {
        List<ViewStatsDto> viewStatsDtos = new ArrayList<>();
        for (String app : apps) {
            long count = hits.stream()
                    .filter(h -> h.getApp().equals(app))
                    .count();
            viewStatsDtos.add(HitMapper.toViewStatsDto(app, uri, count));
        }
        return viewStatsDtos;
    }
}
