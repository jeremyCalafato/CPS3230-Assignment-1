package Task1.Spies;

import Task1.Interfaces.timesWebsiteIsOpenedController;

public class timesWebsiteIsOpenedControllerSpy implements timesWebsiteIsOpenedController
{
    int numTimesPageIsOpened = 0;

    public int openPage()
    {
        numTimesPageIsOpened++;
        return numTimesPageIsOpened;
    }
}
