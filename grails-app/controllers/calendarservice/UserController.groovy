package calendarservice
import calendarservice.Calendar
import grails.converters.JSON
import grails.converters.deep.JSON
import org.springframework.dao.DataIntegrityViolationException

class UserController {
    static scaffold = User
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

//
//    def index() {
//            redirect(action: "listuser", params: params)
//       }
//    //
        def listuser(Integer max) {
            params.max = Math.min(max ?: 10, 100)
            [calendarInstanceList: User.listuser(params), calendarInstanceTotal: User.count()]
        }
//    
//        def create() {
//            [calendarInstance: new User (params)]
//        }
   
        def save() {
            def calendarInstance = new User(params)
            if (!calendarInstance.save(flush: true)) {
                render(view: "create", model: [calendarInstance: calendarInstance])
                return
            }
    
            flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), calendarInstance.id])
            redirect(action: "show", id: calendarInstance.id)
        }
  
        def show(Long id) {
            def calendarInstance = Calendar.get(id)
            if (!calendarInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Calendar'), id])
                redirect(action: "listuser")
                return
            }
    
            [calendarInstance: calendarInstance]
        }
    
        def edit(Long id) {
            def calendarInstance = Calendar.get(id)
            if (!calendarInstance) {
               flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Calendar'), id])
                redirect(action: "listuser")
                return
            }
    
            [calendarInstance: calendarInstance]
        }
    
        def update(Long id, Long version) {
            def calendarInstance = Calendar.get(id)
            if (!calendarInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Calendar'), id])
                redirect(action: "listuser")
                return
            }
    
            if (version != null) {
                if (calendarInstance.version > version) {
                    calendarInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                              [message(code: 'user.label', default: 'Calendar')] as Object[],
                              "Another user has updated this Calendar while you were editing")
                    render(view: "edit", model: [calendarInstance: calendarInstance])
                    return
             }
          }
    
            calendarInstance.properties = params
    
            if (!calendarInstance.save(flush: true)) {
                render(view: "edit", model: [calendarInstance: calendarInstance])
                return
            }
    
            flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'Calendar'), calendarInstance.id])
            redirect(action: "show", id: calendarInstance.id)
        }
    
        def delete(Long id) {
            def calendarInstance = Calendar.get(id)
            if (!calendarInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Calendar'), id])
                redirect(action: "listuser")
                return
            }
    
            try {
                calendarInstance.delete(flush: true)
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
    def unfollow(){

            render "<hola>unfollow</hola>"
        }
    def edituser(){

            render "<hola>useredit</hola>"
        }
    }