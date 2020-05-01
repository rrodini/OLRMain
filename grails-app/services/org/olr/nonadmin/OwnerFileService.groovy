package org.olr.nonadmin

// OwnerFileService - similar to GORM services, but with special semantics.
class OwnerFileService {
//TODO: refactor this code using DRY principles.
    // playerFileService.list() is for admin.
    // playerlistByOwner() is for users.
    def playerFileListByOwner(params) {
        //params.offset = params.offset?: params.max;
        def rowList = PlayerFile.executeQuery("from PlayerFile where owner_id = ? or publik = ?",
                [params.ownerId, true], [offset: params.offset, max: params.max])
        return rowList
    }

    def playerFileListByOwnerCount(params) {
        def count = PlayerFile.executeQuery("select count(*) from PlayerFile where owner_id = ? or publik = ?",
                [params.ownerId, true])
        return count[0]
    }

    // questionFileService.list() is for admin.
    // questionlistByOwner() is for users.
    def questionFileListByOwner(params) {
        //params.offset = params.offset?: params.max;
        def rowList = QuestionFile.executeQuery("from QuestionFile where owner_id = ? or publik = ?",
                [params.ownerId, true], [offset: params.offset, max: params.max])
        return rowList
    }

    def questionFileListByOwnerCount(params) {
        def count = PlayerFile.executeQuery("select count(*) from QuestionFile where owner_id = ? or publik = ?",
                [params.ownerId, true])
        return count[0]
    }

    def questionListByOwner(params) {
        //params.offset = params.offset?: params.max;
        def rowList = Question.executeQuery("from Question where owner_id = ?",
                [params.ownerId], [offset: params.offset, max: params.max])
        return rowList
    }

    def questionListByOwnerCount(params) {
        def count = PlayerFile.executeQuery("select count(*) from Question where owner_id = ?",
                [params.ownerId])
        return count[0]
    }

    def gameListByOwner(params) {
        //params.offset = params.offset?: params.max;
        def rowList = Game.executeQuery("from Game where emcee_id = ?",
                [params.ownerId], [offset: params.offset, max: params.max])
        return rowList
    }

    def gameListByOwnerCount(params) {
        def count = PlayerFile.executeQuery("select count(*) from Game where emcee_id = ?",
                [params.ownerId])
        return count[0]
    }

}
