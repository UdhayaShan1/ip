package tasks;

import tasks.Task;

public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName, "T");
    }

    public Todo(String taskName, int isTaskDone) {
        super(taskName, "T");
        changeStatus(isTaskDone);
        super.setTime(new String[] {"NA", "NA"});
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + super.getTaskName();
    }
}
