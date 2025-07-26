package com.example.suivie_importBackend.service;

import com.example.suivie_importBackend.Enum.AuditStatus;
import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.models.AuditM;
import com.example.suivie_importBackend.models.UserM;
import com.example.suivie_importBackend.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    @Autowired
    AuditRepository auditRepository;

    public Object allAudits(Integer page){
        Page<AuditM> audits = auditRepository.findByDeletedOrderByDateCreationDesc(Deletion.NO, PageRequest.of(page, 25));
        return ResponseEntity.ok(audits);
    }

    public void record(String libelle, String object, Integer status, UserM user){
        AuditM audit = new AuditM(libelle, object, status, user);
        auditRepository.save(audit);
    }

    public void recordInfo(String libelle, String object, UserM user) {
        AuditM audit = new AuditM(libelle, object, AuditStatus.INFO, user);
        auditRepository.save(audit);
    }

    public void recordImportant(String libelle, String object, UserM user){
        AuditM audit = new AuditM(libelle, object, AuditStatus.IMPORTANT, user);
        auditRepository.save(audit);
    }

    public void recordDanger(String libelle, String object, UserM user) {
        AuditM audit = new AuditM(libelle, object, AuditStatus.DANGER, user);
        auditRepository.save(audit);
    }

    public void recordLogin(String libelle, String object, UserM user){
        AuditM audit = new AuditM(libelle, object, AuditStatus.LOGIN, user);
        auditRepository.save(audit);
    }

}
