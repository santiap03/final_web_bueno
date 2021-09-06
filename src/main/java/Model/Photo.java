package Model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class Photo {
    @Id
    private String id;

    private Binary image;

    private int ClientId;

    public void setImage(Binary image) {
        this.image = image;
    }

    public Photo(int clientId) {
        this.ClientId = clientId;
    }

    public String getId() {
        return this.id;
    }

    public int getTitle() {
        return ClientId;
    }

    public Binary getImage() {
        return image;
    }

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int clientId) {
        ClientId = clientId;
    }
}