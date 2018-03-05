package cr.ac.unadeca.todoadams.database.models;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by pc on 5/2/2018.
 */

public class TodoApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
