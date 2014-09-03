package calendarservice

class GroupPermission {

    static belongsTo = [permission: Permission, group: GroupType]
    static constraints = {
    }
}
