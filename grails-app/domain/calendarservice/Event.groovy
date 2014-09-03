package calendarservice

class Event {

    Date creationDate
    Date eventDate
    Integer duration
    String status
    String link
    String description
    Integer Valoration
    static constraints = {
     link nullable: true
     description nullable: true
     duration nullable: true
    }
}
