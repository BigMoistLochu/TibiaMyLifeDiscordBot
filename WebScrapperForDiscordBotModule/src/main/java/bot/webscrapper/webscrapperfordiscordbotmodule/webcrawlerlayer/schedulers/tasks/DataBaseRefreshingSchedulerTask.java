package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawlerlayer.schedulers.tasks;

import java.util.TimerTask;

public class DataBaseRefreshingSchedulerTask extends TimerTask {
    private final TaskCommand taskCommand;
    public DataBaseRefreshingSchedulerTask(TaskCommand taskCommand){
        this.taskCommand = taskCommand;
    }
    @Override
    public void run() {
        taskCommand.start();
    }
}
