/**
 * Created by nishantvishwakarma on 10/02/15.
 */
public class ThresholdRelaxedNotification implements INotification {
    private IThresholdListener thresholdListener;

    public ThresholdRelaxedNotification(IThresholdListener thresholdListener) {
        this.thresholdListener = thresholdListener;
    }

    @Override
    public void notifyEvent() {
        thresholdListener.notifyThresholdIsControlled();
    }
}
