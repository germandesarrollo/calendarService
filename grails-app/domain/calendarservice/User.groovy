package calendarservice

class User {

    String password
    String facebookid
    Date signin
    Date lastLogin
    Date birthDate
    String email
    char sex
    
    static belongsTo = [group: GroupType]
    
    
    static constraints = {
    sex nullable: true
    password nullable: true
    facebookid nullable: true
    birthDate blank: false
    }
}
