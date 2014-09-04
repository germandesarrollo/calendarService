package calendarservice

class Location {
    float lat
    float lng
    String description
  static belongsTo = [event: Event]
    static constraints = {
    }
}
