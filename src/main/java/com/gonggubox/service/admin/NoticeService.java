package com.gonggubox.service.admin;

import com.gonggubox.domain.admin.NoticeEntity;
import com.gonggubox.dto.admin.NoticeDto;
import com.gonggubox.mapper.admin.NoticeMapper;
import com.gonggubox.repository.admin.AdminRepository;
import com.gonggubox.repository.admin.NoticeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;

    private final NoticeMapper noticeMapper;

    private final AdminRepository adminRepository;

    @Transactional
    public NoticeEntity createNotice(Long adminId, NoticeDto.NoticePostDto noticePostDto) {
        return noticeRepository.save(
                noticeMapper.toEntity(noticePostDto,adminRepository.findById(adminId).orElseThrow(EntityNotFoundException::new))
        );
    }

    public NoticeEntity getNotice(Long noticeId) {
        return noticeRepository.findById(noticeId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public NoticeEntity updateNotice(NoticeDto.NoticePatchDto noticePatchDto) {
        NoticeEntity noticeEntity = noticeRepository.findById(noticePatchDto.getId()).orElseThrow(EntityNotFoundException::new);
        noticeMapper.updateFromPatchDto(noticePatchDto, noticeEntity);
        return noticeRepository.findById(noticePatchDto.getId()).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional
    public String deleteNotice(Long noticeId) {
        String title = noticeRepository.findById(noticeId).orElseThrow(EntityNotFoundException::new).getTitle();
        noticeRepository.deleteById(noticeId);
        return "삭제한 notice의 title : "+title;
    }
}
