/**
 * Created by nishantvishwakarma on 10/02/15.
 */
public class ThresholdReachedNotification implements  INotification{

    private IThresholdListener thresholdListener;

    public ThresholdReachedNotification(IThresholdListener thresholdListener) {
        this.thresholdListener = thresholdListener;
    }

    @Override
    public void notifyEvent() {
        thresholdListener.notifyThresholdReached();
    }
}
