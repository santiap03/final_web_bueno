package Model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class Photo {
    @Id
    private String id;



    private int title;

    public void setImage(Binary image) {
        this.image = image;
    }

    private Binary image;

    public Photo(int title) {
        this.title = title;
    }

    public String getId() {
        return this.id;
    }

    public int getTitle() {
        return title;
    }

    public Binary getImage() {
        return image;
    }
}