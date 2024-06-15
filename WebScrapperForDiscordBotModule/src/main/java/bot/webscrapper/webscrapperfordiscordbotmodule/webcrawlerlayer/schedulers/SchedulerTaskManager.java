package bot.webscrapper.webscrapperfordiscordbotmodule.webcrawlerlayer.schedulers;

import bot.webscrapper.webscrapperfordiscordbotmodule.webcrawlerlayer.schedulers.tasks.DataBaseRefreshingSchedulerTask;
import bot.webscrapper.webscrapperfordiscordbotmodule.webcrawlerlayer.schedulers.tasks.TaskCommand;

import java.util.Timer;

public class SchedulerTaskManager {

    private static SchedulerTaskManager INSTANCE = null;
    private Timer timer = new Timer();
    private SchedulerTaskManager(){}
    public synchronized static SchedulerTaskManager getInstance(){
        if(INSTANCE==null){
            INSTANCE = new SchedulerTaskManager();
        }
        return INSTANCE;
    }
    public void scheduleCacheMapUpdateStartTask(TaskCommand taskCommand){
        timer.schedule(new DataBaseRefreshingSchedulerTask(taskCommand),0, 1*60*1000);
    }





}
