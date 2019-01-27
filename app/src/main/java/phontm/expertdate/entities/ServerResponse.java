package phontm.expertdate.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Objects;

public class ServerResponse {

    @SerializedName("imagesToShow")
    String[] images;

    @SerializedName("goToExternalServer")
    boolean external;

    @SerializedName("urlToDisplay")
    String url;

    public ServerResponse(String[] images, boolean external, String url) {
        this.images = images;
        this.external = external;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerResponse that = (ServerResponse) o;
        return isExternal() == that.isExternal() &&
                Arrays.equals(getImages(), that.getImages());
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(isExternal());
        result = 31 * result + Arrays.hashCode(getImages());
        return result;
    }
}
