package org.olr.nonadmin

import grails.gorm.services.Service
import org.olr.admin.User

@Service(PlayerFile)
interface PlayerFileService {
    PlayerFile get(Serializable id)
    List<PlayerFile> list(Map args)
    Long count()
    void delete(Serializable id)
    PlayerFile save(PlayerFile playerFile)
}