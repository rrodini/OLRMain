package org.olr.nonadmin

//import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

//@Integration
@Rollback
class QuestionFileServiceSpec extends Specification {

    QuestionFileService questionFileService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new QuestionFile(...).save(flush: true, failOnError: true)
        //new QuestionFile(...).save(flush: true, failOnError: true)
        //QuestionFile questionFile = new QuestionFile(...).save(flush: true, failOnError: true)
        //new QuestionFile(...).save(flush: true, failOnError: true)
        //new QuestionFile(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //questionFile.id
    }

    void "test get"() {
        setupData()

        expect:
        questionFileService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<QuestionFile> questionFileList = questionFileService.list(max: 2, offset: 2)

        then:
        questionFileList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        questionFileService.count() == 5
    }

    void "test delete"() {
        Long questionFileId = setupData()

        expect:
        questionFileService.count() == 5

        when:
        questionFileService.delete(questionFileId)
        sessionFactory.currentSession.flush()

        then:
        questionFileService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        QuestionFile questionFile = new QuestionFile()
        questionFileService.save(questionFile)

        then:
        questionFile.id != null
    }
}
