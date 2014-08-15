package calendarservice

class CalendarEventGender {

    static belongsTo = [calendar: Calendar, category: Category,gender:Gender]
    static constraints = {
    }
}
