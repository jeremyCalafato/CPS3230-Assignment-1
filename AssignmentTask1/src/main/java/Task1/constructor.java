package Task1;

public class constructor
{
    int alertType;
    String heading;
    String description;
    String url;
    String imageUrl;
    String postedBy;
    String priceInCents;

    public constructor(int alertType, String heading, String description, String url, String imageUrl, String postedBy, String priceInCents)
    {
        this.alertType = alertType;
        this.heading = heading;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.postedBy = postedBy;
        this.priceInCents = priceInCents;
    }
}
