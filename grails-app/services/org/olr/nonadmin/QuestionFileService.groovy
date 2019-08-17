package org.olr.nonadmin

import grails.gorm.services.Service
import org.olr.admin.User

@Service(QuestionFile)
interface QuestionFileService {

    QuestionFile get(Serializable id)
    List<QuestionFile> list(Map args)
    List<QuestionFile> findByOwner(User owner)  // Added, but implemented by GORM service
    Long count()
    void delete(Serializable id)
    QuestionFile save(QuestionFile questionFile)

}

