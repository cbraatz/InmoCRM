package crm

import grails.test.mixin.*
import spock.lang.*

@TestFor(CustomReportTemplateController)
@Mock(CustomReportTemplate)
class CustomReportTemplateControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.customReportTemplateList
            model.customReportTemplateCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.customReportTemplate!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def customReportTemplate = new CustomReportTemplate()
            customReportTemplate.validate()
            controller.save(customReportTemplate)

        then:"The create view is rendered again with the correct model"
            model.customReportTemplate!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            customReportTemplate = new CustomReportTemplate(params)

            controller.save(customReportTemplate)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/customReportTemplate/show/1'
            controller.flash.message != null
            CustomReportTemplate.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def customReportTemplate = new CustomReportTemplate(params)
            controller.show(customReportTemplate)

        then:"A model is populated containing the domain instance"
            model.customReportTemplate == customReportTemplate
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def customReportTemplate = new CustomReportTemplate(params)
            controller.edit(customReportTemplate)

        then:"A model is populated containing the domain instance"
            model.customReportTemplate == customReportTemplate
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/customReportTemplate/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def customReportTemplate = new CustomReportTemplate()
            customReportTemplate.validate()
            controller.update(customReportTemplate)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.customReportTemplate == customReportTemplate

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            customReportTemplate = new CustomReportTemplate(params).save(flush: true)
            controller.update(customReportTemplate)

        then:"A redirect is issued to the show action"
            customReportTemplate != null
            response.redirectedUrl == "/customReportTemplate/show/$customReportTemplate.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/customReportTemplate/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def customReportTemplate = new CustomReportTemplate(params).save(flush: true)

        then:"It exists"
            CustomReportTemplate.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(customReportTemplate)

        then:"The instance is deleted"
            CustomReportTemplate.count() == 0
            response.redirectedUrl == '/customReportTemplate/index'
            flash.message != null
    }
}
