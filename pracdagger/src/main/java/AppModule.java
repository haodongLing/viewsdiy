import android.content.Context;

/**
 * created by linghaoDo on 2020-05-18
 * description:
 * <p>
 * version:
 */
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }
    public Context provideContext(){
        return this.context;
    }
}
