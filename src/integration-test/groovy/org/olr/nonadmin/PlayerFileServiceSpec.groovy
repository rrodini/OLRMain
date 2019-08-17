package org.olr.nonadmin

//import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

//@Integration
@Rollback
class PlayerFileServiceSpec extends Specification {

    PlayerFileService playerFileService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PlayerFile(...).save(flush: true, failOnError: true)
        //new PlayerFile(...).save(flush: true, failOnError: true)
        //PlayerFile playerFile = new PlayerFile(...).save(flush: true, failOnError: true)
        //new PlayerFile(...).save(flush: true, failOnError: true)
        //new PlayerFile(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //playerFile.id
    }

    void "test get"() {
        setupData()

        expect:
        playerFileService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PlayerFile> playerFileList = playerFileService.list(max: 2, offset: 2)

        then:
        playerFileList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        playerFileService.count() == 5
    }

    void "test delete"() {
        Long playerFileId = setupData()

        expect:
        playerFileService.count() == 5

        when:
        playerFileService.delete(playerFileId)
        sessionFactory.currentSession.flush()

        then:
        playerFileService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PlayerFile playerFile = new PlayerFile()
        playerFileService.save(playerFile)

        then:
        playerFile.id != null
    }
}
