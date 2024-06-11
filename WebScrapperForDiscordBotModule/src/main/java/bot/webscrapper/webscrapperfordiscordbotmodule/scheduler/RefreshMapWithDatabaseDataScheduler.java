package bot.webscrapper.webscrapperfordiscordbotmodule.scheduler;

import java.util.Timer;
import java.util.TimerTask;

public class RefreshMapWithDatabaseDataScheduler {

    private Timer timer = new Timer();
    private static RefreshMapWithDatabaseDataScheduler INSTANCE = null;
    private RefreshMapWithDatabaseDataScheduler(){}
    public synchronized static RefreshMapWithDatabaseDataScheduler getINSTANCE(){
        if(INSTANCE==null){
            INSTANCE = new RefreshMapWithDatabaseDataScheduler();
        }
        return INSTANCE;
    }

    /**
     * Task dla schedulera ktory zaciaga konfiguracje z bazydanych i aktualizuje
     * WebCacheApplicationMap
     */
    public void startScheduler(){
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("instacja servisu TrackedCharacter");
//            }
//        },0,10*60*1000);
    }
}
