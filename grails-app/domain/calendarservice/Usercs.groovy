package calendarservice

import org.apache.catalina.Role

class User {

    transient springSecurityService
    
    String username
    String password
    String facebookid
    Date signin
    Date lastLogin
    Date birthDate
    String email
    String accesToken
    char sex
    
    static belongsTo = [group: GroupType]
    
    
    static constraints = {
//        sex nullable: true
//        password nullable: true, size: 7..15
//        facebookid nullable: true
//        email email: true   
//        birthDate blank: false
            username blank: false, unique: true
            password blank: false
    }
    static mapping = {
      password column: '`password`'
   }

   Set<Role> getAuthorities() {
      UserRole.findAllByUser(this).collect { it.role }
   }

   def beforeInsert() {
      encodePassword()
   }

   def beforeUpdate() {
      if (isDirty('password')) {
         encodePassword()
      }
   }

   protected void encodePassword() {
      password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
   }
}
