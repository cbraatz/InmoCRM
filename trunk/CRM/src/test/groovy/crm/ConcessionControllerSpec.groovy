package crm

import grails.test.mixin.*
import spock.lang.*

@TestFor(ConcessionController)
@Mock(Concession)
class ConcessionControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.concessionList
            model.concessionCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.concession!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def concession = new Concession()
            concession.validate()
            controller.save(concession)

        then:"The create view is rendered again with the correct model"
            model.concession!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            concession = new Concession(params)

            controller.save(concession)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/concession/show/1'
            controller.flash.message != null
            Concession.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def concession = new Concession(params)
            controller.show(concession)

        then:"A model is populated containing the domain instance"
            model.concession == concession
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def concession = new Concession(params)
            controller.edit(concession)

        then:"A model is populated containing the domain instance"
            model.concession == concession
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/concession/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def concession = new Concession()
            concession.validate()
            controller.update(concession)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.concession == concession

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            concession = new Concession(params).save(flush: true)
            controller.update(concession)

        then:"A redirect is issued to the show action"
            concession != null
            response.redirectedUrl == "/concession/show/$concession.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/concession/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def concession = new Concession(params).save(flush: true)

        then:"It exists"
            Concession.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(concession)

        then:"The instance is deleted"
            Concession.count() == 0
            response.redirectedUrl == '/concession/index'
            flash.message != null
    }
}
