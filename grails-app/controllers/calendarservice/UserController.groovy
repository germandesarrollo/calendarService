package calendarservice
import calendarservice.Calendar
import grails.converters.JSON
import grails.converters.deep.JSON
import org.springframework.dao.DataIntegrityViolationException

class UserController {


static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


def index() {
        redirect(action: "listuser", params: params)
    }

    def listuser(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.listuser(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User (params)]
    }

    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def show(Long id) {
        def userInstance = Calendar.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Calendar'), id])
            redirect(action: "listuser")
            return
        }

        [userInstance: userInstance]
    }

    def edit(Long id) {
        def userInstance = Calendar.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Calendar'), id])
            redirect(action: "listuser")
            return
        }

        [userInstance: userInstance]
    }

    def update(Long id, Long version) {
        def userInstance = Calendar.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Calendar'), id])
            redirect(action: "listuser")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'Calendar')] as Object[],
                          "Another user has updated this Calendar while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'Calendar'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = Calendar.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Calendar'), id])
            redirect(action: "listuser")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'Calendar'), id])
            redirect(action: "listuser")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'Calendar'), id])
            redirect(action: "show", id: id)
        }
    }

def test(){
        
        render "<hola>test</hola>"
    }
def follow(){
        
        render "<hola>follow</hola>"
    }
def unfolow(){
        
        render "<hola>unfollow</hola>"
    }
def edituser(){
        
        render "<hola>useredit</hola>"
    }
}