package calendarservice

class FollowerCalendar {

    static belongsTo = [user: User, calendar: Calendar]
    static constraints = {
    }
}
