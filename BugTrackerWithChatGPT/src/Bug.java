class Bug {
    // First we have the variables that describe the
    private String bugID;
    private String bugTitle;
    private String bugDescription;
    private int bugPriority;
    private String bugType;
    private String bugStatus;
    private String bugDate;
    private String bugAssignedTo;
    private String bugReportedTo;

    // Next, we have the constructor that will initialize our object of this class
    public Bug(String bugID, String bugTitle, String bugDescription, int bugPriority, String BugType, String BugStatus, String bugDate, String bugAssignedTo, String bugReportedTo) {
        this.bugID = bugID;
        this.bugTitle = bugTitle;
        this.bugDescription = bugDescription;
        this.bugPriority = bugPriority;
        this.bugType = BugType;
        this.bugStatus = BugStatus;
        this.bugDate = bugDate;
        this.bugAssignedTo = bugAssignedTo;
        this.bugReportedTo = bugReportedTo;
    }

    // Lastly, here are our long list of get/setter functions if we ever require them to set variables or get them
    public String getBugID() {
        return bugID;
    }

    public String getBugTitle() {
        return bugTitle;
    }
    public String getBugDescription() {
        return bugDescription;
    }

    public int getBugPriority() {
        return bugPriority;
    }

    public String getBugType() {
        return bugType;
    }

    public String getBugStatus() {
        return bugStatus;
    }

    public String getBugDate() {
        return bugDate;
    }

    public String getBugAssignedTo() {
        return bugAssignedTo;
    }

    public String getBugReportedTo() {
        return bugReportedTo;
    }

    public void setBugID(String bugID) {
        this.bugID = bugID;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle;
    }

    public void setBugDescription(String bugDescription) {
        this.bugDescription = bugDescription;
    }

    public void setBugPriority(int bugPriority) {
        this.bugPriority = bugPriority;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType;
    }

    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus;
    }

    public void setBugDate(String bugDate) {
        this.bugDate = bugDate;
    }

    public void setBugAssignedTo(String bugAssignedTo) {
        this.bugAssignedTo = bugAssignedTo;
    }

    public void setBugReportedTo(String bugReportedTo) {
        this.bugReportedTo = bugReportedTo;
    }

}
