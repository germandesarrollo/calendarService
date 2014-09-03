package calendarservice

class Follower {
 static belongsTo = [user: User, follower: User]
    static constraints = {
    }
}
