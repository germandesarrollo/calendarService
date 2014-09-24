package calendarservice
import calendarservice.User
import grails.converters.JSON
import grails.converters.deep.JSON
import org.springframework.dao.DataIntegrityViolationException
//import grails.plugin.facebooksdk.FacebookGraphClient

class UserController {
    static scaffold = User
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    // To
//FacebookGraphClient(String accessToken= '', String apiVersion = DEFAULT_API_VERSION, Integer timeout = DEFAULT_READ_TIMEOUT_IN_MS, String proxyHost = null, Integer proxyPort = null)
//    
    def index() {
        redirect(action: "list", params: params)
    }
        
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }
    //    
    //        def create() {
    //            [userInstance: new User (params)]
    //        }
   
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
            redirect(action: "list")
            return
        }
    
        [userInstance: userInstance]
    }
    
    def edit(Long id) {
        def userInstance = Calendar.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Calendar'), id])
            redirect(action: "list")
            return
        }
    
        [userInstance: userInstance]
    }
    
    def update(Long id, Long version) {
        def userInstance = Calendar.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Calendar'), id])
            redirect(action: "list")
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
            redirect(action: "list")
            return
        }
    
        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'Calendar'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'Calendar'), id])
            redirect(action: "show", id: id)
        }
    }
    
    def authenticate ={
        def user =User.findByUserNameAndPassword(params.username, params.password)
        if(user){
            session.user =user
            flash.message = "hello ${user.username}"
            redirect(action:"login")
    }else{
        flash.message = "sorry ${params.username}. Please try again"
redirect(action:login)
    }
}
    
    
    def logout = { 
    flas.message = "Goodbye ${session.user.username}"
    session.user= null
    redirect(action:"login")
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
    
        
//    // For public data
//    def facebookClient = new FacebookGraphClient()
//
//    // For private data (access token required)
//    def userAccessToken = facebookAppService.getUserAccessToken() // Or any app/user token
//    def facebookClient = new FacebookGraphClient(userAccessToken)
//
//    // With specific api version
//    def facebookClient = new FacebookGraphClient(userAccessToken, 'v1.0')
//
//    // With specific timeout (default to 180s)
//    def facebookClient = new FacebookGraphClient(userAccessToken, FacebookGraphClient.DEFAULT_API_VERSION, 90000)
//
//    // With proxy support
//    def facebookClient = new FacebookGraphClient(userAccessToken, FacebookGraphClient.DEFAULT_API_VERSION, FacebookGraphClient.DEFAULT_READ_TIMEOUT_IN_MS, '192.168.0.10', 8080)
//
//    Publishing a simple message.
//        def publishMessageResponse = facebookClient.publish("me/feed", [message:"RestFB test"])
//    println "Published message ID: " + publishMessageResponse.id
//    Publishing an event
//    Date tomorrow = new Date() + 1
//    Date twoDaysFromNow = new Date() + 2
//    def publishEventResponse = facebookClient.publish("me/events", [name:"Party", start_time:tomorrow, end_time:twoDaysFromNow])
//    println "Published event ID: " + publishEventResponse.id
//    def myFriends = facebookClient.fetchConnection("me/friends")
//    def myFeed = facebookClient.fetchConnection("me/feed")
//    println "Count of my friends: " + myFriends.size()
//    println "First item in my feed: " + myFeed[0]
//}
}