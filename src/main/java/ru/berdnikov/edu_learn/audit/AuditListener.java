//package ru.berdnikov.edu_learn.audit;
//
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.PreUpdate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.berdnikov.edu_learn.entity.Audit;
//import ru.berdnikov.edu_learn.entity.Game;
//import ru.berdnikov.edu_learn.repository.GameAuditRepository;
//
//import java.util.Date;
//
//@Component
//public class AuditListener {
//    private GameAuditRepository auditRepository;
//
//    @Autowired
//    public AuditListener(GameAuditRepository auditRepository) {
//        this.auditRepository = auditRepository;
//    }
//
//    @PrePersist
//    public void prePersist(Game game) {
//        createAudit(game);
//    }
//
//    @PreUpdate
//    public void preUpdate(Game game) {
//        createAudit(game);
//    }
//
//    private void createAudit(Game game) {
//        Audit audit = new Audit();
//        // Устанавливаем атрибуты аудита, например, время создания и идентификатор создателя
//        audit.setCreateDate(new Date());
////        audit.setCreateBy();
//        // Связываем аудит с игрой
//        game.setAudit(audit);
//        // Сохраняем аудит
//        auditRepository.save(audit);
//    }
//}
