public class ChordVariant {
    private String name;
    private int imageResource;
    private boolean isFavorite;

    public ChordVariant(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
        this.isFavorite = false;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}