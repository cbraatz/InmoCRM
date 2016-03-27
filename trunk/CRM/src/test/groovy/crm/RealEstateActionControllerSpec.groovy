package crm

import grails.test.mixin.*
import spock.lang.*

@TestFor(RealEstateActionController)
@Mock(RealEstateAction)
class RealEstateActionControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.realEstateActionList
            model.realEstateActionCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.realEstateAction!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def realEstateAction = new RealEstateAction()
            realEstateAction.validate()
            controller.save(realEstateAction)

        then:"The create view is rendered again with the correct model"
            model.realEstateAction!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            realEstateAction = new RealEstateAction(params)

            controller.save(realEstateAction)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/realEstateAction/show/1'
            controller.flash.message != null
            RealEstateAction.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def realEstateAction = new RealEstateAction(params)
            controller.show(realEstateAction)

        then:"A model is populated containing the domain instance"
            model.realEstateAction == realEstateAction
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def realEstateAction = new RealEstateAction(params)
            controller.edit(realEstateAction)

        then:"A model is populated containing the domain instance"
            model.realEstateAction == realEstateAction
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/realEstateAction/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def realEstateAction = new RealEstateAction()
            realEstateAction.validate()
            controller.update(realEstateAction)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.realEstateAction == realEstateAction

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            realEstateAction = new RealEstateAction(params).save(flush: true)
            controller.update(realEstateAction)

        then:"A redirect is issued to the show action"
            realEstateAction != null
            response.redirectedUrl == "/realEstateAction/show/$realEstateAction.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/realEstateAction/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def realEstateAction = new RealEstateAction(params).save(flush: true)

        then:"It exists"
            RealEstateAction.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(realEstateAction)

        then:"The instance is deleted"
            RealEstateAction.count() == 0
            response.redirectedUrl == '/realEstateAction/index'
            flash.message != null
    }
}
