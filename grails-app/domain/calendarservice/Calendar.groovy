package calendarservice

class Calendar {

    String name
    String description
    static belongsTo = [owner: User]
    static constraints = {
    }
}
