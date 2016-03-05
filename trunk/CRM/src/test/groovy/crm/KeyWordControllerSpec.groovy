package crm

import grails.test.mixin.*
import spock.lang.*

@TestFor(KeyWordController)
@Mock(KeyWord)
class KeyWordControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.keyWordList
            model.keyWordCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.keyWord!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def keyWord = new KeyWord()
            keyWord.validate()
            controller.save(keyWord)

        then:"The create view is rendered again with the correct model"
            model.keyWord!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            keyWord = new KeyWord(params)

            controller.save(keyWord)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/keyWord/show/1'
            controller.flash.message != null
            KeyWord.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def keyWord = new KeyWord(params)
            controller.show(keyWord)

        then:"A model is populated containing the domain instance"
            model.keyWord == keyWord
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def keyWord = new KeyWord(params)
            controller.edit(keyWord)

        then:"A model is populated containing the domain instance"
            model.keyWord == keyWord
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/keyWord/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def keyWord = new KeyWord()
            keyWord.validate()
            controller.update(keyWord)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.keyWord == keyWord

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            keyWord = new KeyWord(params).save(flush: true)
            controller.update(keyWord)

        then:"A redirect is issued to the show action"
            keyWord != null
            response.redirectedUrl == "/keyWord/show/$keyWord.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/keyWord/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def keyWord = new KeyWord(params).save(flush: true)

        then:"It exists"
            KeyWord.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(keyWord)

        then:"The instance is deleted"
            KeyWord.count() == 0
            response.redirectedUrl == '/keyWord/index'
            flash.message != null
    }
}
