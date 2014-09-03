package calendarservice

class Session {

    Date begin
    Date end
    static belongsTo = [user: User]
    static constraints = {
    }
}
