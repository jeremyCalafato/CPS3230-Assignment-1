package Task1.Spies;

import Task1.Interfaces.timesPostRequestIsMadeController;

public class timesPostRequestIsMadeControllerSpy implements timesPostRequestIsMadeController
{
    int numTimesPostRequestIsMade = 0;

    public int makePostRequest()
    {
        numTimesPostRequestIsMade++;
        return numTimesPostRequestIsMade;
    }
}
