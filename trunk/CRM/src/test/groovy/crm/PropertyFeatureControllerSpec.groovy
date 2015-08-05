package crm

import grails.test.mixin.*
import spock.lang.*

@TestFor(PropertyFeatureController)
@Mock(PropertyFeature)
class PropertyFeatureControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.propertyFeatureList
            model.propertyFeatureCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.propertyFeature!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def propertyFeature = new PropertyFeature()
            propertyFeature.validate()
            controller.save(propertyFeature)

        then:"The create view is rendered again with the correct model"
            model.propertyFeature!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            propertyFeature = new PropertyFeature(params)

            controller.save(propertyFeature)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/propertyFeature/show/1'
            controller.flash.message != null
            PropertyFeature.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def propertyFeature = new PropertyFeature(params)
            controller.show(propertyFeature)

        then:"A model is populated containing the domain instance"
            model.propertyFeature == propertyFeature
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def propertyFeature = new PropertyFeature(params)
            controller.edit(propertyFeature)

        then:"A model is populated containing the domain instance"
            model.propertyFeature == propertyFeature
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/propertyFeature/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def propertyFeature = new PropertyFeature()
            propertyFeature.validate()
            controller.update(propertyFeature)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.propertyFeature == propertyFeature

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            propertyFeature = new PropertyFeature(params).save(flush: true)
            controller.update(propertyFeature)

        then:"A redirect is issued to the show action"
            propertyFeature != null
            response.redirectedUrl == "/propertyFeature/show/$propertyFeature.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/propertyFeature/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def propertyFeature = new PropertyFeature(params).save(flush: true)

        then:"It exists"
            PropertyFeature.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(propertyFeature)

        then:"The instance is deleted"
            PropertyFeature.count() == 0
            response.redirectedUrl == '/propertyFeature/index'
            flash.message != null
    }
}
