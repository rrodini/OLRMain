package org.olr.nonadmin

import grails.gorm.services.Service
import org.olr.admin.User

@Service(Question)
interface QuestionService {

    Question get(Serializable id)
    List<Question> list(Map args)
    List<QuestionFile> findByOwner(User owner)  // Added, but implemented by GORM service
    Long count()
    void delete(Serializable id)
    Question save(Question question)

}