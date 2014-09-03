package calendarservice

class CalendarEvent {

    static belongsTo = [calendar: Calendar, event: Event]
    static constraints = {
    }
}
