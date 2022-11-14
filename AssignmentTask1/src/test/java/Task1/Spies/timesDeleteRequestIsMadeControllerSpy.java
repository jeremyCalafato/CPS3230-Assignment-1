package Task1.Spies;

import Task1.Interfaces.timesDeleteRequestIsMadeController;

public class timesDeleteRequestIsMadeControllerSpy implements timesDeleteRequestIsMadeController
{
    int numTimesDeleteRequestIsMade = 0;

    public int makeDeleteRequest()
    {
        numTimesDeleteRequestIsMade++;
        return numTimesDeleteRequestIsMade;
    }
}
