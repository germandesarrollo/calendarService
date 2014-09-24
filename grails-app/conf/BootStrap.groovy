import com.ssdemo.auth.*
class BootStrap {

    def init = { servletContext ->
    def adminRole =Role.findOrSavehere(athority: 'ROLE_ADMIN')
    def user = User.fidOrSavehere(username: 'noelia@gmail.com', password: 'password')    }
    if(!user.authorities.contains(adminRole)){
            UserRole.create(user, adminRole, true)
    }
    def destroy = {
    }
}
